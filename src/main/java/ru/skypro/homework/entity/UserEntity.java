package ru.skypro.homework.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;

import lombok.*;
import org.hibernate.annotations.Cascade;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Data
@RequiredArgsConstructor
@Table(name = "users")
public class UserEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "f_name")
    private String firstname;

    @Column(name = "s_name")
    private String surname;

    @Column(name = "l_name")
    private String lastname;

    @Column(name = "username")
    private String login;

    @Column(name = "phone")
    private String phone;

    @Column(name = "reg_date")
    private LocalDateTime registrationDate;

    @Column(name = "email")
    private String email;

    @Column(name = "role")
    private String role;

    @Column(name = "password")
    private String password;

    @OneToOne
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private ImageUserEntity image;

    public UserEntity(String firstname, String lastname, String login, String phone, String role, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.login = login;
        this.phone = phone;
        this.role = role;
        this.password = password;
    }

    public UserEntity(Long id, String firstname, String surname, String lastname, String login, String phone, LocalDateTime registrationDate, String email, String role, String password) {
        this.id = id;
        this.firstname = firstname;
        this.surname = surname;
        this.lastname = lastname;
        this.login = login;
        this.phone = phone;
        this.registrationDate = registrationDate;
        this.email = email;
        this.role = role;
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.login;
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
