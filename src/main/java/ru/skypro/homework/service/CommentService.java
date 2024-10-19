package ru.skypro.homework.service;

import ru.skypro.homework.entity.Comment;

public interface CommentService {

    Comment add(Comment comment);

    Comment getComment(Long id);

    void delete(Comment comment);

    Comment update(Comment comment);
}
