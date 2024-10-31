package ru.skypro.homework.exception;

public class ImageUserNotFoundException extends RuntimeException {
    public ImageUserNotFoundException(String message) {
        super(message);
    }
}
