package org.ucsc.account_service.Repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.ucsc.account_service.Models.Customer;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, String> {
    Customer findByNic(String nic);

    Customer findByEmail(String email);
}
