package com.labs.dm.primenumbers.benchmark;

import java.util.concurrent.ForkJoinPool;

/**
 *
 * @author daniel
 */
public class PrimeNumberMulticore {

    public static void main(String args[]) {

        //get input till which prime number to be printed
        System.out.println("Enter the number till which prime number to be printed: ");
        int limit = 100000;

        //printing primer numbers till the limit ( 1 to 100)
        System.out.println("Printing prime number from 1 to " + limit);
        int count = 0;

        long time = System.nanoTime();
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        
        //forkJoinPool.e

        for (int number = 2; count < limit; number++) {
            //print prime numbers only
            
        }

        time = System.nanoTime() - time;

        System.out.println(count);
        System.out.println(time);

    }

    
}
