package org.example.customerservice.security.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.example.customerservice.entity.Customer;
import org.example.starter.config.JwtTokenProperties;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

/**
 * Класс генерации JWT токена
 */
@Component
@RequiredArgsConstructor
@Log4j2
public class JwtTokenGenerator {

    private final JwtTokenProperties jwtTokenProperties;

    /**
     * Генерация JWT токена по псевдониму клиента и его паролю
     * @param authentication содержит псевдоним и пароль клиента
     * @return JWT токен
     */
    public String generateJwtToken(Authentication  authentication) {
        Customer userPrincipal = (Customer) authentication.getPrincipal();

        return Jwts.builder()
                .subject(userPrincipal.getUsername())
                .issuedAt(new Date())
                .expiration(new Date((new Date()).getTime() + jwtTokenProperties.getExpirationMs()))
                .signWith(key())
                .compact();
    }

    private SecretKey key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtTokenProperties.getSecret()));
    }
}
