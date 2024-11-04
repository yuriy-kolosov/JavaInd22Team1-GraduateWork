package ru.skypro.homework.dto;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class Ad {
    //                              Объявление (DTO)
    private int author;         //  id автора объявления
    private String image;       //  Ссылка на картинку объявления
    private int pk;             //  id объявления
    private BigDecimal price;          //  Цена объявления
    private String title;       //  Заголовок объявления

    public Ad(int author, String image, int pk, BigDecimal price, String title) {
        this.author = author;
        this.image = image;
        this.pk = pk;
        this.price = price;
        this.title = title;
    }

}
