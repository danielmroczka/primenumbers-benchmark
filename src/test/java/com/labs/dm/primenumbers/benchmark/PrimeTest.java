package com.labs.dm.primenumbers.benchmark;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Unit tests for {@link Prime}.
 *
 * @author daniel
 */
public class PrimeTest {

    private final Prime prime = new Prime();

    private static final int[] KNOWN_PRIMES = {
        2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97
    };

    @Test
    public void shouldBePrime() {
        for (int p : KNOWN_PRIMES) {
            assertTrue("Expected " + p + " to be prime", prime.isPrime(p));
        }
    }

    @Test
    public void shouldNotBePrime() {
        assertFalse(prime.isPrime(1));
        assertFalse(prime.isPrime(4));
        assertFalse(prime.isPrime(6));
        assertFalse(prime.isPrime(8));
    }

    @Test
    public void countPrimesUpTo100ShouldBe25() {
        assertEquals(25, prime.countPrimesUpTo(100));
    }

    @Test
    public void findNthPrimeShouldReturnCorrectValues() {
        assertEquals(2,  prime.findNthPrime(1));
        assertEquals(3,  prime.findNthPrime(2));
        assertEquals(11, prime.findNthPrime(5));
        assertEquals(97, prime.findNthPrime(25));
    }
}
