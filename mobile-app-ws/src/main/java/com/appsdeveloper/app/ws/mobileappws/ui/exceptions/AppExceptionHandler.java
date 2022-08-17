package com.appsdeveloper.app.ws.mobileappws.ui.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handlerAnyException(Exception exception, WebRequest webRequest) {
        String errorMassageDescription = exception.getLocalizedMessage();
        if(errorMassageDescription==null) errorMassageDescription = exception.toString();
        CustomExceptionMassage customExceptionMassage =
                new CustomExceptionMassage(new Date(), errorMassageDescription);
        return new ResponseEntity<>(customExceptionMassage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {NullPointerException.class, UserServiceException.class})
    public ResponseEntity<Object> handlerSpecificException(Exception exception, WebRequest webRequest) {
        String errorMassageDescription = exception.getLocalizedMessage();
        if(errorMassageDescription==null) errorMassageDescription = exception.toString();
        CustomExceptionMassage customExceptionMassage =
                new CustomExceptionMassage(new Date(), errorMassageDescription);
        return new ResponseEntity<>(customExceptionMassage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
