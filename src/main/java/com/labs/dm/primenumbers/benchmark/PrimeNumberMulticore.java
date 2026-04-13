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
        int threads = Runtime.getRuntime().availableProcessors();

        if (args != null && args.length > 0) {
            try {
                limit = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                System.err.println("Invalid limit provided, using default: " + limit);
            }
            if (args.length > 1) {
                try {
                    threads = Integer.parseInt(args[1]);
                } catch (NumberFormatException e) {
                    System.err.println("Invalid thread count provided, using default: " + threads);
                }
            }
        }

        System.out.println("Counting primes up to " + limit + " (multi-core, threads=" + threads + ")");

        Prime prime = new Prime();
        ForkJoinPool forkJoinPool = new ForkJoinPool(threads);

        long time = System.nanoTime();
        AtomicInteger count = new AtomicInteger();
        try {
            // Submit a task to the custom pool so parallel streams use it
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
