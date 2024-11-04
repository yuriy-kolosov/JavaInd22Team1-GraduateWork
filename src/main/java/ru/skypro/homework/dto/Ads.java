package ru.skypro.homework.dto;

import lombok.Data;

import java.util.List;

@Data
public class Ads {
    //                                Все объявления (DTO)
    private int count;            //  Общее количество объявлений
    private List<Ad> results;     //  Коллекция объявлений

    public Ads() {
    }

    public Ads(int count, List<Ad> results) {
        this.count = count;
        this.results = results;
    }

}
