package com.bfwg.rest.common;

import com.bfwg.model.Account;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class DTOaccount extends Account {
    private String type;

    public DTOaccount(long id, String type, BigDecimal accountBalance, int accountNumber, String cur) {
        this.setId(id);
        this.setAccountBalance(accountBalance);
        this.setAccountNumber(accountNumber);
        this.type = type;
//        this.currency = cur;
    }
}