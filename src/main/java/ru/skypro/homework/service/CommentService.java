package ru.skypro.homework.service;

import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.entity.Comment;

public interface CommentService {
    Comment add(Comment comment);
    Comment getComment(Long id);
}
