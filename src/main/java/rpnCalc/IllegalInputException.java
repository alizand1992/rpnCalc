package com.rpnCalc;

import java.lang.Exception;

public class IllegalInputException extends Exception {
    public IllegalInputException() {
        super("", null);
    }

    public IllegalInputException(String message, Throwable cause) {
        super(message, cause);
    }
}
