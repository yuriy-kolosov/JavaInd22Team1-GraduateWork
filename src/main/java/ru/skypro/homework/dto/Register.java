package ru.skypro.homework.dto;

import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.skypro.homework.entity.UserEntity;

@Data
public class Register {

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String phone;
    private Role role;

    public UserEntity toRegister(PasswordEncoder passwordEncoder) {

        System.out.println(passwordEncoder);
        System.out.println();

        return new UserEntity(firstName, lastName, username, phone
                , role.toString(), passwordEncoder.encode(password));

    }

}
