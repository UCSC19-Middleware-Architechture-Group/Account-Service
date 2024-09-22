package org.ucsc.account_service.Controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.ucsc.account_service.Models.Customer;
import org.ucsc.account_service.Services.CustomerService;

@RestController
@CrossOrigin
@RequestMapping(path = "api/Account_Service/customer")
@AllArgsConstructor
public class CustomerController {
    private final CustomerService customerService;
    @PostMapping("/add")
    public String addCustomer(@RequestBody Customer customer) {
        return customerService.addCustomer(customer);
    }
}
