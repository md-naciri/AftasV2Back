package com.app.fishcompetition.common.exceptions;

import com.app.fishcompetition.common.exceptions.custom.CompetitionDateException;
import com.app.fishcompetition.common.exceptions.custom.DateNotAvailableException;
import com.app.fishcompetition.common.responses.RequestResponseWithDetails;
import com.app.fishcompetition.common.responses.RequestResponseWithoutDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@RestControllerAdvice
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GlobalExceptionHandler {

    private final RequestResponseWithDetails requestResponseWithDetails;
    private final RequestResponseWithoutDetails  requestResponseWithoutDetails;
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<RequestResponseWithDetails> handleValidationExceptions(MethodArgumentNotValidException notValidException ) {
        requestResponseWithDetails.setTimestamp(LocalDateTime.now());
        requestResponseWithDetails.setMessage("Validation error");
        requestResponseWithDetails.setStatus("422");
        Map<String,Object> errors = notValidException.getBindingResult().getFieldErrors().stream()
                .collect(
                        () -> new java.util.HashMap<>(),
                        (map, fieldError) -> map.put(fieldError.getField(), fieldError.getDefaultMessage()),
                        (map, map2) -> map.putAll(map2)
                );
        requestResponseWithDetails.setDetails(errors);
        return ResponseEntity.badRequest().body(requestResponseWithDetails);
    }
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<RequestResponseWithDetails> handleDataIntegrityViolationException(DataIntegrityViolationException dataIntegrityViolationException) {
        requestResponseWithDetails.setTimestamp(LocalDateTime.now());
        requestResponseWithDetails.setMessage("Data integrity violation");
        requestResponseWithDetails.setStatus("409");
        Map<String,Object> errors = new HashMap<>();
        errors.put("duplicate key",dataIntegrityViolationException.getMessage());
        requestResponseWithDetails.setDetails(errors);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(requestResponseWithDetails);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<RequestResponseWithDetails> handleIllegalArgumentException(IllegalArgumentException illegalArgumentException) {
        requestResponseWithDetails.setTimestamp(LocalDateTime.now());
        requestResponseWithDetails.setMessage("Illegal argument");
        requestResponseWithDetails.setStatus("400");
        Map<String,Object> errors = new HashMap<>();
        errors.put("Error", illegalArgumentException.getMessage());
        requestResponseWithDetails.setDetails(errors);
        return ResponseEntity.badRequest().body(requestResponseWithDetails);
    }
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<RequestResponseWithDetails> handleNoSuchElementException(NoSuchElementException noSuchElementException) {
        requestResponseWithDetails.setTimestamp(LocalDateTime.now());
        requestResponseWithDetails.setMessage("No such element");
        requestResponseWithDetails.setStatus("404");
        Map<String,Object> errors = new HashMap<>();
        errors.put("Error", noSuchElementException.getMessage());
        requestResponseWithDetails.setDetails(errors);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(requestResponseWithDetails);
    }
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<RequestResponseWithoutDetails> customHandleNotReadable(HttpMessageNotReadableException ex) {
        requestResponseWithoutDetails.setTimestamp(LocalDateTime.now());
        requestResponseWithoutDetails.setStatus("422");
        requestResponseWithoutDetails.setMessage("please enter a valid date");
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(requestResponseWithoutDetails);
    }

    @ExceptionHandler(DateNotAvailableException.class)
    public ResponseEntity<RequestResponseWithoutDetails> handleDateNotAvailableException(DateNotAvailableException dateNotAvailableException) {
        requestResponseWithoutDetails.setTimestamp(LocalDateTime.now());
        requestResponseWithoutDetails.setStatus("400");
        requestResponseWithoutDetails.setMessage(dateNotAvailableException.getMessage());
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(requestResponseWithoutDetails);
    }
   @ExceptionHandler(CompetitionDateException.class)
    public ResponseEntity<RequestResponseWithoutDetails> handleCompetitionDateException(CompetitionDateException competitionDateException){
        requestResponseWithoutDetails.setTimestamp(LocalDateTime.now());
        requestResponseWithoutDetails.setStatus("400");
        requestResponseWithoutDetails.setMessage(competitionDateException.getMessage());
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(requestResponseWithoutDetails);
   }
}
