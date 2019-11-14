package com.github.diegopacheco.xunit.testing.bank.service;

import com.github.diegopacheco.xunit.testing.bank.exception.InvalidValueException;
import com.github.diegopacheco.xunit.testing.bank.exception.MinimalValueException;
import com.github.diegopacheco.xunit.testing.bank.exception.NoFoundsException;
import com.github.diegopacheco.xunit.testing.bank.model.AccountModel;

public class AccountStandard implements Account {

    @Override
    public void deposit(AccountModel accountModel, Double value) {
        if (value == null) {
            throw new InvalidValueException("Value can't be null");
        }

        if (value < 100) {
            throw new MinimalValueException();
        }

        accountModel.setValue(accountModel.getValue() + value);
    }

    @Override
    public void withdraw(AccountModel accountModel, Double value) {
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
        return accountModel.getValue();
    }

    @Override
    public boolean transfer(AccountModel origin, AccountModel destination, Double value) {
        Double fee = value * 0.05;
        Double newOrigin = origin.getValue() - (value + fee);
        Double newDestination = destination.getValue() + value;

        if (newOrigin < 0) {
            throw new InvalidValueException("Value to transfer is greater than account amount");
        }

        origin.setValue(newOrigin);
        destination.setValue(newDestination);

        return true;
    }
}