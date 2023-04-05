package com.helmes.citymanagement.exception;

import com.helmes.citymanagement.vo.ErrorEntry;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = {NotFoundException.class})
    public ResponseEntity<Object> handleCityNotFoundException(NotFoundException ex) {
        ErrorEntry errorEntry = new ErrorEntry(HttpStatus.NOT_FOUND, ex.getMessage());
        return new ResponseEntity<>(errorEntry, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<Object> handleCityValidationException(MethodArgumentNotValidException ex) {
        ErrorEntry errorEntry = new ErrorEntry(HttpStatus.BAD_REQUEST, ex.getMessage());
        return new ResponseEntity<>(errorEntry, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {NullPointerException.class, IllegalStateException.class, IllegalArgumentException.class})
    public ResponseEntity<Object> handleCityAppException(RuntimeException ex) {
        ErrorEntry errorEntry = new ErrorEntry(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        return new ResponseEntity<>(errorEntry, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
