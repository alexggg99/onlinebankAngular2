package com.bfwg.repository;

import com.bfwg.model.Account;
import com.bfwg.model.Transaction;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface TransactionRepo extends PagingAndSortingRepository<Transaction, Long>, JpaSpecificationExecutor<Transaction> {
    List<Transaction> findByAccountAndAccountUserUsername(Account account, String username, Pageable pageRequest);
}
