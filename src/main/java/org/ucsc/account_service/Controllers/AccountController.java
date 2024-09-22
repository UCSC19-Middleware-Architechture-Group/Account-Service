package org.ucsc.account_service.Controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.ucsc.account_service.Models.Account;
import org.ucsc.account_service.Services.AccountService;

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

}
