package org.ucsc.account_service.Services;

import org.springframework.stereotype.Service;
import org.ucsc.account_service.Models.Customer;
import org.ucsc.account_service.Repositories.CustomerRepository;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public String addCustomer(Customer customer) {
        if(customer.getName() == null){
            return "Name is required";
        }
        if(customer.getNic() == null){
            return "NIC is required";
        }
        if(customer.getEmail() == null){
            return "Email is required";
        }
        if(customer.getAddress() == null){
            return "Address is required";
        }
        if(customer.getUserId() == null){
            return "User ID is required";
        }
        if(customer.getDob() == null){
            return "Date of Birth is required";
        }
        if(customerRepository.findByNic(customer.getNic()) != null) {
            return "Customer already exists";
        }
        if(customerRepository.findByEmail(customer.getEmail()) != null) {
            return "Email already exists";
        }
        customerRepository.save(customer);
        return "Customer added successfully";
    }
}
