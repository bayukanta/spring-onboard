package com.example.day1.demo.job;

import com.example.day1.demo.service.customer.CustomerService;
import com.example.day1.demo.service.dto.CustomerRequest;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class JobCreateCustomer implements Job {

    @Autowired
    CustomerService customerService;


    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        String date = new SimpleDateFormat("yyyyMMdd_hh:mm").format(new Date());
        CustomerRequest tmp = CustomerRequest.builder()
                .number(String.format("test number %s", date))
                .firstName(String.format("test fname %s", date))
                .lastName(String.format("test lname %s", date))
                .email(String.format("test email %s", date))
                .city(String.format("test city %s", date))
                .build();
        System.out.println("In Job");
        customerService.createCustomer(tmp);
    }
}
