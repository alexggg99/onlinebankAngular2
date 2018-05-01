package com.bfwg.service.impl;

import com.bfwg.model.Currency;
import com.bfwg.service.ExchangeRateService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by agashchuk on 29.04.2018
 */

@Service
public class ExchangeRateServiceImpl implements ExchangeRateService {

  @Override
  public BigDecimal exchange(Currency curr1, Currency curr2, BigDecimal amount) {
    if (curr1.getName().equals("EUR") && curr2.getName().equals("RUR")) {
      return amount.multiply(BigDecimal.valueOf(81));
    }
    if (curr1.getName().equals("RUR") && curr2.getName().equals("EUR")) {
      return amount.divide(BigDecimal.valueOf(81), RoundingMode.HALF_EVEN);
    }
    if (curr1.getName().equals("USD") && curr2.getName().equals("RUR")) {
      return amount.multiply(BigDecimal.valueOf(64));
    }
    if (curr1.getName().equals("RUR") && curr2.getName().equals("USD")) {
      return amount.divide(BigDecimal.valueOf(64), RoundingMode.HALF_EVEN);
    }
    if (curr1.getName().equals("EUR") && curr2.getName().equals("USD")) {
      return amount.multiply(BigDecimal.valueOf(1.24));
    }
    if (curr1.getName().equals("USD") && curr2.getName().equals("EUR")) {
      return amount.divide(BigDecimal.valueOf(1.24), RoundingMode.HALF_EVEN);
    }
    return BigDecimal.valueOf(0);
  }
}
