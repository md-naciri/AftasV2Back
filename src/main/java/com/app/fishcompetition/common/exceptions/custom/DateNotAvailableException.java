package com.app.fishcompetition.common.exceptions.custom;

import com.app.fishcompetition.common.responses.RequestResponseWithDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DateNotAvailableException extends Exception {

    private final RequestResponseWithDetails requetResponseWithDetails;
    public ResponseEntity<RequestResponseWithDetails> DateNotAvailableExceptionResponse() {
        Map<String,Object> response = new HashMap<>();
        requetResponseWithDetails.setTimestamp(LocalDateTime.now());
        requetResponseWithDetails.setMessage("Date is not available");
        requetResponseWithDetails.setStatus("400");
        response.put("Error", "you must choose a date that is at least 2 months from now");
        requetResponseWithDetails.setDetails(response);
        return ResponseEntity.badRequest().body(requetResponseWithDetails);
    }
}
