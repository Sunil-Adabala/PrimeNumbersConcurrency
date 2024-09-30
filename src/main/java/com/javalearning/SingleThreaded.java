package com.javalearning;

import java.util.concurrent.atomic.AtomicInteger;

public class SingleThreaded {
    PrimeChecker primeChecker;
    Integer totalPrimes;
    Integer upperLimit;
    Double totalExecutionTime;

    public Double getTotalExecutionTime() {
        return totalExecutionTime;
    }

    private void setTotalExecutionTime(Double totalExecutionTime) {
        this.totalExecutionTime = totalExecutionTime;
    }

    public SingleThreaded(PrimeChecker primeChecker, Integer upperLimit) {
        this.primeChecker = primeChecker;
        this.upperLimit = upperLimit;
        this.totalPrimes = 0;
    }

    public void doWork(){
        long startTime = System.currentTimeMillis();
        for(int i = 3; i <= this.upperLimit; i++){
            if (primeChecker.isPrime(i)) {
                totalPrimes += 1;
            }
        }
        long endTime = System.currentTimeMillis();
        setTotalExecutionTime((endTime - startTime) / 1000.0);
        System.out.println("Total Primes: " + (totalPrimes + 1)+" Time taken for execution: " +getTotalExecutionTime()+"sec(s)");
    }
}
