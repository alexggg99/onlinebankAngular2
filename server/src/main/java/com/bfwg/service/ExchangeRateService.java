package com.bfwg.service;

import com.bfwg.model.Currency;

import java.math.BigDecimal;

/**
 * Created by agashchuk on 29.04.2018
 */
public interface ExchangeRateService {
  BigDecimal exchange(Currency curr1, Currency curr2, BigDecimal amount);
}
