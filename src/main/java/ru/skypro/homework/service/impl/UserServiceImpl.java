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
import java.util.Objects;

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
    public void setNewPassword(NewPassword newPassword, String userName) {
        UserEntity userEntity = findByLogin(userName);
        if (!passwordEncoder.matches(newPassword.getCurrentPassword(), userEntity.getPassword())) {
            throw new WrongPassword("не верный пароль");
        }
        String password = passwordEncoder.encode(newPassword.getNewPassword());
        userEntity.setPassword(password);
        userRepository.save(userEntity);
    }

    @Override
    public User getInfo(String userName) {
        UserEntity userEntity = findByLogin(userName);
        return userMapper.toUserDto(userEntity);
    }

    @Override
    public UpdateUser changeUser(UpdateUser updateUser, String userName) {
        UserEntity userEntity = findByLogin(userName);
        if (Objects.nonNull(updateUser.getFirstname())) {
            userEntity.setFirstname(updateUser.getFirstname());
        }
        if (Objects.nonNull(updateUser.getLastname())) {
            userEntity.setLastname(updateUser.getLastname());
        }
        if (Objects.nonNull(updateUser.getPhone())) {
            userEntity.setPhone(updateUser.getPhone());
        }
        userRepository.save(userEntity);
        return userMapper.toUserUpdate(userEntity);
    }

    @Override
    public void setImage(MultipartFile image, String userName) {
        UserEntity userEntity = findByLogin(userName);
        ImageUserEntity imageEntity = new ImageUserEntity();
        imageEntity.setMediaType(image.getContentType());
        imageEntity.setFileSize(image.getSize());
        try {
            imageEntity.setData(image.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        imageUserService.save(imageEntity);
        imageEntity.setUrl(url + imageEntity.getId());
        userEntity.setImage(imageEntity);
        userRepository.save(userEntity);
    }

    private UserEntity findByLogin(String login) {
        return userRepository.findByLogin(login)
                .orElseThrow(() -> new UserNotFoundException("пользователь с логином %s не найден"
                        .formatted(login)));
    }
}
