package com.github.diegopacheco.xunit.testing.bank.service;

import com.github.diegopacheco.xunit.testing.bank.exception.InvalidValueException;
import com.github.diegopacheco.xunit.testing.bank.exception.MinimalValueException;
import com.github.diegopacheco.xunit.testing.bank.exception.NoFoundsException;
import com.github.diegopacheco.xunit.testing.bank.model.AccountModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AccountStandardTest {

    @Test
    public void shouldDeposit() {
        AccountModel accountModel = new AccountModel();
        accountModel.setValue(10.0);
        accountModel.setId(1);
        accountModel.setName("Daniela");

        Account accountStandard = new AccountStandard();
        accountStandard.deposit(accountModel, 200.0);

        assertEquals(210.0, accountModel.getValue());
    }

    @Test
    public void shouldThrowExceptionWhenValueIsMinorThan100() {
        AccountModel accountModel = new AccountModel();
        accountModel.setValue(10.0);
        accountModel.setId(1);
        accountModel.setName("Daniela");

        Account accountStandard = new AccountStandard();
        assertThrows(MinimalValueException.class, () -> {
            accountStandard.deposit(accountModel, 20.0);
        });
    }

    @Test
    public void shouldThrowExceptionWhenDepositValueIsNull() {
        AccountModel accountModel = new AccountModel();
        accountModel.setValue(10.0);
        accountModel.setId(1);
        accountModel.setName("Daniela");

        Account accountStandard = new AccountStandard();
        assertThrows(InvalidValueException.class, () -> {
            accountStandard.deposit(accountModel, null);
        });
    }

    @Test
    public void shouldThrowExceptionWhenAccountValueIsNull() {
        AccountModel accountModel = new AccountModel();
        accountModel.setValue(null);
        accountModel.setId(1);
        accountModel.setName("Daniela");

        Account accountStandard = new AccountStandard();
        assertThrows(InvalidValueException.class, () -> {
            accountStandard.deposit(accountModel, 10.0);
        });
    }

    @Test
    public void shouldThrowExceptionWhenAccountIsNull() {
        AccountModel accountModel = null;

        Account accountStandard = new AccountStandard();
        assertThrows(InvalidValueException.class, () -> {
            accountStandard.deposit(accountModel, 10.0);
        });
    }

    @Test
    public void shouldWithdraw() {
        AccountModel accountModel = new AccountModel();
        accountModel.setValue(10.0);
        accountModel.setId(1);
        accountModel.setName("Daniela");

        Account accountStandard = new AccountStandard();
        accountStandard.withdraw(accountModel, 5.0);

        assertEquals(5.0, accountModel.getValue());
    }

    @Test
    public void shouldThrowExceptionWhenWithdrawValueIsGreaterThanAccountValue() {
        AccountModel accountModel = new AccountModel();
        accountModel.setValue(10.0);
        accountModel.setId(1);
        accountModel.setName("Daniela");

        Account accountStandard = new AccountStandard();

        assertThrows(NoFoundsException.class, () -> {
            accountStandard.withdraw(accountModel, 100.0);
        });
    }

    @Test
    public void shouldThrowExceptionWhenGivenNegativeValue() {
        AccountModel accountModel = new AccountModel();
        accountModel.setValue(100.0);
        accountModel.setId(1);
        accountModel.setName("Daniela");

        Account accountStandard = new AccountStandard();
        assertThrows(InvalidValueException.class, () -> {
            accountStandard.withdraw(accountModel, -20.0);
        });
    }

    @Test
    public void shouldThrowExceptionWhenWithdrawValueIsNull() {
        AccountModel accountModel = new AccountModel();
        accountModel.setValue(10.0);
        accountModel.setId(1);
        accountModel.setName("Daniela");

        Account accountStandard = new AccountStandard();
        assertThrows(InvalidValueException.class, () -> {
            accountStandard.withdraw(accountModel, null);
        });
    }

    @Test
    public void shouldThrowExceptionWhenWithdrawAccountValueIsNull() {
        AccountModel accountModel = new AccountModel();
        accountModel.setValue(null);
        accountModel.setId(1);
        accountModel.setName("Daniela");

        Account accountStandard = new AccountStandard();
        assertThrows(InvalidValueException.class, () -> {
            accountStandard.withdraw(accountModel, 10.0);
        });
    }

    @Test
    public void shouldThrowExceptionWhenWithdrawAccountIsNull() {
        AccountModel accountModel = null;

        Account accountStandard = new AccountStandard();
        assertThrows(InvalidValueException.class, () -> {
            accountStandard.withdraw(accountModel, 10.0);
        });
    }

    @Test
    public void shouldReturnAccountAmount() {
        AccountModel accountModel = new AccountModel();
        accountModel.setValue(10.0);
        accountModel.setId(1);
        accountModel.setName("Daniela");

        Account accountStandard = new AccountStandard();
        assertEquals(10.0, accountStandard.check(accountModel));
    }

    @Test
    public void shouldThrowExceptionWhenCheckAccountValueIsNull() {
        AccountModel accountModel = new AccountModel();
        accountModel.setValue(null);
        accountModel.setId(1);
        accountModel.setName("Daniela");

        Account accountStandard = new AccountStandard();
        assertThrows(InvalidValueException.class, () -> {
            accountStandard.check(accountModel);
        });
    }

    @Test
    public void shouldThrowExceptionWhenCheckAccountIsNull() {
        AccountModel accountModel = null;

        Account accountStandard = new AccountStandard();
        assertThrows(InvalidValueException.class, () -> {
            accountStandard.check(accountModel);
        });
    }

    @Test
    public void shouldTransferToAnother() {
        AccountModel original = new AccountModel();
        original.setValue(50.0);
        original.setId(1);
        original.setName("Daniela");

        AccountModel destination = new AccountModel();
        destination.setValue(100.0);
        destination.setId(2);
        destination.setName("Andressa");

        Account accountStandard = new AccountStandard();
        accountStandard.transfer(original, destination, 40.0);

        assertEquals(8.0, original.getValue());
        assertEquals(140.0, destination.getValue());
    }

    @Test
    public void shouldThrowExceptionWhenTransferValueIsGreaterThanAccountAmount() {
        AccountModel original = new AccountModel();
        original.setValue(50.0);
        original.setId(1);
        original.setName("Daniela");

        AccountModel destination = new AccountModel();
        destination.setValue(100.0);
        destination.setId(2);
        destination.setName("Andressa");

        Account accountStandard = new AccountStandard();
        assertThrows(InvalidValueException.class, () -> {
            accountStandard.transfer(original, destination, 51.0);
        });
    }

    @Test
    public void shouldThrowExceptionWhenTransferValueWithFeeIsGreaterThanAccountAmount() {
        AccountModel original = new AccountModel();
        original.setValue(50.0);
        original.setId(1);
        original.setName("Daniela");

        AccountModel destination = new AccountModel();
        destination.setValue(100.0);
        destination.setId(2);
        destination.setName("Andressa");

        Account accountStandard = new AccountStandard();
        assertThrows(InvalidValueException.class, () -> {
            accountStandard.transfer(original, destination, 49.0);
        });
    }

}
