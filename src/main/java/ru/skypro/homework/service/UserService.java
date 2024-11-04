package ru.skypro.homework.service;

import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;

public interface UserService {
    void setNewPassword(NewPassword newPassword, String userName);

    User getInfo(String userName);

    UpdateUser changeUser(UpdateUser updateUser, String userName);

    void setImage(MultipartFile image, String userName);
}
