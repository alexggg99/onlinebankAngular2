package com.bfwg.rest;

import com.bfwg.model.Account;
import com.bfwg.model.PrimaryAccount;
import com.bfwg.model.SavingAccount;
import com.bfwg.model.User;
import com.bfwg.model.*;
import com.bfwg.repository.TransactionRepo;
import com.bfwg.service.AccountService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
@RestController
@RequestMapping( value = "/api", produces = MediaType.APPLICATION_JSON_VALUE )
public class AccountController  {

    @Autowired
    private AccountService accountService;

    @Autowired
    private TransactionRepo transactionRepo;

//    @Value("${app.itemsPerPage}")
//    private int itemsPerPage;

    @ModelAttribute("username")
    public String getUsername() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user != null ? user.getUsername() : null;
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(BigDecimal.class, new CustomNumberEditor(BigDecimal.class, false));
    }

    @GetMapping(value = "/account")
    public List<Account> getAllAccounts(Model model) {
        return accountService.getAllAccounts((String) model.asMap().get("username"));
    }
    @PostMapping(value = "/account/primary")
    public Account createPrimaryAccounts(@RequestBody Currency currency) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return accountService.createPrimaryAccount(currency, user.getUsername());
    }

    @GetMapping(value = "/account/primary")
    public List<PrimaryAccount> getPrimaryAccounts(Model model) {
        return accountService.getPrimaryAccounts((String) model.asMap().get("username"));
    }

    @GetMapping(value = "/account/saving")
    public List<SavingAccount> getSavingAccounts(Model model) {
        return accountService.getSavingAccount((String) model.asMap().get("username"));
    }

    @PostMapping(value = "/account")
    public ResponseEntity<Account> depositAccount(@RequestBody FormCommand formCommand, Model model) {
        accountService.manageAccount("deposit", formCommand, (String) model.asMap().get("username"));
        return new ResponseEntity<>(accountService.getAccount(formCommand.accountId, (String) model.asMap().get("username")), HttpStatus.OK);
    }

    @Data
    public static class FormCommand {
        private Long accountId;
        private BigDecimal amount;
    }

}
