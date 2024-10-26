package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.exception.UserNotFoundException;
import ru.skypro.homework.exception.WrongPassword;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.UserService;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;


    @Override
    public void setNewPassword(NewPassword newPassword, Authentication authentication) {
        String login = authentication.getName();
        UserEntity user = findByLogin(login);
        if (!user.getPassword().contentEquals(newPassword.getCurrentPassword())) {
            throw new WrongPassword("не верный пароль");
        }
        user.setPassword(newPassword.getNewPassword());
        userRepository.save(user);
    }

    @Override
    public User getInfo(Authentication authentication) {
        String login = authentication.getName();
        UserEntity userEntity = findByLogin(login);
        return userMapper.toUserDto(userEntity);
    }

    @Override
    public UpdateUser changeUser(UpdateUser updateUser, Authentication authentication) {
        String login = authentication.getName();
        UserEntity userEntity = findByLogin(login);
        return userMapper.toUserUpdate(userEntity);
    }

    private UserEntity findByLogin(String login){
        return userRepository.findByLogin(login)
                .orElseThrow(() -> new UserNotFoundException("пользователь с логином %s не найден".formatted(login)));
    }
}
