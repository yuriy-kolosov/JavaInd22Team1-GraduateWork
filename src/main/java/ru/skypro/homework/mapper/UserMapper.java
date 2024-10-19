package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.entity.UserEntity;

@Mapper(componentModel = "spring")
public interface UserMapper {
   User toDto(UserEntity entity);
   UserEntity toEntity(User dto);
}
