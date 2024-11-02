package ru.skypro.homework.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.entity.CommentEntity;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.service.CommentService;

import java.util.Collection;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private final CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public Comment add(CommentEntity comment) {
        CommentEntity savedComment = commentRepository.save(comment);
        return CommentMapper.INSTANCE.toDTO(savedComment);
    }

    @Override
    public Collection<CommentEntity> getAll() {
        return commentRepository.findAll();
    }

    @Override
    public Comment getComment(Long id) {
        CommentEntity comment = commentRepository.getById(id);
        return CommentMapper.INSTANCE.toDTO(comment);
    }

    @Override
    public void delete(CommentEntity comment) {
        commentRepository.delete(comment);
    }

    @Override
    public Comment update(CommentEntity comment) {
        CommentEntity updatedComment = commentRepository.save(comment);
        return CommentMapper.INSTANCE.toDTO(updatedComment);
    }
}
