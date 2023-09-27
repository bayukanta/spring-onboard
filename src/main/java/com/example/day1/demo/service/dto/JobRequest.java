package com.example.day1.demo.service.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Data
@Builder
public class JobRequest {
    private LocalDateTime dateTime;
    private ZoneId timeZone;
    private String scheduledType;
}
