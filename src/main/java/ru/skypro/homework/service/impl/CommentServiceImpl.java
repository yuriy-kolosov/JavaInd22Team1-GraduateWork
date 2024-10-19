package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import ru.skypro.homework.entity.Comment;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public Comment add(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public Comment getComment(Long id) {
        return commentRepository.getById(id);
    }

    @Override public void delete(Comment comment) {
        commentRepository.delete(comment);
    }

    @Override public Comment update(Comment comment) {
        return commentRepository.save(comment);
    }
}
