package com.labs.dm.primenumbers.benchmark;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author daniel
 */
public class PrimeTest {

    final Prime prime = new Prime();
    int[] primes = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97};
    @Test
    public void shouldBePrime() {
        for (int p:primes) {
            assertTrue(prime.isPrime(p));
        }
    }

    @Test
    public void shouldNotBePrime() {
        assertFalse(prime.isPrime(1));
        assertFalse(prime.isPrime(4));
        assertFalse(prime.isPrime(6));
        assertFalse(prime.isPrime(8));
    }

}
