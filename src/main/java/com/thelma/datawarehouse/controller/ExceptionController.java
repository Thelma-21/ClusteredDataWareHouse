package com.thelma.datawarehouse.controller;

import com.thelma.datawarehouse.exception.CustomAlreadyExistException;
import com.thelma.datawarehouse.exception.CustomNotFoundException;
import com.thelma.datawarehouse.exception.ExceptionResponse;
import com.thelma.datawarehouse.exception.InvalidCurrencyException;
import com.thelma.datawarehouse.response.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
@RequiredArgsConstructor
public class ExceptionController {
    private final ResponseService<ExceptionResponse> responseService;

    @ExceptionHandler(CustomAlreadyExistException.class)
    public ResponseEntity<ExceptionResponse> resourceNotFoundHandler(CustomAlreadyExistException exception){
        return responseService.response(new ExceptionResponse(exception.getMessage(), LocalDateTime.now() , HttpStatus.CONFLICT), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(InvalidCurrencyException.class)
    public ResponseEntity<ExceptionResponse> resourceNotFoundHandler(InvalidCurrencyException exception){
        return responseService.response(new ExceptionResponse(exception.getMessage(), LocalDateTime.now() , HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CustomNotFoundException.class)
    public ResponseEntity<ExceptionResponse> resourceNotFoundHandler(CustomNotFoundException exception){
        return responseService.response(new ExceptionResponse(exception.getMessage(), LocalDateTime.now() , HttpStatus.NOT_FOUND), HttpStatus.NOT_FOUND);
    }
}
