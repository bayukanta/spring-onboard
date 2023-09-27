package com.example.day1.demo.service.customer;


import com.example.day1.demo.service.dto.CustomerRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.day1.demo.model.Customer;
import com.example.day1.demo.repository.CustomerRepository;

import java.util.List;
import java.util.UUID;

@Service
public class CustomerServiceImpl implements CustomerService{
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<Customer> getAllCustomers() {
        return (List<Customer>) customerRepository.findAll();
    }

    @Override

    public Customer getCustomerById(String id) {
        return customerRepository.findById(id).orElse(null);
    }

    @Override
    public String createCustomer(CustomerRequest customer) {

        Customer tmp = Customer.builder()
                .id(UUID.randomUUID().toString())
                .number(customer.getNumber())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .email(customer.getEmail())
                .city(customer.getCity())
                .build();

        var result = customerRepository.save(tmp);
        return result.getId();
    }

    @Override

    public void deleteCustomer(String id) {
        customerRepository.deleteById(id);
    }

    @Override

    public void updateCustomer(String id, Customer customer) {
        // Check if the customer exists
        var editedCustomer = customerRepository.findById(id).orElse(null);

        // Update the customer's details
        editedCustomer.setNumber(customer.getNumber());
        editedCustomer.setFirstName(customer.getFirstName());
        editedCustomer.setLastName(customer.getLastName());
        editedCustomer.setCity(customer.getCity());
        editedCustomer.setEmail(customer.getEmail());
        customerRepository.save(editedCustomer);
    }

    @Override

    public List<Customer> getCustomerByNumber(String number){
        return customerRepository.findByNumber(number);
    }
}
