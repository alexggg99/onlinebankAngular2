package com.bfwg.service;


import com.bfwg.model.*;
import com.bfwg.rest.AccountController;

import java.math.BigDecimal;
import java.util.List;

public interface AccountService {
    List<Account> getAllAccounts(String username);
    PrimaryAccount createPrimaryAccount(Currency currency, String username);
    SavingAccount createSavingAccount(String username);
    Account getAccount(long accountId, String username);
    List<PrimaryAccount> getPrimaryAccounts(String username);
    List<SavingAccount> getSavingAccount(String username);
    void saveAccount(Account account);
    void manageAccount(AccountController.FormCommand command, String username);
    void transferBetweenAccounts(String accountIdFrom, String accountIdTo, BigDecimal amount, String username);
}
