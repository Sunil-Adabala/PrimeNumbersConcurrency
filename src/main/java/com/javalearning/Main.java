package com.javalearning;

import java.util.logging.Logger;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Integer upperLimit = 100000000;
        Integer numberOfThreads = 10;
        MultiThreadedFair multiThreadedFair = new MultiThreadedFair(new PrimeChecker(), upperLimit, numberOfThreads);
        multiThreadedFair.doWork();

//        MultiThreadedUnFair multiThreadedUnFair = new MultiThreadedUnFair(new PrimeChecker(),  upperLimit, numberOfThreads);
//        multiThreadedUnFair.doWork();
//
//        SingleThreaded singleThreaded = new SingleThreaded(new PrimeChecker(), upperLimit);
//        singleThreaded.doWork();

//        System.out.println("Total time taken to find Total primes in first "+upperLimit+" using -\n"+
//                "1.Using Single Threaded  -> " + singleThreaded.getTotalExecutionTime()+
//                "\n2.MultiThreaded Unfair using Batches -> " + multiThreadedUnFair.getTotalExecutionTime()+
//                "\n3.MultiThreaded with Fairness using shared Counter -> "+ multiThreadedFair.getTotalExecutionTime());


    }
}