package com.app.fishcompetition.common.responses;

import java.time.LocalDateTime;

import lombok.Data;
import org.springframework.stereotype.Component;
@Data
@Component
public class RequestResponseWithoutDetails {
    private LocalDateTime timestamp;
    private String message;
    private String status;
}
