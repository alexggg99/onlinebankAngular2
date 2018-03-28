package com.bfwg.repository;

import com.bfwg.model.PrimaryAccount;
import org.springframework.transaction.annotation.Transactional;


@Transactional
public interface PrimaryAccountRepo extends AccountBaseRepo<PrimaryAccount> {
}
