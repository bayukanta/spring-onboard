package com.example.day1.demo.controller;


import com.example.day1.demo.service.dto.CustomerRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.day1.demo.model.Customer;
import com.example.day1.demo.service.customer.CustomerService;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable String id) {
        return customerService.getCustomerById(id);
    }

    @PostMapping
    public ResponseEntity<Object> createCustomer(@RequestBody CustomerRequest customer) {
        var id = customerService.createCustomer(customer);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable String id) {
        customerService.deleteCustomer(id);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(
            @PathVariable String id,
            @RequestBody Customer customer) {
        customerService.updateCustomer(id, customer);
        return ResponseEntity.ok(null);
    }
    @RequestMapping("/hello")
    public String helloWorld() {
        // Just return the page name
        // No Path, no extension
        return "Hello";
    }
}
