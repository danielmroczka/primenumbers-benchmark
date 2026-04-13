package com.labs.dm.primenumbers.benchmark;

/**
 * Single-core prime number benchmark.
 *
 * @author daniel
 */
public class SingleCorePrimeNumber {

    private static final int DEFAULT_LIMIT = 100_000_000;

    public static void main(String[] args) {
        int limit = DEFAULT_LIMIT;
        if (args != null && args.length > 0) {
            try {
                limit = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                System.err.println("Invalid limit provided, using default: " + DEFAULT_LIMIT);
            }
        }

        System.out.println("Counting primes up to " + limit + " (single-core)");

        long time = System.nanoTime();
        Prime prime = new Prime();
        int count = prime.countPrimesUpTo(limit);
        time = System.nanoTime() - time;

        System.out.println("Count:        " + count);
        System.out.println("Time:         " + (double) time / 1_000_000 + " [ms]");
        System.out.println("CPU:          " + System.getenv("PROCESSOR_IDENTIFIER"));
        System.out.println("Architecture: " + System.getenv("PROCESSOR_ARCHITECTURE"));
        System.out.println("Cores:        " + System.getenv("NUMBER_OF_PROCESSORS"));
        System.out.println("JVM:          " + System.getProperty("java.vm.name")
                + " " + System.getProperty("java.vm.specification.version")
                + " " + System.getProperty("java.vm.version"));
        System.out.println("OS:           " + System.getProperty("os.name"));
    }
}
