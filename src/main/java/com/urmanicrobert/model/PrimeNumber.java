package com.urmanicrobert.model;

import com.urmanicrobert.exceptions.IncorrectPrimeNumberException;

public class PrimeNumber {
    public static int counter = 0;

    private int primeNumber;

    public PrimeNumber(int primeNumber) throws IncorrectPrimeNumberException {
        this.primeNumber = checkPrime(primeNumber);
    }

    protected int checkPrime(int primeNumber) throws IncorrectPrimeNumberException{
        if (primeNumber == 2) return primeNumber;
        if (primeNumber <= 1) throw new IncorrectPrimeNumberException("The imported number is not a primary number");
        for (int i = 2; i < Math.sqrt(primeNumber) + 1; i++) {
            if ((primeNumber % i == 0)) throw new IncorrectPrimeNumberException("The imported number is not a primary number");
        }
        return primeNumber;
    }

    public int getPrimeNumber() {
        return primeNumber;
    }
}
