package com.test.privat.currency.models.exceptions;

public class RatesNotReceivedException extends Exception{
    public RatesNotReceivedException(String message, Throwable cause) {
        super(message, cause);
    }
}
