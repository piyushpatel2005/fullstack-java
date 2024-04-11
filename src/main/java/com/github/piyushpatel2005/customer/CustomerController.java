package com.github.piyushpatel2005.customer;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/customers")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
//    @RequestMapping(value = "api/v1/customers", method = RequestMethod.GET)
    public List<Customer> getCustomers() {
        return customerService.getAllCustomers();
    }

    @RequestMapping(value = "{customerId}", method = RequestMethod.GET)
    public Customer getCustomers(@PathVariable("customerId") Integer id) {
        return customerService.getCustomer(id);
    }

    @PostMapping
    public void registerCustomer(@RequestBody CustomerRegistrationRequest request) {
        customerService.addCustomer(request);
    }

    @RequestMapping(value = "{customerId}", method = RequestMethod.DELETE)
    public void deleteCustomer(@PathVariable("customerId") Integer id) {
        customerService.deleteCustomer(id);
    }

    @RequestMapping(value = "{customerId}", method = RequestMethod.PUT)
    public void updateCustomer(@PathVariable("customerId") Integer id, @RequestBody CustomerUpdateRequest request) {
        customerService.updateCustomer(id, request);
    }
}
