package com.example.demo.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.putIfAbsent("error", errorMessage);
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Message> handleIllegalArgumentException(IllegalArgumentException ex) {
        logger.error("{}", ex.getMessage());
        Message message = new Message(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Message> handleRuntimeException(RuntimeException ex) {
        logger.error("{}", ex.getMessage());
        Message message = new Message(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
        return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Message> handleNoSuchElementException(NoSuchElementException ex) {
        logger.error("{}", ex.getMessage());
        Message message = new Message(HttpStatus.NOT_FOUND.value(), ex.getMessage()); // 404
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Message> handleGeneralException(Exception ex) {
        logger.error("{}", ex.getMessage());
        Message message = new Message(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage()); // 500
        return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(DuplicateRecordException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<Message> handleDuplicateRecordException(Exception ex) {
        logger.error("{}", ex.getMessage());
        Message message = new Message(HttpStatus.CONFLICT.value(), ex.getMessage()); // 500
        return new ResponseEntity<>(message, HttpStatus.CONFLICT);
    }
    @Getter
    @AllArgsConstructor
    public static class Message {
        private int code;
        private String message;
    }
}
