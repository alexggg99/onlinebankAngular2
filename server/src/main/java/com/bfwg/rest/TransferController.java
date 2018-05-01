package com.bfwg.rest;

import com.bfwg.model.Account;
import com.bfwg.service.AccountService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
@Controller
@RequestMapping("/api/transfer")
@SessionAttributes("command")
public class TransferController extends AbstractController {

    @Autowired
    private AccountService accountService;

    @ModelAttribute("command")
    public FormCommand command(HttpSession httpSession) {
        return new FormCommand();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping(value = "/betweenAccounts")
    public ResponseEntity betweenAccounts(Model model, @Valid @RequestBody FormCommand command, Errors result) {
        if (result.hasErrors() || command.getAmount().intValue() < 0) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        accountService.transferBetweenAccounts(command.accountIdFrom, command.getAccountIdTo(), command.getAmount(), (String) model.asMap().get("username"));
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Data
    public static class FormCommand {
        private String accountIdFrom;
        private String accountIdTo;
        @NotNull
        private BigDecimal amount;
    }

}
