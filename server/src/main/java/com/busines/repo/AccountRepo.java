package com.busines.repo;

import com.busines.model.accounts.Account;
import org.springframework.transaction.annotation.Transactional;


@Transactional
public interface AccountRepo extends AccountBaseRepo<Account> {
}
