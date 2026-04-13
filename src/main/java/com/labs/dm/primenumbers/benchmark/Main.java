package com.labs.dm.primenumbers.benchmark;

import java.util.Arrays;

/**
 * Entry point for the prime-numbers benchmark.
 *
 * Usage:
 *   java -jar app.jar single [limit]
 *   java -jar app.jar multi  [limit] [threads]
 *
 * If no arguments are provided the usage will be printed.
 */
public class Main {

    public static void main(String[] args) {
        if (args.length == 0) {
            printUsage();
            return;
        }

        String mode = args[0];

        if ("single".equalsIgnoreCase(mode)) {
            // forward remaining args to SingleCorePrimeNumber (optional limit)
            String[] forward = (args.length > 1) ? Arrays.copyOfRange(args, 1, args.length) : new String[0];
            SingleCorePrimeNumber.main(forward);
        } else if ("multi".equalsIgnoreCase(mode)) {
            // forward remaining args to PrimeNumberMulticore (optional limit and threads)
            String[] forward = (args.length > 1) ? Arrays.copyOfRange(args, 1, args.length) : new String[0];
            PrimeNumberMulticore.main(forward);
        } else {
            System.out.println("Unknown mode: " + mode);
            printUsage();
        }
    }

    private static void printUsage() {
        System.out.println("Usage:");
        System.out.println("  java -jar app.jar single [limit]");
        System.out.println("  java -jar app.jar multi  [limit] [threads]");
        System.out.println("Examples:");
        System.out.println("  java -jar app.jar single 1000000");
        System.out.println("  java -jar app.jar multi 1000000 4");
    }
}
