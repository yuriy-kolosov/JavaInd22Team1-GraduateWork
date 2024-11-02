package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.homework.entity.ImageUserEntity;

public interface ImageUserRepository extends JpaRepository<ImageUserEntity,Long> {
}
