package com.techelevator.controller.exception;

public class InvalidBillOptionException extends RuntimeException {
    public InvalidBillOptionException(String message) {
        super(message);
    }
}
