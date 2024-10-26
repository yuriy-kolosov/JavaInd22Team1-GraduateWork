package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;

@Data
@Validated
public class UpdateUser {

    @Max(10)
    @Min(3)
    private String firstname;

    @Max(10)
    @Min(3)
    private String lastname;

    @Pattern(regexp = "\\+7\\s?\\(?\\d{3}\\)?\\s\\d{3}-?\\d{2}-?\\d{2}")
    private String phone;
}
