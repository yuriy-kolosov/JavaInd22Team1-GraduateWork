package ru.skypro.homework.service;

import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.entity.CommentEntity;

import java.util.Collection;

public interface CommentService {

    Comment add(CommentEntity comment);

    Collection<CommentEntity> getAll();

    Comment getComment(Long id);

    void delete(CommentEntity comment);

    Comment update(CommentEntity comment);
}
