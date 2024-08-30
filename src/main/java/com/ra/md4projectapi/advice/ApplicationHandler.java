package com.ra.md4projectapi.advice;


import com.ra.md4projectapi.exception.DataExistException;
import com.ra.md4projectapi.model.dto.response.DataError;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class ApplicationHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public DataError<Map<String,String>> handleError(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getFieldErrors().forEach(err->errors.put(err.getField(), err.getDefaultMessage()));
        return new DataError<>(errors,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public DataError<String> handleErrorNotFound(NoSuchElementException ex) {
        return new DataError<>(ex.getMessage(),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DataExistException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public DataError<Map<String,String>> handleErrorDataExist(DataExistException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put(ex.getField(), ex.getMessage());
        return new DataError<>(errors,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public DataError<String> handleErrorBadRequest(BadRequestException ex) {
        return new DataError<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public DataError<String> handleErrorNotFound(BadCredentialsException ex) {
        return new DataError<>(ex.getMessage(),HttpStatus.NOT_FOUND);
    }
}
