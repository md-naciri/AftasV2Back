package com.app.fishcompetition.common.exceptions;

import com.app.fishcompetition.common.responses.RequestResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Map;

@RestControllerAdvice
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GlobalExceptionHandler {

    private final RequestResponse requestResponse;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<RequestResponse> handleValidationExceptions(MethodArgumentNotValidException notValidException) {
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
}
