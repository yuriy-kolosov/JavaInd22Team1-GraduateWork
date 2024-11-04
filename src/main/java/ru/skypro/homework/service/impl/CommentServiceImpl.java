package ru.skypro.homework.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.CommentEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.CommentService;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final CommentMapper commentMapper;
    private final AdRepository adRepository;

    public CommentServiceImpl(CommentRepository commentRepository, UserRepository userRepository, CommentMapper commentMapper, AdRepository adRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.commentMapper = commentMapper;
        this.adRepository = adRepository;
    }

    @Override
    public Comment add(Long id, CreateOrUpdateComment comment, Authentication authentication) {
        String userName = authentication.getName();
        UserEntity userEntity = userRepository.findByLogin(userName).get();
        AdEntity adEntity = adRepository.findById(id).get();
        Comment result = new Comment();
        result.setAuthor(userEntity.getId());
        result.setPk(id.intValue());
        result.setText(comment.getText());
        result.setAuthorImage(userEntity.getImage().getUrl());
        result.setAuthorFirstName(userEntity.getFirstname());
        result.setCreatedAt(LocalDateTime.now().atZone(ZoneId.of("Europe/Moscow")).toInstant().toEpochMilli());
        CommentEntity commentEntity = commentMapper.toEntity(result);
        commentEntity.setAd(adEntity);
        result.setPk(adEntity.getId().intValue());
        commentRepository.save(commentEntity);
        return result;
    }

    @Override
    public Collection<CommentEntity> getAll() {
        return commentRepository.findAll();
    }

    @Override
    public Comments getComment(Long id) {
        AdEntity adEntity = adRepository.findById(id).get();
        List<CommentEntity> commentEntities = adEntity.getComments();
        List<Comment> comments = commentEntities.stream()
                .map(c -> commentMapper.toDTO(c)).toList();
        Comments result = new Comments();
        result.setCount(comments.size());
        result.setResults(comments);
        return result;
    }

    @Override
    public void delete(Long adId, Long commentId) {
        AdEntity adEntity = adRepository.findById(adId).get();
        List<CommentEntity> commentEntities = adEntity.getComments();
        CommentEntity commentEntity =
                commentEntities.stream().filter(c -> c.getId().equals(commentId)).findFirst().get();
        if (Objects.nonNull(commentEntity)) {
            commentRepository.delete(commentEntity);
        }
    }

    @Override
    public Comment update(CreateOrUpdateComment comment, Long adId, Long commentId) {
        AdEntity adEntity = adRepository.findById(adId).get();
        List<CommentEntity> commentEntities = adEntity.getComments();
        CommentEntity commentEntity =
                commentEntities.stream().filter(c -> c.getId().equals(commentId)).findFirst().get();

        if (Objects.nonNull(commentEntity)) {
            commentEntity.setText(comment.getText());
            commentEntity.setCreatedAt(LocalDateTime.now()
                    .atZone(ZoneId.of("Europe/Moscow")).toInstant().toEpochMilli());
            commentRepository.save(commentEntity);
        }

        return commentMapper.toDTO(commentEntity);
    }
}
