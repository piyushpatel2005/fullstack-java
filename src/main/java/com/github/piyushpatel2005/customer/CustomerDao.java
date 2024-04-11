package com.github.piyushpatel2005.customer;

import java.util.List;
import java.util.Optional;

public interface CustomerDao {
    List<Customer> selectAllCustomers();
    Optional<Customer> selectCustomerById(Integer id);
    void insertCustomer(Customer customer);
    boolean existsPersonWithEamil(String email);
    boolean existsCustomerById(Integer id);
    void deleteCustomerById(Integer id);
    void updateCustomer(Customer update);
}
