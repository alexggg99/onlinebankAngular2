package com.bfwg.rest;

import com.bfwg.model.Currency;
import com.bfwg.model.PrimaryAccount;
import com.bfwg.model.User;
import com.bfwg.service.AccountService;
import com.bfwg.service.ExchangeRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
@RestController
@RequestMapping( value = "/api/currency", produces = MediaType.APPLICATION_JSON_VALUE )
public class CurrencyController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private ExchangeRateService exchangeRateService;


    @RequestMapping
    public List<Currency> getCurrencies() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Currency> currHoldByUser = accountService.getAllAccounts(user.getUsername()).stream()
                .filter(elem -> elem instanceof PrimaryAccount)
                .map(elem -> ((PrimaryAccount) elem).getCurrency()).collect(Collectors.toList());
        List<Currency> filtered = Currency.stream()
                .filter(elem ->  !currHoldByUser.contains(elem))
                .collect(Collectors.toList());
        return filtered;
    }

    @RequestMapping(value = "/exchange/{currName1}/{currName2}/{amount}" )
    public BigDecimal exchange(@PathVariable String currName1, @PathVariable String currName2, @PathVariable String amount) {
        Currency curr1 = Currency.getEnum(currName1);
        return exchangeRateService.exchange(Currency.getEnum(currName1), Currency.getEnum(currName2), new BigDecimal(amount));
    }

}
