package org.ucsc.account_service.Services;

import org.springframework.stereotype.Service;
import org.ucsc.account_service.Models.Account;
import org.ucsc.account_service.Repositories.AccountRepository;
import org.ucsc.account_service.Repositories.CustomerRepository;

@Service
public class AccountService {
    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;

    public AccountService(AccountRepository accountRepository, CustomerRepository customerRepository) {
        this.accountRepository = accountRepository;
        this.customerRepository = customerRepository;
    }

    public String addAccount(Account account) {
        if(account.getCustomerId() == null || account.getCustomerId().isEmpty()) {
            return "Customer ID is required";
        }
        if(account.getPhoneNo() == null){
            return "Phone number is required";
        }
        if(customerRepository.findById(account.getCustomerId()).orElse(null) == null) {
            return "Customer not found";
        }
        if(accountRepository.findByPhoneNo(account.getPhoneNo()) != null) {
            return "Account already exists";
        }
        accountRepository.save(account);
        return "Account added successfully";
    }
}
