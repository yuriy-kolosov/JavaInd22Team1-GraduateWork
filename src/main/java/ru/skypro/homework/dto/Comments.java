package ru.skypro.homework.dto;

import lombok.Data;

import java.util.Collection;

@Data
public class Comments {
    private int count;
    private Collection<Comment> results;
}
