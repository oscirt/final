package org.example.starter.exception;

/**
 * Попытка перехода на страницу без авторизации
 * 403 код
 */
public class UnauthorizedException extends RuntimeException {

    public UnauthorizedException(String message) {
        super(message);
    }
}
