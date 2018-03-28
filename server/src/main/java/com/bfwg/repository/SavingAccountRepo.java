package com.bfwg.repository;

import com.bfwg.model.SavingAccount;
import org.springframework.transaction.annotation.Transactional;


@Transactional
public interface SavingAccountRepo extends AccountBaseRepo<SavingAccount> {
}
