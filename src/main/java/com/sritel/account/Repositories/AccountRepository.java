package com.sritel.account.Repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.sritel.account.Models.Account;

import java.util.List;

@Repository
public interface AccountRepository extends MongoRepository<Account, String> {
    Account findByPhoneNo(String phoneNo);

    List<Account> findByEmail(String customerId);

    boolean existsByPhoneNo(String phoneNo);
}
