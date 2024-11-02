package ru.skypro.homework.service;

import org.springframework.security.core.Authentication;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.entity.CommentEntity;

import java.util.Collection;

public interface CommentService {

    Comment add(Long id, CreateOrUpdateComment comment, Authentication authentication);

    Collection<CommentEntity> getAll();

    Comment getComment(Long id);

    void delete(CommentEntity comment);

    Comment update(CommentEntity comment);
}
