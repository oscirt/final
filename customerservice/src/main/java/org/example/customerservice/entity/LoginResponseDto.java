package org.example.customerservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Ответа на запрос на вход
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponseDto {

    /**
     * JWT токен авторизации
     */
    private String token;
}
