package com.app.fishcompetition.common.responses;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@Component
public class RequestResponse {
    private LocalDateTime timestamp;
    private String message;
    private String status;
    private Map<String,Object> details;
}
