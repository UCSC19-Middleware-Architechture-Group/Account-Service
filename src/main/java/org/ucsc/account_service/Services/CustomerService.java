package org.ucsc.account_service.Services;

import org.springframework.stereotype.Service;
import org.ucsc.account_service.Models.Customer;
import org.ucsc.account_service.Repositories.CustomerRepository;

import java.util.List;

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

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomer(String id) {
        return customerRepository.findById(id).orElse(null);
    }

    public String updateCustomer(Customer customer) {
        Customer existingCustomer = customerRepository.findById(customer.getId()).orElse(null);
        if(existingCustomer == null) {
            return "Customer not found";
        }

        if(customer.getName() != null) {
            existingCustomer.setName(customer.getName());
        }
        if(customer.getNic() != null) {
            existingCustomer.setNic(customer.getNic());
        }
        if(customer.getEmail() != null) {
            existingCustomer.setEmail(customer.getEmail());
        }
        if(customer.getAddress() != null) {
            existingCustomer.setAddress(customer.getAddress());
        }
        if(customer.getUserId() != null) {
            existingCustomer.setUserId(customer.getUserId());
        }
        if(customer.getDob() != null) {
            existingCustomer.setDob(customer.getDob());
        }
        customerRepository.save(existingCustomer);
        return "Customer updated successfully";
    }

    public String deleteCustomer(String id) {
        Customer existingCustomer = customerRepository.findById(id).orElse(null);
        if(existingCustomer == null) {
            return "Customer not found";
        }
        customerRepository.deleteById(id);
        return "Customer deleted successfully";
    }
}
