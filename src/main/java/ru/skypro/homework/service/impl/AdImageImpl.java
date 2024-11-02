package ru.skypro.homework.service.impl;

import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.AdImageEntity;
import ru.skypro.homework.repository.AdImageRepository;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.service.AdImageService;

import java.io.*;

@Service
@Transactional
public class AdImageImpl implements AdImageService {

    private final AdRepository adRepository;
    private final AdImageRepository adImageRepository;

    public AdImageImpl(AdRepository adRepository, AdImageRepository adImageRepository) {
        this.adRepository = adRepository;
        this.adImageRepository = adImageRepository;
    }

    Logger logger = LoggerFactory.getLogger(AdImageImpl.class);


    @Override
    public void upload(Long adEntityId, MultipartFile adImageFile) throws IOException {

        logger.debug("\"adImageImpl\" upload method was invoke... ");

        AdEntity adEntity = adRepository.findById(adEntityId).orElseThrow();

        AdImageEntity adImageEntity = findAdImage(adEntityId);
        adImageEntity.setAdEntity(adEntity);
        adImageEntity.setSize(adImageFile.getSize());
        adImageEntity.setType(adImageFile.getContentType());
        adImageEntity.setData(adImageFile.getBytes());

        adImageRepository.save(adImageEntity);

    }

    @Override
    public AdImageEntity findAdImage(Long adEntityId) {
        logger.debug("\"adImageImpl\" findAdImage method was invoke... ");
        return adImageRepository.findById(adEntityId).orElseThrow();
    }

}
