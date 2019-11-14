package com.github.diegopacheco.xunit.testing.bank.service;

import com.github.diegopacheco.xunit.testing.bank.model.AccountModel;

public interface Account {

    void deposit(AccountModel accountModel, Double value);
    void withdraw(AccountModel accountModel, Double value);
    Double check(AccountModel accountModel);
    boolean transfer(AccountModel origin, AccountModel destination, Double value);
}
