package org.example.customerservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Класс запроса на вход
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestDto {

    /**
     * Псевдоним клиента
     */
    private String username;

    /**
     * Пароль клиента
     */
    private String password;
}
