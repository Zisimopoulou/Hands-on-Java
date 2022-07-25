package com.team7.handsOnJava.exception;

public class EshopException extends Exception{
    public EshopException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public EshopException(final String message) {
        super(message);
    }
}
