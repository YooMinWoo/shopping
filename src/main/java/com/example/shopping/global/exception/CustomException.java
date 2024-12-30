package com.example.shopping.global.exception;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException{

    public CustomException(String message) {
        super(message);
    }
}
