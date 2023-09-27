package com.example.day1.demo.job;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class SampleJobQuartz implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        // Tambahkan logika tugas yang akan dijadwalkan di sini
        System.out.println("Job is running with quartz...");
    }
}