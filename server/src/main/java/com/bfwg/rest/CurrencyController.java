package com.bfwg.rest;

import com.bfwg.model.Currency;
import com.bfwg.model.PrimaryAccount;
import com.bfwg.model.User;
import com.bfwg.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping( value = "/api/currency", produces = MediaType.APPLICATION_JSON_VALUE )
public class CurrencyController {

    @Autowired
    private AccountService accountService;

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

}
