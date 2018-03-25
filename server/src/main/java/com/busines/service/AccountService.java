package com.busines.service;

import com.busines.model.accounts.Account;
import com.busines.model.accounts.Currency;
import com.busines.model.accounts.PrimaryAccount;
import com.busines.model.accounts.SavingAccount;

import java.math.BigDecimal;
import java.util.List;

public interface AccountService {
    List<Account> getAllAccounts(String username);
    PrimaryAccount createPrimaryAccount(Currency currency, String username);
    SavingAccount createSavingAccount(String username);
    Account getPrimaryAccount(long accountId, String username);
    Account getSavingAccount(long accountId, String username);
    void saveAccount(Account account);
    void manageAccount(String action, String accountId, BigDecimal amount, String username);
    void transferBetweenAccounts(String accountIdFrom, String accountIdTo, BigDecimal amount, String username);
}
