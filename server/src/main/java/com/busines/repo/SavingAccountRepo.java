package com.busines.repo;

import com.busines.model.accounts.SavingAccount;
import org.springframework.transaction.annotation.Transactional;


@Transactional
public interface SavingAccountRepo extends AccountBaseRepo<SavingAccount> {
}
