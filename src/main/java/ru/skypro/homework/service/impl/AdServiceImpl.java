package ru.skypro.homework.service.impl;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.AdImageEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.exception.UserNotFoundException;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.mapper.CreateOrUpdateAdMapper;
import ru.skypro.homework.mapper.ExtendedAdMapper;
import ru.skypro.homework.repository.AdImageRepository;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AdService;

import java.io.IOException;
import java.util.List;

@Service
public class AdServiceImpl implements AdService {

    private final AdImageRepository adImageRepository;
    private final AdRepository adRepository;
    private final UserRepository userRepository;

    public AdServiceImpl(AdImageRepository adImageRepository, AdRepository adRepository, UserRepository userRepository) {
        this.adImageRepository = adImageRepository;
        this.adRepository = adRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Ads getAds() {

        List<AdEntity> adEntities = adRepository.findAll();
        List<Ad> results = adEntities.stream()
                .map(AdMapper.INSTANCE::toDTO)
                .toList();

        Ads ads = new Ads();
        ads.setCount(adEntities.size());
        ads.setResults(results);
        return ads;
    }

    @Override
    public Ad addAdWithImage(CreateOrUpdateAd updatedAd, MultipartFile adFile
            , Authentication authentication) throws IOException {

        Long author = userRepository.findByLogin(authentication.getName()).orElseThrow().getId();

        AdEntity adEntityCreated = CreateOrUpdateAdMapper.INSTANCE.toEntity(updatedAd);
        adEntityCreated.setAuthor(author);
        AdEntity savedAdEntity = adRepository.save(adEntityCreated);

        Ad savedAd = AdMapper.INSTANCE.toDTO(savedAdEntity);

        AdImageEntity adImageEntity = new AdImageEntity(savedAdEntity);
        adImageEntity.setAdEntity(savedAdEntity);
        adImageEntity.setSize(adFile.getSize());
        adImageEntity.setType(adFile.getContentType());
        adImageEntity.setData(adFile.getBytes());
        adImageRepository.save(adImageEntity);

        return savedAd;
    }

    @Override
    public ExtendedAd getExtendedAd(Long id) {

        AdEntity adEntity = adRepository.findById(id).orElseThrow();
        UserEntity userEntity = userRepository.findById(adEntity.getAuthor()).orElseThrow();
        return ExtendedAdMapper.INSTANCE.toExtendedAd(userEntity, adEntity);
    }

    @Override
    public void delete(Long id) {
        adRepository.deleteById(id);
    }

    @Override
    public Ad update(Long id, CreateOrUpdateAd updatedAd) {

        AdEntity adEntity = adRepository.findById(id).orElseThrow();
        AdEntity adEntityUpdated = CreateOrUpdateAdMapper.INSTANCE.toEntity(updatedAd);

        adEntityUpdated.setId(adEntity.getId());
        adEntityUpdated.setAuthor(adEntity.getAuthor());
        adEntityUpdated.setAuthor(adEntity.getAuthor());

        AdEntity savedAdEntity = adRepository.save(adEntityUpdated);
        return AdMapper.INSTANCE.toDTO(savedAdEntity);
    }

    @Override
    public Ads getAdsMe(Authentication authentication) {

        String login = authentication.getName();
        UserEntity user = userRepository.findByLogin(login)
                .orElseThrow(() -> new UserNotFoundException("Пользователь с логином %s не найден".formatted(login)));
        Long userId = user.getId();

        List<AdEntity> adEntities = adRepository.findAll();

        List<Ad> results = adEntities.stream()
                .map(AdMapper.INSTANCE::toDTO)
                .filter((d) -> d.getAuthor() == userId)
                .toList();

        Ads ads = new Ads();
        ads.setCount(results.size());
        ads.setResults(results);
        return ads;
    }

    @Override
    public String[] updateAdWithImage(Long id, MultipartFile adFile) throws IOException {

        AdEntity updatedAdEntity = adRepository.findById(id).orElseThrow();

        AdImageEntity adImageEntity = new AdImageEntity(updatedAdEntity);
        adImageEntity.setAdEntity(updatedAdEntity);
        adImageEntity.setSize(adFile.getSize());
        adImageEntity.setType(adFile.getContentType());
        adImageEntity.setData(adFile.getBytes());
        adImageRepository.save(adImageEntity);

        String[] stringGetAdImageUrl = {"/ads/get_image/" + id};

        return stringGetAdImageUrl;
    }

    @Override
    public ResponseEntity<byte[]> getImage(Long id) throws IOException {

        AdImageEntity adImageEntity = adRepository.findById(id).orElseThrow().getAdImageEntity();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(adImageEntity.getType()));
        headers.setContentLength(adImageEntity.getSize());

        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(adImageEntity.getData());
    }

}
