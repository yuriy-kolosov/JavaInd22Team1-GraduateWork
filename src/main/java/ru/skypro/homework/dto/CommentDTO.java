package ru.skypro.homework.dto;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class CommentDTO {

    private String author;
    private String authorImage;
    private String authorFirstName;
    private LocalDateTime createdAt;
    private Integer pk;
    private String text;
}
