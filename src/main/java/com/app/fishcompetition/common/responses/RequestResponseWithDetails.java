package com.app.fishcompetition.common.responses;

import lombok.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@Component
public class RequestResponseWithDetails {
    private LocalDateTime timestamp;
    private String message;
    private String status;
    private Map<String,?> details;
}
