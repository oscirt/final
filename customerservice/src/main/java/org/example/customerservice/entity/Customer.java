package org.example.customerservice.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "customers", schema = "customers")
@Data
public class Customer implements UserDetails {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id_customer")
    private Long id;

    @Column(name = "customer_username", nullable = false, unique = true)
    private String username;

    @Column(name = "customer_password", nullable = false)
    private String password;

    @Column(name = "customer_role", nullable = false)
    private String role;

    @Column(name = "bank_account_id", nullable = false)
    private Integer bankAccountId;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
