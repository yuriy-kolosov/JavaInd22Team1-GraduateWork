package ru.skypro.homework.service;


import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;

import java.io.IOException;

public interface AdService {

    Ads getAds();

    Ad addAdWithImage(CreateOrUpdateAd updatedAd, MultipartFile adFile
            , Authentication authentication) throws IOException;

    ExtendedAd getExtendedAd(Long id);

    void delete(Long id);

    Ad update(Long id, CreateOrUpdateAd updatedAd);

    Ads getAdsMe(Authentication authentication);

    String[] updateAdWithImage(Long id, MultipartFile adFile) throws IOException;

    ResponseEntity<byte[]> getImage(Long id) throws IOException;

}
