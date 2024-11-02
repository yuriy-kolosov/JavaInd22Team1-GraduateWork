package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;

@Data
public class UpdateUser {

    @Length(min = 3, max = 10)
    private String firstname;

    @Length(min = 3, max = 10)
    private String lastname;

    @Pattern(regexp = "\\+7\\s?\\(?\\d{3}\\)?\\s\\d{3}-?\\d{2}-?\\d{2}")
    private String phone;
}
