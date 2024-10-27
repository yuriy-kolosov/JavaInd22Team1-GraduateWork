package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;

import jakarta.validation.Valid;
import ru.skypro.homework.service.UserService;

@Tag(name = "Пользователи")
@RestController
@RequiredArgsConstructor
@RequestMapping("users")
public class UserController {


    private final UserService userService;

    @Operation(summary = "Обновление пароля", tags = "Пользователи",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = NewPassword.class)
                            )),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized"
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Forbidden"
                    )
            })
    @PostMapping("set_password")
    public ResponseEntity<Void> setNewPassword(@Valid @RequestBody NewPassword newPassword, Authentication authentication) {
        userService.setNewPassword(newPassword, authentication);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(summary = "Получение информации об авторизованном пользователе", tags = "Пользователи",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = User.class)
                            )),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized"
                    )
            })
    @GetMapping("me")
    public ResponseEntity<User> getInfo(Authentication authentication) {
        User user= userService.getInfo(authentication);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @Operation(summary = "Обновление информации об авторизованном пользователе", tags = "Пользователи",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = UpdateUser.class)
                            )),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized"
                    )
            })
    @PatchMapping("me")
    public ResponseEntity<UpdateUser> changeUser(@Valid @RequestBody UpdateUser updateUser, Authentication authentication) {
        UpdateUser user = userService.changeUser(updateUser, authentication);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @Operation(summary = "Обновление аватара авторизованного пользователя", tags = "Пользователи",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK",
                            content = @Content(
                                    mediaType = MediaType.MULTIPART_FORM_DATA_VALUE,
                                    schema = @Schema(implementation = String.class)
                            )),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized"
                    )
            })
    @PatchMapping("me/image")
    public ResponseEntity<Void> setImage(String image, Authentication authentication) {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
