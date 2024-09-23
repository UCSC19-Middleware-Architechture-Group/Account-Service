package com.sritel.account.Repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.sritel.account.Models.Customer;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, String> {
    Customer findByNic(String nic);

    Customer findByEmail(String email);
}
