package com.javalearning;

public class PrimeChecker {
    public boolean isPrime(int n) {
        for (int i = 2; i <= Math.sqrt(n) ; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
