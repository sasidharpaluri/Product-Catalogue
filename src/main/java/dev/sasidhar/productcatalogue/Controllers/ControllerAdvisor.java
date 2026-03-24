package dev.sasidhar.productcatalogue.Controllers;

import dev.sasidhar.productcatalogue.Exceptions.LoginExpired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvisor {
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> IllegalArgumentExceptionAdvisor(Exception exp){
        return new ResponseEntity<>(exp.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(LoginExpired.class)
    public ResponseEntity<String> loginExpired(Exception exp){
        return new ResponseEntity<>("Login Expired, Please relogin",HttpStatus.UNAUTHORIZED);
    }
}
