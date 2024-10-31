package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.AdImageEntity;

import java.io.IOException;

public interface AdImageService {

    void upload(Long adEntityId, MultipartFile avatarFile) throws IOException;

    AdImageEntity findAdImage(Long id);
}
