package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.UserEntity;

@Mapper(componentModel = "spring")
public interface ExtendedAdMapper {

    @Mapping(target = "pk", source = "adEntity.id")
    @Mapping(target = "authorFirstName", source = "userEntity.firstname")
    @Mapping(target = "authorLastName", source = "userEntity.lastname")
    @Mapping(target = "description", source = "adEntity.description")
    @Mapping(target = "email", source = "userEntity.email")
    @Mapping(target = "image", source = "adEntity.image")
    @Mapping(target = "phone", source = "userEntity.phone")
    @Mapping(target = "price", source = "adEntity.price")
    @Mapping(target = "title", source = "adEntity.title")
    ExtendedAd toExtendedAd(AdEntity adEntity, UserEntity userEntity);

}
