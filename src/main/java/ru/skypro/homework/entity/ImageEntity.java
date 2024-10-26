package ru.skypro.homework.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "images")
public class ImageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "url")
    private String url;

    @Column(name = "file_size")
    private Long fileSize;

    @Column(name="media_type")
    private String mediaType;

    @Column(name = "data")
    private byte[] data;

    @OneToOne(mappedBy = "image",cascade = CascadeType.ALL)
    @JsonIgnore
    private UserEntity user;

    @OneToOne
    @JsonIgnore
    private Comment comment;
}
