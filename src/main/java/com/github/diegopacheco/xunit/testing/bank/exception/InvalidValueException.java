package com.github.diegopacheco.xunit.testing.bank.exception;

public class InvalidValueException extends RuntimeException {
    public InvalidValueException() {
        super("Invalid value.");
    }

    public InvalidValueException(String message) {
        super(message);
    }
}
