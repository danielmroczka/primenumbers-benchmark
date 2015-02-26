package com.labs.dm.primenumbers.benchmark;

/**
 *
 * @author daniel
 */
public class SingleCorePrimeNumber {

    public static void main(String args[]) {
        int limit = 1000000;

        System.out.println("Printing prime number from 1 to " + limit);

        long time = System.nanoTime();
        Prime prime = new Prime();
        int count = prime.execute(limit);

        time = System.nanoTime() - time;

        System.out.println("Counts: \t" + count);
        System.out.println("Time: \t" + (double) time / 1000000f + " [ms]");
        System.out.println(System.getenv("PROCESSOR_IDENTIFIER"));
        System.out.println(System.getenv("PROCESSOR_ARCHITECTURE"));
        System.out.println("Cores: \t" + System.getenv("NUMBER_OF_PROCESSORS"));
        System.out.println(System.getProperty("java.vm.name") + " " + System.getProperty("java.vm.specification.version") + " " + System.getProperty("java.vm.version"));
        System.out.println(System.getProperty("os.name"));

    }

}
