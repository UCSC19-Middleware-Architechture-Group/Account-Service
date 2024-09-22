package org.ucsc.account_service.Repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.ucsc.account_service.Models.Account;

import java.util.List;

@Repository
public interface AccountRepository extends MongoRepository<Account, String> {
    Account findByPhoneNo(String phoneNo);

    List<Account> findByCustomerId(String customerId);
}
