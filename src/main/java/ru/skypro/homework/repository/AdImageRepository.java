package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.AdImageEntity;

import java.util.Optional;

public interface AdImageRepository extends JpaRepository<AdImageEntity, Long> {

    Optional<AdImageEntity> findById(Long id);

}
