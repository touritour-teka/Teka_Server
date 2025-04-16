package com.teka.shared.error;

import com.teka.shared.response.CommonResponse;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MissingRequestValueException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BindException.class)
    public ResponseEntity<CommonResponse<Map<String, String>>> handleBindException(BindException e) {
        Map<String, String> errorMap = new HashMap<>();
        e.getFieldErrors().forEach(fieldError ->
                errorMap.put(fieldError.getField(), fieldError.getDefaultMessage())
        );

        logHandledException(e);

        return ResponseEntity
                .status(GlobalErrorProperty.BAD_REQUEST.getStatus())
                .body(CommonResponse.error(GlobalErrorProperty.BAD_REQUEST, errorMap));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<CommonResponse<Map<String, String>>> handleConstraintViolationException(ConstraintViolationException e) {
        Map<String, String> errorMap = new HashMap<>();
        e.getConstraintViolations().forEach(violation ->
                errorMap.put(violation.getPropertyPath().toString(), violation.getMessage())
        );

        logHandledException(e);

        return ResponseEntity
                .status(GlobalErrorProperty.BAD_REQUEST.getStatus())
                .body(CommonResponse.error(GlobalErrorProperty.BAD_REQUEST, errorMap));
    }

    @ExceptionHandler(MissingRequestValueException.class)
    public ResponseEntity<CommonResponse<String>> handleMissingRequestValueException(MissingRequestValueException e) {
        logHandledException(e);

        return ResponseEntity
                .status(GlobalErrorProperty.BAD_REQUEST.getStatus())
                .body(CommonResponse.error(GlobalErrorProperty.BAD_REQUEST, e.getMessage()));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<CommonResponse<String>> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        logHandledException(e);

        return ResponseEntity
                .status(GlobalErrorProperty.BAD_REQUEST.getStatus())
                .body(CommonResponse.error(GlobalErrorProperty.BAD_REQUEST, e.getMessage()));
    }

    @ExceptionHandler(HandlerMethodValidationException.class)
    public ResponseEntity<CommonResponse<Map<String, String>>> handleHandlerMethodValidationException(HandlerMethodValidationException e) {
        Map<String, String> errorMap = new HashMap<>();
        e.getParameterValidationResults().forEach(result ->
                errorMap.put(result.getMethodParameter().getParameterName(), "필수값입니다."));

        logHandledException(e);

        return ResponseEntity
                .status(GlobalErrorProperty.BAD_REQUEST.getStatus())
                .body(CommonResponse.error(GlobalErrorProperty.BAD_REQUEST, errorMap));
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<CommonResponse<String>> handleNoResourceFoundException(NoResourceFoundException e) {
        logHandledException(e);

        return ResponseEntity
                .status(GlobalErrorProperty.NOT_FOUND.getStatus())
                .body(CommonResponse.error(GlobalErrorProperty.NOT_FOUND, e.getMessage()));
    }

    @ExceptionHandler(TekaException.class)
    public ResponseEntity<CommonResponse<String>> handleTekaException(TekaException e) {
        logHandledException(e);

        return ResponseEntity
                .status(e.getErrorProperty().getStatus())
                .body(CommonResponse.error(e.getErrorProperty()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CommonResponse<String>> handleException(Exception e) {
        log.error(e.getMessage(), e);

        return ResponseEntity
                .status(GlobalErrorProperty.INTERNAL_SERVER_ERROR.getStatus())
                .body(CommonResponse.error(GlobalErrorProperty.INTERNAL_SERVER_ERROR));
    }

    private void logHandledException(Exception e) {
        log.warn("Resolved [{}: {}]", e.getClass().getName(), e.getMessage());
    }
}
