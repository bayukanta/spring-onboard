package com.example.day1.demo.service.customer;

import com.example.day1.demo.model.Customer;
import com.example.day1.demo.service.dto.CustomerRequest;

import java.util.List;
import java.util.concurrent.Future;

public interface CustomerService {
    List<Customer> getAllCustomers();

    Customer getCustomerById(String id);

    String createCustomer(CustomerRequest customer);

    Future<String> createFutureCustomer(CustomerRequest customer);

    void deleteCustomer(String id);

    void updateCustomer(String id, Customer customer);

    List<Customer> getCustomerByNumber(String number);
}