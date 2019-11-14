package com.github.diegopacheco.xunit.testing.bank.exception;

public class MinimalValueException extends RuntimeException {
    public MinimalValueException() {
        super("Minimal value must be 100.00");
    }
}
