package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.entity.ImageUserEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.exception.UserNotFoundException;
import ru.skypro.homework.exception.WrongPassword;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.ImageUserService;
import ru.skypro.homework.service.UserService;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ImageUserService imageUserService;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Value("${image.user.url}")
    private String url;

    @Override
    public void setNewPassword(NewPassword newPassword, Authentication authentication) {
        UserEntity userEntity = findByAuthentication(authentication);
        String currentPassword = passwordEncoder.encode(newPassword.getCurrentPassword());
        if (!passwordEncoder.matches(userEntity.getPassword(), currentPassword)) {
            throw new WrongPassword("не верный пароль");
        }
        userEntity.setPassword(newPassword.getNewPassword());
        userRepository.save(userEntity);
    }

    @Override
    public User getInfo(Authentication authentication) {
        UserEntity userEntity = findByAuthentication(authentication);
        return userMapper.toUserDto(userEntity);
    }

    @Override
    public UpdateUser changeUser(UpdateUser updateUser, Authentication authentication) {
        UserEntity userEntity = findByAuthentication(authentication);
        return userMapper.toUserUpdate(userEntity);
    }

    @Override
    public void setImage(MultipartFile image, Authentication authentication) {
        UserEntity userEntity = findByAuthentication(authentication);
        ImageUserEntity imageEntity = new ImageUserEntity();
        imageEntity.setMediaType(image.getContentType());
        imageEntity.setFileSize(image.getSize());
        try {
            imageEntity.setData(image.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        imageEntity.setUrl(url + imageEntity.getId());
        imageUserService.save(imageEntity);
    }

    private UserEntity findByAuthentication(Authentication authentication) {
        String login = authentication.getName();
        return findByLogin(login);
    }

    private UserEntity findByLogin(String login) {
        return userRepository.findByLogin(login)
                .orElseThrow(() -> new UserNotFoundException("пользователь с логином %s не найден"
                        .formatted(login)));
    }
}
