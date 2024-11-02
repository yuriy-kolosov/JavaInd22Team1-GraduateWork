package ru.skypro.homework.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.entity.CommentEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.CommentService;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collection;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    public CommentServiceImpl(CommentRepository commentRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Comment add(Long id, CreateOrUpdateComment comment, Authentication authentication) {
        String userName = authentication.getName();
        UserEntity userEntity = userRepository.findByLogin(userName).get();
        Comment result = new Comment();
        result.setPk(id.intValue());
        result.setText(comment.getText());
        result.setAuthor(userName);
        result.setAuthorImage(userEntity.getImage().getUrl());
        result.setAuthorFirstName(userEntity.getFirstname());
        result.setCreatedAt(LocalDateTime.now().atZone(ZoneId.of("Europe/Moscow")).toInstant().toEpochMilli());
        return result;
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
