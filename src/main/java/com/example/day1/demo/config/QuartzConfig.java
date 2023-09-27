package com.example.day1.demo.config;
import com.example.day1.demo.job.SampleJobQuartz;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfig {

    @Bean
    public JobDetail sampleJobDetail() {
        System.out.println("tes1");
        return JobBuilder.newJob(SampleJobQuartz.class)
                .withIdentity("sampleJobQuartz")
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger sampleJobTrigger() {
        System.out.println("tes2");
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(10) // Atur interval eksekusi job (contoh: 10 detik)
                .repeatForever();

        return TriggerBuilder.newTrigger()
                .forJob(sampleJobDetail())
                .withIdentity("sampleTrigger")
                .startNow()
                .withSchedule(CronScheduleBuilder.cronSchedule("0/10 * * * * ?"))
                .build();
    }
}

