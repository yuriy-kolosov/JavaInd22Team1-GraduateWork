package ru.skypro.homework.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Id;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Comment {

    @Id
    private Long Id;
    private String author;
    private String authorImage;
    private String authorFirstName;
    private Integer createdAt;
    private Integer pk;
    private String text;

    public Comment() {
    }

    public Comment(String author, String authorImage, String authorFirstName, Integer createdAt, Integer pk, String text) {
        this.author = author;
        this.authorImage = authorImage;
        this.authorFirstName = authorFirstName;
        this.createdAt = createdAt;
        this.pk = pk;
        this.text = text;
    }


}
