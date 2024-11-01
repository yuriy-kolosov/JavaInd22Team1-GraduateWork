package ru.skypro.homework.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "ad_image")
public class AdImageEntity {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinTable(name = "ads_ad_image"
            , joinColumns = @JoinColumn(name = "ad_image_id")
            , inverseJoinColumns = @JoinColumn(name = "ads_id"
            , nullable = false
            , unique = true))
    protected AdEntity adEntity;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "file_length")
    private Long size;

    @Column(name = "media_type")
    private String type;

    @Column(name = "data")
    private byte[] data;

    public AdImageEntity(AdEntity adEntity) {
        this.adEntity = adEntity;
    }

}
