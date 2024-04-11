package com.github.piyushpatel2005.customer;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository("list")
public class CustomerListDataAccessService implements CustomerDao {
    private static List<Customer> customers;

    static {
        customers = new ArrayList<>();
        Customer alice = new Customer(1, "Alice", "alice@gmail.com", 23);
        Customer jamila = new Customer(2, "Jamila", "jamila@microsoft.com", 21);
        customers.add(alice);
        customers.add(jamila);
    }

    @Override
    public List<Customer> selectAllCustomers() {
        return customers;
    }

    @Override
    public Optional<Customer> selectCustomerById(Integer id) {
        return customers.stream()
                .filter(customer -> customer.getId().equals(id))
                .findFirst();
    }

    @Override
    public void insertCustomer(Customer customer) {
        customers.add(customer);
    }

    @Override
    public boolean existsPersonWithEamil(String email) {
        return customers.stream()
                .anyMatch(customer -> customer.getEmail().equals(email));
    }

    @Override
    public boolean existsCustomerById(Integer id) {
        return customers.stream()
                .anyMatch(customer -> customer.getId().equals(id));
    }

    @Override
    public void deleteCustomerById(Integer id) {
        customers.removeIf(customer -> customer.getId().equals(id));
    }

    @Override
    public void updateCustomer(Customer update) {
        customers.stream()
                .filter(customer -> customer.getId().equals(update.getId()))
                .findFirst()
                .ifPresent(customer -> {
                    customer.setName(update.getName());
                    customer.setEmail(update.getEmail());
                    customer.setAge(update.getAge());
                });
    }
}
