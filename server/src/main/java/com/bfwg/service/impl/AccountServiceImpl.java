package com.bfwg.service.impl;

import com.bfwg.model.*;
import com.bfwg.repository.PrimaryAccountRepo;
import com.bfwg.repository.SavingAccountRepo;
import com.bfwg.rest.AccountController;
import com.bfwg.service.AccountService;
import com.bfwg.service.ExchangeRateService;
import com.bfwg.service.UserService;
import com.bfwg.exception.GeneralException;
import com.bfwg.exception.NotEnoughAccountBalance;
import com.bfwg.repository.AccountRepo;
import com.bfwg.repository.TransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    private static int nextAccountNum = 46454765;

    @Autowired
    private AccountRepo accountRepo;
    @Autowired
    private PrimaryAccountRepo primaryAccountRepo;
    @Autowired
    private SavingAccountRepo savingAccountRepo;
    @Autowired
    private TransactionRepo transactionRepo;
    @Autowired
    private UserService userService;
    @Autowired
    private ExchangeRateService exchangeRateService;

    @Override
    public List<Account> getAllAccounts(String username) {
        return accountRepo.findByUserUsernameOrderById(username);
    }

    @Override
    public PrimaryAccount createPrimaryAccount(Currency currency, String username) {
        PrimaryAccount primaryAccount = new PrimaryAccount();
        primaryAccount.setAccountBalance(new BigDecimal(0.0));
        primaryAccount.setAccountNumber(++nextAccountNum);
        primaryAccount.setCurrency(currency);
        primaryAccount.setUser(userService.findByUsername(username));
        accountRepo.save(primaryAccount);
        return primaryAccount;
    }

    @Override
    public SavingAccount createSavingAccount(String username) {
        SavingAccount savingAccount = new SavingAccount();
        savingAccount.setAccountBalance(new BigDecimal(0.0));
        savingAccount.setAccountNumber(++nextAccountNum);
        savingAccount.setUser(userService.findByUsername(username));
        accountRepo.save(savingAccount);
        return savingAccount;
    }

    @Override
    public Account getAccount(long accountId, String username) {
        return accountRepo.findByIdAndUserUsername(accountId, username);
    }

    @Override
    public List<PrimaryAccount> getPrimaryAccounts(String username) {
        return primaryAccountRepo.findByUserUsernameOrderById(username);
    }

    @Override
    public List<SavingAccount> getSavingAccount(String username) {
        return savingAccountRepo.findByUserUsernameOrderById(username);
    }

    @Override
    public void saveAccount(Account account) {
        if (account instanceof PrimaryAccount) {
            accountRepo.save((PrimaryAccount) account);
        } else {
            accountRepo.save((SavingAccount) account);
        }
    }

    @Override
    @Transactional
    public void manageAccount(AccountController.FormCommand command, String username) {
        Account account = getAccount(command.getAccountId(), username);
        Calendar calendar = Calendar.getInstance();
        if(account == null) {
            throw new GeneralException();
        }
        if ("deposit".equals(command.getAction())) {
            account.setAccountBalance(account.getAccountBalance().add(command.getAmount()));
        }
        if ("withdraw".equals(command.getAction())) {
            testAccountBalance(command.getAmount(), account);
            account.setAccountBalance(account.getAccountBalance().subtract(command.getAmount()));
        }
        accountRepo.save(account);

        Transaction transaction = new Transaction(new Timestamp(calendar.getTime().getTime()),"",
                                                                        command.getAction(),
                                                                        "ok", command.getAmount().doubleValue(), account.getAccountBalance(), account);
        transactionRepo.save(transaction);

    }

    @Override
    @Transactional
    public void transferBetweenAccounts(String accountIdFrom, String accountIdTo, BigDecimal amount, String username) {
        Account accountFrom = null;
        Account accountTo = null;
        Calendar calendar = Calendar.getInstance();
        if (accountIdFrom != null && accountIdTo != null) {
            accountFrom = accountRepo.findOne(Long.valueOf(accountIdFrom));
            accountTo = accountRepo.findOne(Long.valueOf(accountIdTo));
        }
        if (accountFrom == null || accountTo == null || accountFrom.getId() == accountTo.getId()) {
            throw new GeneralException();
        }
        if (accountFrom.getAccountBalance().compareTo(amount) >= 0) {
            accountFrom.setAccountBalance(accountFrom.getAccountBalance().subtract(amount));
            BigDecimal transferAmount = exchangeRateService.exchange(accountFrom.getCurrency(), accountTo.getCurrency(), amount);
            accountTo.setAccountBalance(accountTo.getAccountBalance().add(transferAmount));
        } else {
            throw new NotEnoughAccountBalance();
        }

        Transaction transactionFrom = new Transaction(new Timestamp(calendar.getTime().getTime()),"",
                "transfer",
                "ok", amount.negate().doubleValue(), accountFrom.getAccountBalance(), accountFrom);
        Transaction transactionTo = new Transaction(new Timestamp(calendar.getTime().getTime()),"",
                "transfer",
                "ok", amount.doubleValue(), accountFrom.getAccountBalance(), accountTo);
        transactionRepo.save(transactionFrom);
        transactionRepo.save(transactionTo);
    }

    private void testAccountBalance(BigDecimal amount, Account account) {
        BigDecimal balance = account.getAccountBalance();
        if (balance.compareTo(amount) < 0) {
            throw new NotEnoughAccountBalance();
        }
    }
}
