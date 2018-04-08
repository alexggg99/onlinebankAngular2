package com.bfwg.rest;

import com.bfwg.exception.ExceptionResponse;
import com.bfwg.exception.NotEnoughAccountBalance;
import com.bfwg.model.Account;
import com.bfwg.model.PrimaryAccount;
import com.bfwg.model.SavingAccount;
import com.bfwg.model.User;
import com.bfwg.model.*;
import lombok.Data;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
@RestController
@RequestMapping( value = "/api/account", produces = MediaType.APPLICATION_JSON_VALUE )
public class AccountController extends AbstractController {

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(BigDecimal.class, new CustomNumberEditor(BigDecimal.class, false));
    }

    @GetMapping
    public List<Account> getAllAccounts(Model model) {
        return accountService.getAllAccounts((String) model.asMap().get("username"));
    }

    @GetMapping(value = "/{id}")
    public Account getAccount(@PathVariable Long id, Model model) {
        return accountService.getAccount(id, (String) model.asMap().get("username"));
    }

    @PostMapping(value = "/primary")
    public Account createPrimaryAccounts(@RequestBody Currency currency) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return accountService.createPrimaryAccount(currency, user.getUsername());
    }

    @GetMapping(value = "/primary")
    public List<PrimaryAccount> getPrimaryAccounts(Model model) {
        return accountService.getPrimaryAccounts((String) model.asMap().get("username"));
    }

    @GetMapping(value = "/saving")
    public List<SavingAccount> getSavingAccounts(Model model) {
        return accountService.getSavingAccount((String) model.asMap().get("username"));
    }

    @PostMapping
    public ResponseEntity<Account> manageAccount(@RequestBody FormCommand formCommand, Model model) {
        if (formCommand.amount.intValue() > 0) {
            accountService.manageAccount(formCommand, (String) model.asMap().get("username"));
        }
        return new ResponseEntity<>(accountService.getAccount(formCommand.accountId, (String) model.asMap().get("username")), HttpStatus.OK);
    }

    @ExceptionHandler(NotEnoughAccountBalance.class)
    public ResponseEntity<ExceptionResponse> handleNotEnoughBalance(NotEnoughAccountBalance ex) {
        ExceptionResponse res = new ExceptionResponse();
        res.setErrorMessage("Not enough balance");
        return new ResponseEntity(res, HttpStatus.BAD_REQUEST);
    }

    @Data
    public static class FormCommand {
        private Long accountId;
        private BigDecimal amount;
        private String action;
    }

}
