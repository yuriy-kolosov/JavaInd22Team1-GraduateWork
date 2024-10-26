package ru.skypro.homework.mapper;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.entity.ImageEntity;
import ru.skypro.homework.entity.UserEntity;

import javax.swing.text.TabableView;

@Mapper(componentModel = "spring")
public interface UserMapper {

   @Mapping(target = "image",expression = "java(entity.getImage().getUrl())")
   User toUserDto(UserEntity entity);

   UpdateUser toUserUpdate(UserEntity entity);

   UserEntity toEntity(User dto);

   @Mapping(target = "url", source = "value")
   ImageEntity map(String value);

//   @Mapping(target = "url", source = "image")
//   ImageEntity userToImageEntity(User user);
}
