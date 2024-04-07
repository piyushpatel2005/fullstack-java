package com.github.piyushpatel2005.customer;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("api/v1/customers")
//    @RequestMapping(value = "api/v1/customers", method = RequestMethod.GET)
    public List<Customer> getCustomers() {
        return customerService.getAllCustomers();
    }

    @RequestMapping(value = "api/v1/customers/{customerId}", method = RequestMethod.GET)
    public Customer getCustomers(@PathVariable("customerId") Integer id) {
        return customerService.getCustomer(id);
    }
}
