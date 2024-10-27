package ru.skypro.homework.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.MediaType;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "ads")
public class AdEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private Long author;

    private byte[] image;

    @Column(name = "image_length")
    private Long imageLength;

    @Column(name = "image_type")
    private String imageType;

    private BigDecimal price;

    private String title;

    private String description;

}
