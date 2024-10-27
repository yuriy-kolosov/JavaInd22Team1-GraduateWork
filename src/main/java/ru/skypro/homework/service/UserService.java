package ru.skypro.homework.service;

import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;

public interface UserService {
    void setNewPassword(@Valid NewPassword newPassword, Authentication authentication);

    User getInfo(Authentication authentication);

    UpdateUser changeUser(@Valid UpdateUser updateUser, Authentication authentication);
}
