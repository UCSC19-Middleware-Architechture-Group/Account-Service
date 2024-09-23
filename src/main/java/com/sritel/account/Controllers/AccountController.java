package com.sritel.account.Controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.sritel.account.Models.Account;
import com.sritel.account.Services.AccountService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path = "api/Account_Service/account")
@AllArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @PostMapping("/add")
    public String addAccount(@RequestBody Account account) {
        return accountService.addAccount(account);
    }

    @GetMapping("/allAccounts")
    public List<Account> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    @GetMapping("/account/{id}")
    public Account getAccount(@PathVariable String id) {
        return accountService.getAccount(id);
    }

    @PutMapping("/update")
    public String updateAccount(@RequestBody Account account) {
        return accountService.updateAccount(account);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteAccount(@PathVariable String id) {
        return accountService.deleteAccount(id);
    }

    @GetMapping("/account_list/{id}")
    public List<Account> getAccountList(@PathVariable String id) {
        return accountService.getAccountList(id);
    }



}
