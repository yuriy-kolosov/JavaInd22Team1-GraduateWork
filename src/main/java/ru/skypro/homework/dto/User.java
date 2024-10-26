package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
public class User {

    private Integer id;


    private String email;

    private String firstname;

    private String lastName;

    private String phone;

    private Role role;

    private String image;
}
