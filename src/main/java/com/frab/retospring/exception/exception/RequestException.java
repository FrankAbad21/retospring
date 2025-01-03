package com.frab.retospring.exception.exception;

import lombok.Data;

@Data
public class RequestException extends RuntimeException {

    public RequestException(String message) {
        super(message);
    }
}
