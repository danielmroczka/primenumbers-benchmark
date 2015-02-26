package com.labs.dm.primenumbers.benchmark;

/**
 * Class responsible for computing prime numbers
 * 
 * @author daniel
 */
public class Prime {

    /**
     * Computes prime number until provided value
     * 
     * @param limit
     * @return 
     */
    public int execute(int limit) {
        int count = 0;
        for (int number = 2; count < limit; number++) {
            if (isPrime(number)) {
                count++;
            }
        }

        return count;
    }

    /**
     * Computes prime number and returns maxCount results
     * 
     * @param maxCount
     * @return 
     */
    public int executeMaxCount(int maxCount) {
        int count = 0, number = 2;

        while (count < maxCount) {
            if (isPrime(number)) {
                count++;
            }
        }

        return count;
    }

    /**
     * Returns true if input is a prime number
     * 
     * @param n
     * @return 
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
