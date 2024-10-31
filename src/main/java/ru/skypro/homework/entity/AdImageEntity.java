package ru.skypro.homework.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Data
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
    private Long id;

    @Column(name = "file_length")
    private Long size;

    @Column(name = "media_type")
    private String type;

//    @Lob
    @Column(name = "data")
    private byte[] data;

    public AdImageEntity(AdEntity adEntity) {
        this.adEntity = adEntity;
    }

}
