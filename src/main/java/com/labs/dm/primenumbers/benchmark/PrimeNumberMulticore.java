package com.labs.dm.primenumbers.benchmark;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * Multi-core prime number benchmark using a ForkJoinPool with parallel streams.
 *
 * @author daniel
 */
public class PrimeNumberMulticore {

    public static void main(String[] args) {
        int limit = 1_000_000;

        System.out.println("Counting primes up to " + limit + " (multi-core)");

        Prime prime = new Prime();
        ForkJoinPool forkJoinPool = new ForkJoinPool();

        long time = System.nanoTime();
        AtomicInteger count = new AtomicInteger();
        try {
            forkJoinPool.submit(() ->
                IntStream.rangeClosed(2, limit)
                        .parallel()
                        .filter(prime::isPrime)
                        .forEach(n -> count.incrementAndGet())
            ).get();
        } catch (Exception e) {
            Thread.currentThread().interrupt();
            System.err.println("Benchmark interrupted: " + e.getMessage());
        } finally {
            forkJoinPool.shutdown();
        }
        time = System.nanoTime() - time;

        System.out.println("Count: " + count.get());
        System.out.println("Time:  " + (double) time / 1_000_000 + " [ms]");
    }
}
