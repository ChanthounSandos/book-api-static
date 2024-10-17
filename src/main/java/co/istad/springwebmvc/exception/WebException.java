package co.istad.springwebmvc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class WebException {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<?> handleServiceException(ResponseStatusException e){
        return ResponseEntity
                .status(e.getStatusCode())
                .body(ErrorResponse.builder()
                        .code(HttpStatus.BAD_REQUEST.value())
                        .message(HttpStatus.BAD_REQUEST.getReasonPhrase())
                        .requestedtime(LocalDateTime.now())
                        .reason(e.getReason())
                        .build());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse<?> handleValidationException(MethodArgumentNotValidException e){

        //property error
        //property error message
        List<ValidationResponse> validation = new ArrayList<>();
        e.getFieldErrors()
                .forEach(fieldError -> validation
                        .add(ValidationResponse.builder()
                                .field(fieldError.getField())
                                .desc(fieldError.getDefaultMessage())
                                .build()
                        )
                );

        return ErrorResponse.builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .message(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .requestedtime(LocalDateTime.now())
                .reason(validation)
                .build();
    }

    @ExceptionHandler(RuntimeException.class)
    public Map<String, String> handleRuntimeEx(RuntimeException e){
        return Map.of("message", e.getMessage());
    }
}
