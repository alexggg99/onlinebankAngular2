package com.bfwg.rest;

import com.bfwg.model.User;
import com.bfwg.repository.TransactionRepo;
import com.bfwg.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
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
}
