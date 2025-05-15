package com.suhayilmazok.exception;

public class BaseException extends RuntimeException {
    public BaseException(ErrorMessage error) {
        super(error.prepareErrorMessage());
    }
}
