package ru.skypro.homework.service;

import ru.skypro.homework.entity.ImageUserEntity;

public interface ImageUserService {
    void save(ImageUserEntity imageEntity);

    ImageUserEntity findById(Long id);
}
