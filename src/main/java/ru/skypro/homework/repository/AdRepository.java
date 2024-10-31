package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.entity.AdEntity;

import java.util.Optional;

public interface AdRepository extends JpaRepository<AdEntity, Long> {

    Optional<AdEntity> findById(Long id);

}