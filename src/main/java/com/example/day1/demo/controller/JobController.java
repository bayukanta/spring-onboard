package com.example.day1.demo.controller;

import com.example.day1.demo.job.JobCreateCustomer;
import com.example.day1.demo.service.dto.JobCustomerRequest;
import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("/api/customers")
public class JobController {

    @Autowired
    private  Scheduler scheduler;


    @PostMapping("/scheduleCreate")
    public String scheduleCreate(@Valid @RequestBody JobCustomerRequest jobCustomerRequest) throws SchedulerException {


        ZonedDateTime dateTime = null;
        JobDetail jobDetail = null;
        Trigger trigger = null;

        if (jobCustomerRequest.getJob().getScheduledType().equals("ONCE")){
            dateTime = ZonedDateTime.of(jobCustomerRequest.getJob().getDateTime(),
                    jobCustomerRequest.getJob().getTimeZone());
            if(dateTime.isBefore(ZonedDateTime.now())){
                return("Datetime must be after current time");
            }
        }


        jobDetail = buildJobDetail(jobCustomerRequest);
        trigger = buildJobTrigger(jobDetail,jobCustomerRequest.getJob().getScheduledType(), dateTime);
        scheduler.scheduleJob(jobDetail, trigger);
        System.out.println("In Controller");

        return "Success";

    }

    private JobDetail buildJobDetail(JobCustomerRequest jobCustomerRequest) {
        JobDataMap jobDataMap = new JobDataMap();

        jobDataMap.put("number", jobCustomerRequest.getNumber());
        jobDataMap.put("firstName", jobCustomerRequest.getFirstName());
        jobDataMap.put("lastName", jobCustomerRequest.getLastName());
        jobDataMap.put("email", jobCustomerRequest.getEmail());
        jobDataMap.put("city", jobCustomerRequest.getCity());
        System.out.println("In JobDetail");
        return JobBuilder.newJob(JobCreateCustomer.class)
                .withIdentity(UUID.randomUUID().toString(), "customer-jobs")
                .withDescription("Create Customer Job")
                .usingJobData(jobDataMap)
                .storeDurably()
                .build();
    }

    private Trigger buildJobTrigger(JobDetail jobDetail,String schedulerType, ZonedDateTime startAt) {
        Trigger trigger = null;

        if( schedulerType.equals("ONCE")){
            trigger = TriggerBuilder.newTrigger()
                    .forJob(jobDetail)
                    .withIdentity(jobDetail.getKey().getName(), "customer-trigger-once")
                    .withDescription("Create Customer Trigger Once")
                    .startAt(Date.from(startAt.toInstant()))
                    .withSchedule(SimpleScheduleBuilder.simpleSchedule().withMisfireHandlingInstructionFireNow())
                    .build();
        }
        else {
            trigger = TriggerBuilder.newTrigger()
                    .forJob(jobDetail)
                    .withIdentity(jobDetail.getKey().getName(), "customer-trigger-repeat")
                    .withDescription("Create Customer Trigger Repeat")
                    .startNow()
                    .withSchedule(SimpleScheduleBuilder.simpleSchedule().withMisfireHandlingInstructionFireNow())
                    .build();
        }
        System.out.println("In Trigger");
        return trigger;

    }

}
