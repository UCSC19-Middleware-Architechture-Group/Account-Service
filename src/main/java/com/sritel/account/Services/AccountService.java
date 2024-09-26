package com.sritel.account.Services;

import com.sritel.account.Repositories.AccountRepository;
import com.sritel.account.event.AccountEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import com.sritel.account.Models.Account;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountService {
    private final AccountRepository accountRepository;
    private final KafkaTemplate<String, AccountEvent> kafkaTemplate;

    public String addAccount(Account account) {
        if (account.getEmail() == null || account.getEmail().isEmpty()) {
            return "Customer Email is required";
        }
        if (account.getPhoneNo() == null) {
            return "Phone number is required";
        }
        if (accountRepository.findByPhoneNo(account.getPhoneNo()) != null) {
            return "Account already exists";
        }
        account.setCreatedDate(LocalDate.now().toString());
        account.setUpdatedDate(LocalDate.now().toString());
        account.setStatus("active");
        accountRepository.save(account);

        AccountEvent accountEvent = new AccountEvent();
        accountEvent.setId(account.getId());
        accountEvent.setEmail(account.getEmail());
        accountEvent.setPhoneNo(account.getPhoneNo());
        accountEvent.setCreatedDate(LocalDate.now().toString());

        log.info("Start - Sending CreateAccountEvent {} to kafka topic create-account", accountEvent);
        kafkaTemplate.send("create-account", accountEvent);
        log.info("End - Sending CreateAccountEvent {} to kafka topic create-account", accountEvent);

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
        if (existingAccount == null) {
            return "Account not found";
        }

        if (account.getPhoneNo() != null) {
            existingAccount.setPhoneNo(account.getPhoneNo());
        }
        if (account.getStatus() != null) {
            existingAccount.setStatus(account.getStatus());
        }
        if (account.getEmail() != null) {
            existingAccount.setEmail(account.getEmail());
        }
        existingAccount.setUpdatedDate(LocalDate.now().toString());
        accountRepository.save(existingAccount);
        return "Account updated successfully";
    }

    public String deleteAccount(String id) {
        Account account = accountRepository.findById(id).orElse(null);
        if (account == null) {
            return "Account not found";
        }
        accountRepository.delete(account);

        AccountEvent accountEvent = new AccountEvent();
        accountEvent.setId(account.getId());
        accountEvent.setEmail(account.getEmail());
        accountEvent.setPhoneNo(account.getPhoneNo());
        accountEvent.setCreatedDate(LocalDate.now().toString());

        log.info("Start - Sending DeleteAccountEvent {} to kafka topic delete-account", accountEvent);
        kafkaTemplate.send("delete-account", accountEvent);
        log.info("End - Sending DeleteAccountEvent {} to kafka topic delete-account", accountEvent);

        return "Account deleted successfully";
    }

    public List<Account> getAccountList(String customerId) {
        return accountRepository.findByEmail(customerId);
    }

    public boolean isAccountExists(String phoneNo) {
        return accountRepository.existsByPhoneNo(phoneNo);
    }
}
