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

        System.out.println(count);
        System.out.println((double) time / 1000000f + " [ms]");
        System.out.println(System.getenv("PROCESSOR_IDENTIFIER"));
        System.out.println(System.getenv("PROCESSOR_ARCHITECTURE"));
        System.out.println(System.getenv("NUMBER_OF_PROCESSORS"));
        System.out.println(System.getProperty("java.vm.specification.version"));
        System.out.println(System.getProperty("java.vm.version"));
        System.out.println(System.getProperty("java.vm.name"));
        System.out.println(System.getProperty("os.name"));

    }

}
