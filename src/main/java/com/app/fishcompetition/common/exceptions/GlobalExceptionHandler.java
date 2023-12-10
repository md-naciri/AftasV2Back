package com.app.fishcompetition.common.exceptions;

import com.app.fishcompetition.common.responses.RequestResponse;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    private final RequestResponse requestResponse;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<RequestResponse> handleValidationExceptions(MethodArgumentNotValidException notValidException ) {
        requestResponse.setTimestamp(LocalDateTime.now());
        requestResponse.setMessage("Validation error");
        requestResponse.setStatus("422");
        Map<String,Object> errors = notValidException.getBindingResult().getFieldErrors().stream()
                .collect(
                        () -> new java.util.HashMap<>(),
                        (map, fieldError) -> map.put(fieldError.getField(), fieldError.getDefaultMessage()),
                        (map, map2) -> map.putAll(map2)
                );
        requestResponse.setDetails(errors);
        return ResponseEntity.badRequest().body(requestResponse);
    }
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<RequestResponse> handleDataIntegrityViolationException(DataIntegrityViolationException dataIntegrityViolationException) {
        requestResponse.setTimestamp(LocalDateTime.now());
        requestResponse.setMessage("Data integrity violation");
        requestResponse.setStatus("409");
        Map<String,Object> errors = new HashMap<>();
        errors.put("duplicate key",dataIntegrityViolationException.getMessage());
        requestResponse.setDetails(errors);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(requestResponse);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<RequestResponse> handleIllegalArgumentException(IllegalArgumentException illegalArgumentException) {
        requestResponse.setTimestamp(LocalDateTime.now());
        requestResponse.setMessage("Illegal argument");
        requestResponse.setStatus("400");
        Map<String,Object> errors = new HashMap<>();
        errors.put("Error", illegalArgumentException.getMessage());
        requestResponse.setDetails(errors);
        return ResponseEntity.badRequest().body(requestResponse);
    }
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<RequestResponse> handleNoSuchElementException(NoSuchElementException noSuchElementException) {
        requestResponse.setTimestamp(LocalDateTime.now());
        requestResponse.setMessage("No such element");
        requestResponse.setStatus("404");
        Map<String,Object> errors = new HashMap<>();
        errors.put("Error", noSuchElementException.getMessage());
        requestResponse.setDetails(errors);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(requestResponse);
    }

}
