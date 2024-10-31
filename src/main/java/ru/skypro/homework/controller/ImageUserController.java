package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.skypro.homework.entity.ImageUserEntity;
import ru.skypro.homework.service.ImageUserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("users/images")
public class ImageUserController {

    private final ImageUserService imageUserService;

    @GetMapping("/{id}")
    ResponseEntity<byte[]> findById(@PathVariable Long id){
        ImageUserEntity imageUserEntity = imageUserService.findById(id);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(imageUserEntity.getMediaType()));
        headers.setContentLength(imageUserEntity.getData().length);
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(imageUserEntity.getData());
    }

}
