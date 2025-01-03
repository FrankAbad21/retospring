package com.frab.retospring.exception.advice;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.frab.retospring.dto.ErrorDTO;
import com.frab.retospring.exception.exception.RequestException;
import com.frab.retospring.exception.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorDTO> userNotFound(UserNotFoundException exception){
        return new ResponseEntity(ErrorDTO.builder().message(exception.getMessage()).build(),
                HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(RequestException.class)
    public ResponseEntity<ErrorDTO> runtimeExceptionHandler(RequestException ex) {
        return new ResponseEntity(ErrorDTO.builder().message(ex.getMessage()).build(),
                HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorDTO> badCredentials(BadCredentialsException exception){
        return new ResponseEntity(ErrorDTO.builder().message(exception.getMessage()).build(),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(JWTVerificationException.class)
    public ResponseEntity<ErrorDTO> jwtVerificationException(JWTVerificationException exception){
        return new ResponseEntity(ErrorDTO.builder().message(exception.getMessage()).build(),
                HttpStatus.UNAUTHORIZED);
    }
}
