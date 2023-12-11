package com.app.fishcompetition.common.exceptions.custom;

import com.app.fishcompetition.common.responses.RequestResponseWithDetails;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class DateNotAvailableException extends RuntimeException {
   public DateNotAvailableException( String message) {
        super(message);
    }

}
