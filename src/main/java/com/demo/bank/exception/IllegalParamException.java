package com.demo.bank.exception;

public class IllegalParamException extends RuntimeException{

    public IllegalParamException(String message) {
        super(message);
    }
}
