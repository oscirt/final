package org.example.starter.jwt;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.example.starter.config.JwtTokenProperties;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;

/**
 * Базовый класс вспомогательных функций для JWT токенов
 */
@Component
@RequiredArgsConstructor
@Log4j2
public class BasicJwtUtils {

    private final JwtTokenProperties jwtTokenProperties;

    /**
     * Получение псевдонима клиента с помощью JWT токена
     * @param token токен авторизации
     * @return псевдоним клиента
     */
    public String getUsernameFromJwtToken(String token) {
        log.info("Получение псевдонима клиента");
        String username = Jwts.parser().verifyWith(key()).build()
                .parseSignedClaims(token).getPayload().getSubject();
        log.info("Получен псевдоним клиента: {}", username);
        return username;
    }

    /**
     * Валидация JWT токена
     * @param authToken токен авторизации
     * @return валидность токена
     */
    public boolean validateJwtToken(String authToken) {
        try {
            log.info("Валидация JWT токена");
            Jwts.parser().verifyWith(key()).build().parse(authToken);
            log.info("Валидация прошла успешно");
            return true;
        } catch (MalformedJwtException e) {
            log.error("Неправильный JWT токен: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            log.error("JWT токен просрочен: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.error("JWT токен не поддерживается: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error("Данные JWT токена пустые: {}", e.getMessage());
        }
        return false;
    }

    private SecretKey key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtTokenProperties.getSecret()));
    }
}
