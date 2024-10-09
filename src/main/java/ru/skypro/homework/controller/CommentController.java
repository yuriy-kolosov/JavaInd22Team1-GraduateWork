package ru.skypro.homework.controller;

import com.sun.istack.NotNull;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.service.CommentService;
import ru.skypro.homework.service.impl.CommentServiceImpl;

import java.io.IOException;

@RestController
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentServiceImpl commentService) {
        this.commentService = commentService;
    }

    @PostMapping(value = "/ads/{id}/comments", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadAvatar(@PathVariable Long studentId, @RequestParam @NotNull MultipartFile avatar) throws IOException {
        if (avatar.getSize() >= 1024 * 300) {
            return ResponseEntity.badRequest().body("Слишком большой файл");
        }
        commentService.(studentId, avatar);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/ads/{id}/comments")
    public CommentDTO get(@PathVariable Long id) {
        CommentDTO commentDTO = commentService.getComment(id);

        return commentService.getComment(id);
    }

}
