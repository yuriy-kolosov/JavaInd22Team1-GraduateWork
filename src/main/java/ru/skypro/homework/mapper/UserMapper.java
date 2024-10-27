package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.entity.UserEntity;

@Mapper(componentModel = "spring")
public interface UserMapper {

   User toUserDto(UserEntity entity);

   UpdateUser toUserUpdate(UserEntity entity);

   UserEntity toEntity(User dto);
}
