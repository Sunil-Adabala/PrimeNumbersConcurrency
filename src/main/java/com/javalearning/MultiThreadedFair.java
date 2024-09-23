package com.javalearning;

import java.util.concurrent.atomic.AtomicInteger;

public class MultiThreadedFair extends Thread {
    PrimeChecker primeChecker;
    AtomicInteger totalPrimes;
    Integer upperLimit;
    Integer numberOfThreads;


    public MultiThreadedFair(PrimeChecker primeChecker, Integer upperLimit, Integer numberOfThreads) {
        this.primeChecker = primeChecker;
        this.totalPrimes = new AtomicInteger(0);
        this.upperLimit = upperLimit ;
        this.numberOfThreads = numberOfThreads;
    }

    public void doWork(){
        long startTime = System.currentTimeMillis();
        createThreadsAndRun(this.numberOfThreads);
        long endTime = System.currentTimeMillis();


        System.out.println("Total prime numbers: " + (this.totalPrimes.incrementAndGet()));
        System.out.println("Total time to find all the prime numbers less than "+this.upperLimit+" using "+numberOfThreads+" thread(s) is -> "+(endTime - startTime)/1000 + " second(s)");
    }

    private void createThreadsAndRun(int numberOfThreads) {
        AtomicInteger counter = new AtomicInteger(3);

        Thread[] threads = new Thread[numberOfThreads];

        long threadStartTime = System.currentTimeMillis();
        for (int i = 0; i < numberOfThreads; i++) {
            final int threadIndex = i + 1;
            threads[i] = new Thread(() -> run(counter));
            System.out.println("Starting thread -> " + threadIndex);
            threads[i].start();
        }
        long threadEndTime = 0;
        // Wait for all threads to complete
        for (int i = 0; i < numberOfThreads; i++) {

            try {
                threads[i].join();  // This will make the main thread wait for the worker threads
                threadEndTime = System.currentTimeMillis();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread " + (i + 1) + " has finished. Total time takes to execute current Thread -> "+((double)(threadEndTime - threadStartTime) / 1000)+" second(s)");
        }
    }


    private void run(AtomicInteger counter) {
        while (true) {
            int currentNumber;

            // Synchronize access to the shared counter
            synchronized (counter) {
                currentNumber = counter.getAndIncrement();
                if (currentNumber >= this.upperLimit) {
                    break;
                }
            }

            if (primeChecker.isPrime(currentNumber)) {
                totalPrimes.incrementAndGet();
            }
        }
    }


}
