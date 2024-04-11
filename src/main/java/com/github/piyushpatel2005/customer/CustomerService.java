package com.github.piyushpatel2005.customer;

import com.github.piyushpatel2005.exception.DuplicateResourceException;
import com.github.piyushpatel2005.exception.RequestValidationException;
import com.github.piyushpatel2005.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private final CustomerDao customerDao;

    public CustomerService(@Qualifier("jpa") CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    public List<Customer> getAllCustomers() {
        return customerDao.selectAllCustomers();
    }

    public Customer getCustomer(Integer id) {
        return customerDao.selectCustomerById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Customer %s not found".formatted(id))
                );
    }

    public void addCustomer(CustomerRegistrationRequest customer) {
        // check if email is exists
        if (customerDao.existsPersonWithEamil(customer.email())) {
            throw new DuplicateResourceException(
                    "Email already taken"
            );
        }
        Customer incomingCustomer = new Customer(
                customer.name(),
                customer.email(),
                customer.age()
        );
        customerDao.insertCustomer(
                incomingCustomer
        );
    }

    public void deleteCustomer(Integer id) {
        if (!customerDao.existsCustomerById(id)) {
            throw new ResourceNotFoundException("Customer with id [%d] not found".formatted(id));
        }
        customerDao.deleteCustomerById(id);
    }

    public void updateCustomer(Integer customerId, CustomerUpdateRequest request) {
        Customer customer = this.getCustomer(customerId);
        boolean changes = false;

        if (request.name() != null && !request.name().equals(customer.getName())) {
            customer.setName(request.name());
            changes = true;
        }

        if (request.email() != null && !request.email().equals(customer.getEmail())) {
            if (customerDao.existsPersonWithEamil(request.email())) {
                throw new DuplicateResourceException(
                        "Email already taken"
                );
            }
            customer.setEmail(request.email());
            changes = true;
        }

        if (request.age() != null && !request.age().equals(customer.getAge())) {
            customer.setAge(request.age());
            changes = true;
        }

        if (!changes) {
            throw new RequestValidationException("No changes detected");
        }

        customerDao.updateCustomer(customer);
    }
}
