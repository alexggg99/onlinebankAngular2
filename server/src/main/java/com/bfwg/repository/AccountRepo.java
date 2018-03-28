package com.bfwg.repository;

import com.bfwg.model.Account;
import org.springframework.transaction.annotation.Transactional;


@Transactional
public interface AccountRepo extends AccountBaseRepo<Account> {
}
