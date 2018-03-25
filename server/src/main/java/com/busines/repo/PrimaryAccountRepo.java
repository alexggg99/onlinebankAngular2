package com.busines.repo;

import com.busines.model.accounts.PrimaryAccount;
import org.springframework.transaction.annotation.Transactional;


@Transactional
public interface PrimaryAccountRepo extends AccountBaseRepo<PrimaryAccount> {
}
