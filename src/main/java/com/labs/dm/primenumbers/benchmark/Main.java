package com.labs.dm.primenumbers.benchmark;

/**
 *
 * @author daniel 
 */
public class Main {
    public static void main(String[] args) {
        if (args.length > 0) {
            if ("single".equalsIgnoreCase(args[0])) {
                SingleCorePrimeNumber.main(args);
            } else if ("multi".equalsIgnoreCase(args[0])) {
                PrimeNumberMulticore.main(args);
            } else {
                System.out.println("usage: single|multi");
            }
        }
    }
}
