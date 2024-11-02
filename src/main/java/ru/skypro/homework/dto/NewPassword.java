package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

@Data
public class NewPassword {

    @Length(min = 8, max = 16)
    private String currentPassword;

    @Length(min = 8, max = 16)
    private String newPassword;
}
