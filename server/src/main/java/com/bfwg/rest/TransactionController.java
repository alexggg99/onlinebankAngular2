package com.bfwg.rest;

import com.bfwg.model.Account;
import com.bfwg.model.Transaction;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
@RestController
@RequestMapping( value = "/api/transaction", produces = MediaType.APPLICATION_JSON_VALUE )
public class TransactionController extends AbstractController {

    @Value("${app.itemsPerPage}")
    private int ITEMSPERPAGE;

    @GetMapping("/{accountId}")
    public List<Transaction> getTransactions(@PathVariable Long accountId,
                                             @RequestParam("sort") String sort,
                                             @RequestParam("order") String order,
                                             @RequestParam("page") int page,
                                             Model model) {
        Account account = accountService.getAccount(accountId, (String) model.asMap().get("username"));
        return transactionRepo.findByAccountAndAccountUserUsername(account, (String) model.asMap().get("username"), new PageRequest(page, ITEMSPERPAGE, Sort.Direction.fromString(order), sort));
    }

    @GetMapping("/size/{accountId}")
    public int getTransactionsSize(@PathVariable Long accountId, Model model) {
        Account account = accountService.getAccount(accountId, (String) model.asMap().get("username"));
        return transactionRepo.countByAccount(account);
    }

}
