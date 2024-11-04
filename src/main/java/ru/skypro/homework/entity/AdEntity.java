package ru.skypro.homework.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "ads")
public class AdEntity {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinTable(name = "ads_ad_image"
            , joinColumns = @JoinColumn(name = "ads_id")
            , inverseJoinColumns = @JoinColumn(name = "ad_image_id"))
    protected AdImageEntity adImageEntity;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="author")
    private Long author;

    @Column(name="price")
    private BigDecimal price;

    @Column(name="title")
    private String title;

    @Column(name="description")
    private String description;

    public AdEntity(AdImageEntity adImageEntity) {
        this.adImageEntity = adImageEntity;
    }

    public AdEntity(Long id, Long author, BigDecimal price, String title, String description) {
        this.id = id;
        this.author = author;
        this.price = price;
        this.title = title;
        this.description = description;
    }

}
