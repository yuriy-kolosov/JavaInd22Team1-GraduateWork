package ru.skypro.homework.service;

import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.entity.UserEntity;

public interface UserService {
    void setNewPassword(@Valid NewPassword newPassword, Authentication authentication);

    User getInfo(Authentication authentication);

    UpdateUser changeUser(@Valid UpdateUser updateUser, Authentication authentication);
}
