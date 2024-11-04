package ru.skypro.homework.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.exception.UserNotFoundException;
import ru.skypro.homework.exception.WrongPassword;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.ImageUserService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private ImageUserService imageUserService;

    @Mock
    private UserMapper userMapper;

    @Mock
    private PasswordEncoder passwordEncoder;


    @InjectMocks
    private UserServiceImpl userService;

    @Test
    public void testValidSetNewPassword() {
        UserEntity userEntity = new UserEntity();
        userEntity.setPassword("password");
        userEntity.setLogin("login");

        when(userRepository.findByLogin(anyString())).thenReturn(Optional.of(userEntity));
        when(passwordEncoder.matches(anyString(), anyString())).thenReturn(true);
        when(passwordEncoder.encode(anyString())).thenReturn("newPassword");

        NewPassword newPassword = new NewPassword();
        newPassword.setNewPassword("newPassword");
        newPassword.setCurrentPassword(userEntity.getPassword());

        assertDoesNotThrow(() -> userService.setNewPassword(newPassword, "login"));

        verify(userRepository).save(userEntity);

    }

    @Test
    public void testInvalidLogin() {
        String login = "login";

        NewPassword newPassword = new NewPassword();
        newPassword.setNewPassword("newPassword");
        newPassword.setCurrentPassword("password");

        when(userRepository.findByLogin(anyString())).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.setNewPassword(newPassword, login));
    }

    @Test
    public void testInvalidCurrentPassword() {
        UserEntity userEntity = new UserEntity();
        userEntity.setPassword("password");
        userEntity.setLogin("login");

        NewPassword newPassword = new NewPassword();
        newPassword.setNewPassword("newPassword");
        newPassword.setCurrentPassword("password");


        when(userRepository.findByLogin(anyString())).thenReturn(Optional.of(userEntity));
        when(passwordEncoder.matches(anyString(), anyString())).thenReturn(false);

        assertThrows(WrongPassword.class, () -> userService.setNewPassword(newPassword, userEntity.getLogin()));
    }

    @Test
    public void testValidGetInfo() {
        String userName = "login";
        UserEntity userEntity = new UserEntity();

        User user = new User();

        when(userRepository.findByLogin(anyString())).thenReturn(Optional.of(userEntity));
        when(userMapper.toUserDto(userEntity)).thenReturn(user);

        assertDoesNotThrow(() -> userService.getInfo(userName));
        assertEquals(user, userService.getInfo(userName));
    }

    @Test
    public void testInvalidUserNameGetInfo() {
        String userName = "login";
        UserEntity userEntity = new UserEntity();

        User user = new User();

        when(userRepository.findByLogin(anyString())).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.getInfo(userName));
    }

    @Test
    public void testValidChangeUser() {
        String login = "login";
        UserEntity userEntity = new UserEntity();
        userEntity.setPassword("password");
        userEntity.setLogin("login");

        UpdateUser updateUser = new UpdateUser();
        updateUser.setFirstname("newFirstName");
        updateUser.setLastname("newLastName");
        updateUser.setPhone("+79139130000");

        when(userRepository.findByLogin(anyString())).thenReturn(Optional.of(userEntity));
        when(userRepository.save(any())).thenReturn(userEntity);
        when(userMapper.toUserUpdate(any())).thenReturn(updateUser);

        assertDoesNotThrow(() -> userService.changeUser(updateUser, login));
        assertEquals(updateUser, userService.changeUser(updateUser, login));
    }

    @Test
    public void testInvalidUser() {
        String login = "login";

        UpdateUser updateUser = new UpdateUser();
        updateUser.setFirstname("newFirstName");
        updateUser.setLastname("newLastName");
        updateUser.setPhone("+79139130000");

        when(userRepository.findByLogin(anyString())).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.changeUser(updateUser, login));
    }

    @Test
    public void testValidSetImage() {
        String login = "login";
        UserEntity userEntity = new UserEntity();
        userEntity.setPassword("password");
        userEntity.setLogin("login");

        ReflectionTestUtils.setField(userService, "url", "123");

        MultipartFile multipartFile = new MockMultipartFile("multiPartFileName", new byte[]{123});
        when(userRepository.findByLogin(anyString())).thenReturn(Optional.of(userEntity));

        assertDoesNotThrow(()->  userService.setImage(multipartFile, login));
        verify(imageUserService).save(any());
        verify(userRepository).save(any());
    }
}