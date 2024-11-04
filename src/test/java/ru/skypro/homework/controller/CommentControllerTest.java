package ru.skypro.homework.controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.entity.CommentEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.CommentService;
import ru.skypro.homework.service.UserService;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CommentControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private CommentController commentController;

    @Autowired
    private UserController userController;

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    private String url;

    @BeforeEach
    void setUp() {
        url = "http://localhost:" + port;
    }

    @AfterEach
    void tearDown() {
        Collection<CommentEntity> comments = commentService.getAll();
        comments.forEach(comment -> commentService.delete(1L, comment.getId()));
    }

    @Test
    void contextLoads() {
        Assertions.assertThat(commentController).isNotNull();
    }


    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void addTest() {
        UserEntity author = saveUser();
        Comment comment = createCommentDTO(author);
        CommentEntity commentEntity = createCommentEntity(author.getId(), comment.getCreatedAt());

        url += "/ads/" + commentEntity.getId() + "/comments";
        Comment actualComment = testRestTemplate.postForObject(url, comment, Comment.class);

        assertThat(actualComment).isEqualTo(comment);
    }

    @Test
    void get() {
        UserEntity author = saveUser();
        Comment comment = createCommentDTO(author);
        CommentEntity commentEntity = createCommentEntity(author.getId(), comment.getCreatedAt());

        url += "/ads/" + commentEntity.getId() + "/comments";
        testRestTemplate.postForObject(url, comment, Comment.class);
        String actualComment = testRestTemplate.getForObject(url, String.class);

        assertThat(actualComment).isEqualTo(comment);
    }

    @Test
    void delete() {
        UserEntity author = saveUser();
        Comment comment = createCommentDTO(author);
        CommentEntity commentEntity = createCommentEntity(author.getId(), comment.getCreatedAt());

        url += "/ads/" + commentEntity.getId() + "/comments";
        testRestTemplate.delete(url, comment, Comment.class);
        String actualComment = testRestTemplate.getForObject(url, String.class);

        assertThat(actualComment).isNull();
    }

    @Test
    void update() {
        UserEntity author = saveUser();
        Comment comment = createCommentDTO(author);
        CommentEntity commentEntity = createCommentEntity(author.getId(), comment.getCreatedAt());

        url += "/ads/" + commentEntity.getId() + "/comments";
        comment.setText("all bad");
        testRestTemplate.postForObject(url, comment, Comment.class);
        String actualComment = testRestTemplate.getForObject(url, String.class);

        assertThat(actualComment).isEqualTo(comment);
    }

    private UserEntity saveUser() {
        UserEntity user = new UserEntity();
        user.setFirstname("Иван");
        user.setLastname("Иванов");
        user.setEmail("ivan@mail.ru");
        user.setPhone("89657854");
        user.setRole(Role.USER.toString());
        return userRepository.save(user);
    }

    private Comment createCommentDTO(UserEntity author) {
        Comment comment = new Comment();
        comment.setAuthor(author.getId());
        comment.setText("all good");
        comment.setCreatedAt(LocalDateTime.now().atZone(ZoneId.of("Europe/Moscow")).toInstant().toEpochMilli());
        return comment;
    }

    private CommentEntity createCommentEntity(Long authorId, Long createAt) {
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setText("all good");
        commentEntity.setAuthor(authorId);
        commentEntity.setCreatedAt(createAt);
        commentEntity.setId(1L);
        return commentEntity;
    }
}