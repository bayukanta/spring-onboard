package com.example.day1.demo.repository;

import static org.assertj.core.api.Assertions.assertThat;
import com.example.day1.demo.model.Customer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.TestPropertySource;


import java.util.List;

@DataJpaTest
@TestPropertySource(locations = "classpath:application-test.properties")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CustomerRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CustomerRepository customerRepository;

    @BeforeAll
    public void init(){
        Customer customer = new Customer("test id", "test number", "test fname", "test Lname", "test email", "test city");
        entityManager.persist(customer);
        entityManager.flush();
    }

    @Test
    public void test_findByNumber(){
        List<Customer> found = customerRepository.findByNumber("test id");

        Customer result = null;
        if(!found.isEmpty()){
            result = found.get(0);
        }

        assertThat(result.getNumber().equals("test number"));

    }
}
