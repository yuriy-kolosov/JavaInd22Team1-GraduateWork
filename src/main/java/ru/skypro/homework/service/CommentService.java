package ru.skypro.homework.service;

import org.springframework.security.core.Authentication;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.entity.CommentEntity;

import java.util.Collection;

public interface CommentService {

    Comment add(Long id, CreateOrUpdateComment comment, Authentication authentication);

    Collection<CommentEntity> getAll();

    Comments getComment(Long id);

    void delete(Long adId, Long commentId);

    Comment update(CreateOrUpdateComment comment, Long adId, Long commentId);
}
