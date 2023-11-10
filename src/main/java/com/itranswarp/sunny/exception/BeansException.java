package com.itranswarp.sunny.exception;

public class BeansException extends NestedRuntimeException {

    public BeansException() {
    }

    public BeansException(String message) {
        super(message);
    }

    public BeansException(Throwable cause) {
        super(cause);
    }

    public BeansException(String message, Throwable cause) {
        super(message, cause);
    }
}