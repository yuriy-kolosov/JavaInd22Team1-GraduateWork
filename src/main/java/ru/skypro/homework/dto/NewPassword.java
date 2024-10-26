package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

@Data
@Validated
public class NewPassword {

    @Max(16)
    @Min(8)
    private String currentPassword;

    @Max(16)
    @Min(8)
    private String newPassword;
}
