package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.entity.ImageUserEntity;
import ru.skypro.homework.entity.UserEntity;

@Mapper(componentModel = "spring")
public interface UserMapper {

   @Mapping(target = "image",expression = "java(entity.getImage().getUrl())")
   User toUserDto(UserEntity entity);

   UpdateUser toUserUpdate(UserEntity entity);

   UserEntity toEntity(User dto);

   @Mapping(target = "url", source = "value")
   ImageUserEntity map(String value);

//   @Mapping(target = "map", source = "url")
//   String map (ImageUserEntity value);
}
