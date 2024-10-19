package ru.skypro.homework.mapper;

import jakarta.validation.constraints.Max;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.entity.Comment;

@Mapper
public interface CommentMapper {
    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);
    CommentDTO toDTO(Comment comment);
}
