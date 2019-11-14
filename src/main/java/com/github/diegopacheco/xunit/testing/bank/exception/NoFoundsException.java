package com.github.diegopacheco.xunit.testing.bank.exception;

public class NoFoundsException extends RuntimeException {
    public NoFoundsException() {
        super("You don't have enough amount");
    }
}
