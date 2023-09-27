package com.example.day1.demo.controller;


import com.example.day1.demo.service.dto.CustomerRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.day1.demo.model.Customer;
import com.example.day1.demo.service.customer.CustomerService;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

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
        var cust = customerService.createCustomer(customer);
        return ResponseEntity.ok(cust);
    }
    @PostMapping("/future-create")
    public ResponseEntity<Object> createFutureCustomer(@RequestBody CustomerRequest customer) throws ExecutionException, InterruptedException {
        Future<String> future = customerService.createFutureCustomer(customer);
        while(!future.isDone()){
            System.out.println("Waiting...");
        }
        
        var cust = future.get();
        return ResponseEntity.ok(cust);
    }

    @PostMapping("/future-create2")
    public ResponseEntity<Object> createFutureCustomer2(@RequestBody CustomerRequest customer, @RequestBody CustomerRequest customer2) throws ExecutionException, InterruptedException {
        Future<String> future = customerService.createFutureCustomer(customer);
        Future<String> future2 = customerService.createFutureCustomer(customer2);
        while(!future.isDone()){
            System.out.println("Waiting...");
        }

        var cust = future.get();
        return ResponseEntity.ok(cust);
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
    @GetMapping("/hello")
    public String helloWorld() {
        // Just return the page name
        // No Path, no extension
        return "Hello";
    }
}
