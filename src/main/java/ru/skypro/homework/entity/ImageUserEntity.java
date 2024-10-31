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
@Table(name = "user_images")
public class ImageUserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="file_size")
    private long fileSize;

    @Column(name = "media_type")
    private String mediaType;

    @Column(name = "data")
    private byte[] data;

    @Column(name = "url")
    private String url;

    @OneToOne(mappedBy = "image")
    @JsonIgnore
    private UserEntity user;
}
