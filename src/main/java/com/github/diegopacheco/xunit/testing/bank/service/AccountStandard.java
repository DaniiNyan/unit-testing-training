package com.github.diegopacheco.xunit.testing.bank.service;

import com.github.diegopacheco.xunit.testing.bank.exception.InvalidValueException;
import com.github.diegopacheco.xunit.testing.bank.exception.MinimalValueException;
import com.github.diegopacheco.xunit.testing.bank.exception.NoFoundsException;
import com.github.diegopacheco.xunit.testing.bank.model.AccountModel;

public class AccountStandard implements Account {

    @Override
    public void deposit(AccountModel accountModel, Double value) {
        if (accountModel == null) {
            throw new InvalidValueException("Account can't be null");
        }

        if (accountModel.getValue() == null) {
            throw new InvalidValueException("Account value can't be null");
        }

        if (value == null) {
            throw new InvalidValueException("Value can't be null");
        }

        if (value < 100) {
            throw new MinimalValueException();
        }

        Double roundedResult = roundNumber(accountModel.getValue() + value);
        accountModel.setValue(roundedResult);
    }

    @Override
    public void withdraw(AccountModel accountModel, Double value) {
        if (accountModel == null) {
            throw new InvalidValueException("Account can't be null");
        }

        if (accountModel.getValue() == null) {
            throw new InvalidValueException("Account value can't be null");
        }

        if (value == null) {
            throw new InvalidValueException("Value can't be null");
        }

        if (accountModel.getValue() < value) {
            throw new NoFoundsException();
        }

        if (value < 2) {
            throw new InvalidValueException();
        }
        accountModel.setValue(accountModel.getValue() - value);
    }

    @Override
    public Double check(AccountModel accountModel) {
        if (accountModel == null) {
            throw new InvalidValueException("Account can't be null");
        }

        if (accountModel.getValue() == null) {
            throw new InvalidValueException("Account value can't be null");
        }

        return accountModel.getValue();
    }

    @Override
    public boolean transfer(AccountModel origin, AccountModel destination, Double value) {
        if (origin == null) {
            throw new InvalidValueException("Origin can't be null");
        }

        if (origin.getValue() == null) {
            throw new InvalidValueException("Origin value can't be null");
        }

        if (destination == null) {
            throw new InvalidValueException("Destination can't be null");
        }

        if (destination.getValue() == null) {
            throw new InvalidValueException("Destination value can't be null");
        }

        if (value == null) {
            throw new InvalidValueException("Value can't be null");
        }

        Double fee = value * 0.05;
        Double newOrigin = origin.getValue() - (value + fee);
        Double newDestination = destination.getValue() + value;

        if (newOrigin < 0) {
            throw new InvalidValueException("Value to transfer is greater than account amount");
        }

        origin.setValue(roundNumber(newOrigin));
        destination.setValue(roundNumber(newDestination));

        return true;
    }

    private Double roundNumber(Double number) {
        return Math.floor(number * 100) / 100;
    }
}