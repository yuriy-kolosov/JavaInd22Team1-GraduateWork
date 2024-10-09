package ru.skypro.homework.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.CommentDTO;
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

        CommentDTO commentDTO = commentRepository.save(comment);
        return ;
    }

    @Override
    public Comment getComment(Long id) {
        return commentRepository.getById(id);
    }
}
