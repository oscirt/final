package org.example.starter.exception;

/**
 * Отправлен запрос на несуществующий endpoint
 * 404 код
 */
public class NotFoundException extends RuntimeException {

    public NotFoundException(String message) {
        super(message);
    }
}
