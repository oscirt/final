package org.example.starter.exception;

/**
 * Внештатная ситуация в работе системы
 * 500 код
 */
public class InnerException extends RuntimeException {

    public InnerException(String message) {
        super(message);
    }
}
