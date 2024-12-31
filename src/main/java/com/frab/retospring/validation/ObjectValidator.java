package com.frab.retospring.validation;


import com.frab.retospring.exception.exception.RequestException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
@Slf4j
@RequiredArgsConstructor
public class ObjectValidator {
    private final Validator validator;

    @SneakyThrows
    public <T> T validate(T object) {
        Set<ConstraintViolation<T>> errors = validator.validate(object);
        if(errors.isEmpty()) {
            return object;
        }
        else {
            String message = errors.stream().map(err -> err.getMessage()).collect(Collectors.joining(", "));
            throw new RequestException( message, HttpStatus.BAD_REQUEST.toString());
        }
    }

}