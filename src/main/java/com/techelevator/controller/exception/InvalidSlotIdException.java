package com.techelevator.controller.exception;

public class InvalidSlotIdException extends RuntimeException {
    public InvalidSlotIdException(String message) {
        super(message);
    }
}
