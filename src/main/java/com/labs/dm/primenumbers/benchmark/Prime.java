package com.labs.dm.primenumbers.benchmark;

/**
 * Utility class for computing prime numbers.
 *
 * @author daniel
 */
public class Prime {

    /**
     * Counts how many prime numbers exist up to (and including) the given limit.
     *
     * @param limit upper bound (inclusive)
     * @return number of primes in range [2, limit]
     */
    public int countPrimesUpTo(int limit) {
        int count = 0;
        for (int number = 2; number <= limit; number++) {
            if (isPrime(number)) {
                count++;
            }
        }
        return count;
    }

    /**
     * Finds the n-th prime number (1-based).
     * For example, {@code findNthPrime(1)} returns 2, {@code findNthPrime(5)} returns 11.
     *
     * @param n position of the prime to find (must be &gt;= 1)
     * @return value of the n-th prime number
     */
    public int findNthPrime(int n) {
        int count = 0;
        int number = 1;
        while (count < n) {
            number++;
            if (isPrime(number)) {
                count++;
            }
        }
        return number;
    }

    /**
     * Returns {@code true} if {@code n} is a prime number.
     *
     * @param n number to test
     * @return {@code true} if prime, {@code false} otherwise
     */
    protected boolean isPrime(int n) {
        if (n < 2 || (n > 2 && n % 2 == 0)) {
            return false;
        }
        for (int i = 3; i * i <= n; i += 2) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
