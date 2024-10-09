package ru.skypro.homework.dto;

public class CommentDTO {
    private String author;
    private String authorImage;
    private String authorFirstName;
    private Integer createdAt;
    private Integer pk;
    private String text;

    public CommentDTO(String author, String authorImage, String authorFirstName, Integer createdAt, Integer pk, String text) {
        this.author = author;
        this.authorImage = authorImage;
        this.authorFirstName = authorFirstName;
        this.createdAt = createdAt;
        this.pk = pk;
        this.text = text;
    }
}
