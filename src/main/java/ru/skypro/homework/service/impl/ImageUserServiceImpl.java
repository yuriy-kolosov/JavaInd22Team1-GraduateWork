package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.skypro.homework.entity.ImageUserEntity;
import ru.skypro.homework.exception.ImageUserNotFoundException;
import ru.skypro.homework.repository.ImageUserRepository;
import ru.skypro.homework.service.ImageUserService;

@Service
@RequiredArgsConstructor
public class ImageUserServiceImpl implements ImageUserService {

    private final ImageUserRepository imageUserRepository;
    @Override
    public void save(ImageUserEntity imageEntity) {
        imageUserRepository.save(imageEntity);
    }

    @Override
    public ImageUserEntity findById(Long id) {
        return imageUserRepository
                .findById(id).orElseThrow(()-> new ImageUserNotFoundException("изображение с id=%s не найдено"
                        .formatted(id)));
    }
}
