package com.example.day1.demo.job;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SampleJob {

    public void runJob() {
        // Tambahkan logika tugas yang akan dijadwalkan di sini
        System.out.println("Job is running...");
    }
}
