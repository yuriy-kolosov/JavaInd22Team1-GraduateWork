package ru.skypro.homework.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class CreateOrUpdateComment {
    @Length(min = 8, max = 64)
    String text;
}
