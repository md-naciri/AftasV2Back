package com.app.fishcompetition.common.responses;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Setter
@Component
public class RequestResponse {
    private LocalDateTime timestamp;
    private String message;
    private String status;
    private Map<String,Object> details;
}
