package org.example.starter.exception;

/**
 * Намеренно выбрасываемое исключение
 * 400 код
 */
public class IntentionException extends RuntimeException {

    public IntentionException(String message) {
        super(message);
    }
}
