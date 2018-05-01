package com.bfwg.rest;

import com.bfwg.exception.ExceptionResponse;
import com.bfwg.exception.NotEnoughAccountBalance;
import com.bfwg.model.User;
import com.bfwg.repository.TransactionRepo;
import com.bfwg.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;

public abstract class AbstractController {

    @Autowired
    protected AccountService accountService;

    @Autowired
    protected TransactionRepo transactionRepo;

    @ModelAttribute("username")
    public String getUsername() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user != null ? user.getUsername() : null;
    }

    @ExceptionHandler(NotEnoughAccountBalance.class)
    public ResponseEntity<ExceptionResponse> handleNotEnoughBalance(NotEnoughAccountBalance ex) {
        ExceptionResponse res = new ExceptionResponse();
        res.setErrorMessage("Not enough balance");
        return new ResponseEntity(res, HttpStatus.BAD_REQUEST);
    }
}
