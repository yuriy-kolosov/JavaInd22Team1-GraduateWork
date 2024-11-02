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
            , inverseJoinColumns = @JoinColumn(name = "ad_image_id"
            , nullable = false
            , unique = true))
    protected AdImageEntity adImageEntity;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long author;

    private BigDecimal price;

    private String title;

    private String description;

    public AdEntity(AdImageEntity adImageEntity) {
        this.adImageEntity = adImageEntity;
    }

}
