package com.javalearning;

import java.util.concurrent.atomic.AtomicInteger;

public class MultiThreadedUnFair {
    PrimeChecker primeChecker;
    AtomicInteger totalPrimes;
    Integer upperLimit;
    Integer numberOfThreads;
    Integer batchSize;
    Double totalExecutionTime;

    public Double getTotalExecutionTime() {
        return totalExecutionTime;
    }

    private void setTotalExecutionTime(Double totalExecutionTime) {
        this.totalExecutionTime = totalExecutionTime;
    }

    public MultiThreadedUnFair(PrimeChecker primeChecker, Integer upperLimit, Integer numberOfThreads) {
        this.primeChecker = primeChecker;
        this.totalPrimes = new AtomicInteger(0);
        this.upperLimit = upperLimit;
        this.numberOfThreads = numberOfThreads;
        this.batchSize  = upperLimit / numberOfThreads;
    }

    public void doWork(){
        long start = System.currentTimeMillis();
        createThreadsAndRun();
        long end = System.currentTimeMillis();
        setTotalExecutionTime(((double)(end - start) / 1000));
        System.out.println("All Threads Finished execution in -> "+getTotalExecutionTime()+" second(s), Total Primes: " + (totalPrimes.get() + 1));
    }

    private void createThreadsAndRun() {
        Thread[] threads = new Thread[this.numberOfThreads];
        for (int i = 0; i < this.numberOfThreads; i++) {
            int batchStart = (this.batchSize * i) + 1;
            int batchEnd = batchStart + batchSize - 1;
            int threadIndex = i;
            threads[i] = new Thread(() -> run(batchStart, batchEnd, threadIndex + 1));
            threads[i].start();
        }


        for(int i = 0; i < numberOfThreads; i++){
            try{
                threads[i].join();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }


    private void run(int batchStart, int batchEnd, int threadIndex) {
        long threadStartTime = System.currentTimeMillis();
        System.out.println("Thread " + threadIndex + " of " + numberOfThreads + " is created and started running, batchStart is " + batchStart + " batchEnd is " + batchEnd);
        for(int i = batchStart; i <= batchEnd; i++){
            if (primeChecker.isPrime(i)) {
                totalPrimes.incrementAndGet();
            }
        }
        long threadEndTime = System.currentTimeMillis();
        double executionTime = (threadEndTime - threadStartTime) / 1000.0;
        setTotalExecutionTime(executionTime);
        System.out.println("Thread " + threadIndex + " has finished. Total time takes to execute current Thread -> "+executionTime+" second(s)");
    }
}
