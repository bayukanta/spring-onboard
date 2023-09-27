package com.example.day1.demo.controller;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.day1.demo.model.Customer;
import com.example.day1.demo.service.dto.CustomerRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CustomerControllerTest {

    @LocalServerPort
    private int port;

    private String url;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testCreate() throws Exception {
        url = String.format("http://localhost:%d/api/customers", port);
        Customer request = new Customer("test number","test id", "test fname", "test lname", "test email", "test city");
        String expectedJson = new ObjectMapper()
                .writeValueAsString(new Customer("test number","test id", "test fname", "test lname", "test email", "test city"));
        String  result = this.restTemplate.postForObject(url, request, String.class);
        assertThat(result.equals(expectedJson));
    }

//    @Test
//    public void testCreateSuccess(){
//        url = String.format("http://localhost:%d/api/customers", port);
//        CustomerRequest request = CustomerRequest.builder()
//                .number("test number")
//                .firstName("test fname")
//                .lastName("test lname")
//                .email("test email")
//                .city("test city")
//                .build();
//        ResponseEntity<String> result = this.restTemplate.postForEntity(url, request, String.class);
//
//        Assertions.assertEquals(result.getStatusCode(), HttpStatus.OK);
//    }

    @Test
    public void testGetSuccess(){
        url = String.format("http://localhost:%d/api/customers", port);
        //Customer request = new Customer("test number","test id", "test fname", "test lname", "test email", "test city");
        ResponseEntity<String> result = this.restTemplate.getForEntity(url,  String.class);

        Assertions.assertEquals(result.getStatusCode(), HttpStatus.OK);
    }


    @Test
    public void testHello(){
        url = String.format("http://localhost:%d/api/customers/hello", port);
        assertThat(this.restTemplate.getForObject(url, String.class)).contains("Hello");
    }
}
