package com.emcode.training_log.exception;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleRequestValidation(
        MethodArgumentNotValidException exception
    ) {
        Map<String, String> errors = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .collect(Collectors.toMap(
                        FieldError::getField,
                         (FieldError fieldError) -> Objects.requireNonNullElse(
                                fieldError.getDefaultMessage(),
                                "Invalid value"
                        ),
                        (first, second) -> first,
                        LinkedHashMap::new
                ));

        return buildProblemDetail(
                HttpStatus.BAD_REQUEST,
                "Request validation failed",
                errors);

    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ProblemDetail handleConstraintViolation(
            ConstraintViolationException exception
    ) {
        Map<String, String> errors = exception.getConstraintViolations()
                .stream()
                .collect(Collectors.toMap(
                        violation -> violation.getPropertyPath().toString(),
                        ConstraintViolation::getMessage,
                        (first, second) -> first,
                        LinkedHashMap:: new

                ));

        return buildProblemDetail(
                HttpStatus.BAD_REQUEST,
                "Constraint validation failed",
                errors
        );
    }



    private ProblemDetail buildProblemDetail(
            HttpStatus status,
            String title,
            Map<String, String> errors
    ) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(status);
        problemDetail.setTitle(title);
        problemDetail.setProperty("errors", errors);

        return problemDetail;
    }
}
