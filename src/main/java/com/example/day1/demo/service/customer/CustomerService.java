package com.example.day1.demo.service.customer;

import com.example.day1.demo.model.Customer;
import com.example.day1.demo.service.dto.CustomerRequest;

import java.util.List;

public interface CustomerService {
    List<Customer> getAllCustomers();

    Customer getCustomerById(String id);

    String createCustomer(CustomerRequest customer);

    void deleteCustomer(String id);

    void updateCustomer(String id, Customer customer);

    List<Customer> getCustomerByNumber(String number);
}