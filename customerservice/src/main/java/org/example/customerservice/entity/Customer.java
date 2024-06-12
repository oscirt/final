package org.example.customerservice.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Объект данных клиентов
 */
@Entity
@Table(name = "customers", schema = "customers")
@Data
public class Customer implements UserDetails {

    /**
     * Идентификатор клиента
     */
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id_customer")
    private Long id;

    /**
     * Псевдоним клиента
     */
    @Column(name = "customer_username", nullable = false, unique = true)
    private String username;

    /**
     * Пароль клиента
     */
    @Column(name = "customer_password", nullable = false)
    private String password;

    /**
     * Роль клиента
     */
    @Column(name = "customer_role", nullable = false)
    private String role;

    /**
     * Идентификатор счета клиента
     */
    @Column(name = "bank_account_id", nullable = false)
    private Integer bankAccountId;

    /**
     * Номер телефона клиента
     */
    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    /**
     * Получение ролей клиента
     * @return список ролей клиента
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role));
    }

    /**
     * Аккаунт клиента не просрочен
     * @return true - аккаунт клиента всегда не просрочен
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Аккаунт клиента не заблокирован
     * @return true - аккаунт клиента всегда не заблокирован
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Пароль клиента не просрочен
     * @return true - пароль клиента всегда не просрочен
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Аккаунт клиента включен
     * @return true - аккаунт клиента всегда включен
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
