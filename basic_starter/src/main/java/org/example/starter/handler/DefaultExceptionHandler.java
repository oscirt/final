package org.example.starter.handler;

import feign.FeignException;
import org.example.starter.exception.InnerException;
import org.example.starter.exception.IntentionException;
import org.example.starter.exception.NotFoundException;
import org.example.starter.exception.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Стандартный обработчик исключений
 */
@ControllerAdvice
public class DefaultExceptionHandler {

    /**
     * Обработка исключения {@link IntentionException}
     * @param e исключение {@link IntentionException}
     * @return ответ, содержащий причину ошибки и статус код
     */
    @ExceptionHandler(IntentionException.class)
    public ResponseEntity<ErrorResponse> handleIntentionException(IntentionException e) {
        ErrorResponse response = new ErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(response);
    }

    /**
     * Обработка исключения {@link UnauthorizedException}
     * @param e исключение {@link UnauthorizedException}
     * @return ответ, содержащий причину ошибки и статус код
     */
    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ErrorResponse> handleUnauthorizedException(UnauthorizedException e) {
        ErrorResponse response = new ErrorResponse(e.getMessage(), HttpStatus.FORBIDDEN.value());
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(response);
    }

    /**
     * Обработка исключения {@link NotFoundException}
     * @param e исключение {@link NotFoundException}
     * @return ответ, содержащий причину ошибки и статус код
     */
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException e) {
        ErrorResponse response = new ErrorResponse(e.getMessage(), HttpStatus.NOT_FOUND.value());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(response);
    }

    /**
     * Обработка исключения {@link InnerException}
     * @param e исключение {@link InnerException}
     * @return ответ, содержащий причину ошибки и статус код
     */
    @ExceptionHandler(InnerException.class)
    public ResponseEntity<ErrorResponse> handleInnerException(InnerException e) {
        ErrorResponse response = new ErrorResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(response);
    }

    /**
     * Обработка исключения {@link FeignException}
     * @param e исключение {@link FeignException}
     * @return ответ, содержащий причину ошибки и статус код
     */
    @ExceptionHandler(FeignException.class)
    public ResponseEntity<ErrorResponse> handleFeignClientException(FeignException.FeignClientException e) {
        ErrorResponse response = new ErrorResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(response);
    }
}
