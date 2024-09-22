package org.ucsc.account_service.Repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.ucsc.account_service.Models.Account;

@Repository
public interface AccountRepository extends MongoRepository<Account, String> {
    Account findByPhoneNo(String phoneNo);
}
