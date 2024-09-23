package com.sritel.account.Services;

import com.sritel.account.Repositories.AccountRepository;
import com.sritel.account.Repositories.CustomerRepository;
import org.springframework.stereotype.Service;
import com.sritel.account.Models.Account;

import java.time.LocalDate;
import java.util.List;

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
        account.setCreatedDate(LocalDate.now().toString());
        account.setUpdatedDate(LocalDate.now().toString());
        account.setStatus("active");
        accountRepository.save(account);
        return "Account added successfully";
    }

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Account getAccount(String id) {
        return accountRepository.findById(id).orElse(null);
    }

    public String updateAccount(Account account) {
        Account existingAccount = accountRepository.findById(account.getId()).orElse(null);
        if(existingAccount == null) {
            return "Account not found";
        }

        if(account.getPhoneNo() != null) {
            existingAccount.setPhoneNo(account.getPhoneNo());
        }
        if(account.getStatus() != null) {
            existingAccount.setStatus(account.getStatus());
        }
        if(account.getCustomerId() != null) {
            existingAccount.setCustomerId(account.getCustomerId());
        }
        existingAccount.setUpdatedDate(LocalDate.now().toString());
        accountRepository.save(existingAccount);
        return "Account updated successfully";
    }

    public String deleteAccount(String id) {
        Account account = accountRepository.findById(id).orElse(null);
        if(account == null) {
            return "Account not found";
        }
        accountRepository.delete(account);
        return "Account deleted successfully";
    }

    public List<Account> getAccountList(String customerId) {
        return accountRepository.findByCustomerId(customerId);
    }
}
