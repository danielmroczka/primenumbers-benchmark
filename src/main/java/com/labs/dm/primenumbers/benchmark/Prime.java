package com.labs.dm.primenumbers.benchmark;

import java.util.Arrays;

/**
 * Utility class for computing prime numbers. Contains a few operations used by
 * the benchmark and unit tests.
 *
 * Implementations aim for clarity and reasonable performance: the counting and
 * nth-prime operations use a sieve where appropriate which is much faster than
 * repeated trial division for larger limits.
 */
public class Prime {

    /**
     * Counts how many prime numbers exist up to (and including) the given limit.
     * Uses an efficient Sieve of Eratosthenes implementation.
     *
     * @param limit upper bound (inclusive)
     * @return number of primes in range [2, limit]
     */
    public int countPrimesUpTo(int limit) {
        if (limit < 2) {
            return 0;
        }
        boolean[] isComposite = sieve(limit);
        int count = 0;
        for (int i = 2; i <= limit; i++) {
            if (!isComposite[i]) {
                count++;
            }
        }
        return count;
    }

    /**
     * Finds the n-th prime number (1-based).
     * Uses an upper bound estimate and a sieve to collect primes.
     *
     * @param n position of the prime to find (must be >= 1)
     * @return value of the n-th prime number
     * @throws IllegalArgumentException if n &lt; 1
     */
    public int findNthPrime(int n) {
        if (n < 1) {
            throw new IllegalArgumentException("n must be >= 1");
        }
        if (n == 1) {
            return 2;
        }

        // For n >= 6, an upper bound for nth prime is: n*(log n + log log n)
        // Use a small safety factor. For very small n use a small table.
        int limit;
        if (n < 6) {
            limit = 15;
        } else {
            double dn = n;
            double estimate = dn * (Math.log(dn) + Math.log(Math.log(dn)));
            limit = (int) (estimate + 10);
        }

        // Increase limit until we have at least n primes (rare loop)
        while (true) {
            boolean[] isComposite = sieve(limit);
            int count = 0;
            int found = -1;
            for (int i = 2; i <= limit; i++) {
                if (!isComposite[i]) {
                    count++;
                    if (count == n) {
                        found = i;
                        break;
                    }
                }
            }
            if (found != -1) {
                return found;
            }
            // Not enough primes — increase limit and retry
            limit = limit * 2;
        }
    }

    /**
     * Returns {@code true} if {@code n} is a prime number.
     * Uses fast trial division for correctness and speed on small inputs.
     *
     * @param n number to test
     * @return {@code true} if prime, {@code false} otherwise
     */
    public boolean isPrime(int n) {
        if (n < 2) {
            return false;
        }
        if (n == 2) {
            return true;
        }
        if (n % 2 == 0) {
            return false;
        }
        int r = (int) Math.sqrt(n);
        for (int i = 3; i <= r; i += 2) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    // Helper: simple sieve of Eratosthenes returning a composite boolean array
    private boolean[] sieve(int limit) {
        boolean[] isComposite = new boolean[limit + 1];
        Arrays.fill(isComposite, false);
        if (limit < 2) {
            return isComposite;
        }
        int max = (int) Math.sqrt(limit);
        for (int i = 2; i <= max; i++) {
            if (!isComposite[i]) {
                for (int j = i * i; j <= limit; j += i) {
                    isComposite[j] = true;
                }
            }
        }
        return isComposite;
    }
}
