package com.example.day1.demo.service.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JobCustomerRequest {

    private String number;

    private String firstName;

    private String lastName;

    private String email;

    private String city;

    private JobRequest job;
}
