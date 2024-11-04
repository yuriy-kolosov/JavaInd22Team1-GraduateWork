package ru.skypro.homework.constants;

import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.UserEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class AdServiceTestImpConstants {

    public static final String url = "http://localhost:8080/ads";

    public static final Long LOT1_ID = 1L;
    public static final Long LOT1_AUTHOR = 1L;
    public static final BigDecimal LOT1_PRICE = new BigDecimal(100);
    public static final String LOT1_TITLE = "Lot00001";
    public static final String LOT1_DESCRIPTION = "Лот_номер_1";

    public static final Long LOT2_ID = 2L;
    public static final Long LOT2_AUTHOR = 2L;
    public static final BigDecimal LOT2_PRICE = new BigDecimal(200);
    public static final String LOT2_TITLE = "Lot00002";
    public static final String LOT2_DESCRIPTION = "Лот_номер_2";

    public static final AdEntity LOT1 = new AdEntity(LOT1_ID, LOT1_AUTHOR, LOT1_PRICE, LOT1_TITLE, LOT1_DESCRIPTION);
    public static final AdEntity LOT2 = new AdEntity(LOT2_ID, LOT2_AUTHOR, LOT2_PRICE, LOT2_TITLE, LOT2_DESCRIPTION);

    public static final List<AdEntity> LOTS = List.of(LOT1, LOT2);

    public static final int LOTS_DTO_COUNT = 2;

    public static final int LOT1_DTO_AUTHOR = 1;
    public static final String LOT1_DTO_IMAGE = "/ads/get_image/1";
    public static final int LOT1_DTO_PK = 1;

    public static final Long USER1_ID = 1L;
    public static final String USER1_FIRSTNAME = "Ivan";
    public static final String USER1_SURNAME = "Ivanovich";
    public static final String USER1_LASTNAME = "Ivanov";
    public static final String USER1_LOGIN = "user1@test.com";
    public static final String USER1_PHONE = "+7 901-01-01";
    public static final LocalDateTime USER1_REGISTRATION_DATE = LocalDateTime.MAX;
    public static final String USER1_EMAIL = "user1@test.com";

    public static final String USER1_ROLE = "USER";
    public static final String USER1_PASSWORD = "user1test";

    public static final UserEntity USER1 = new UserEntity(USER1_ID, USER1_FIRSTNAME, USER1_SURNAME, USER1_LASTNAME, USER1_LOGIN
            , USER1_PHONE, USER1_REGISTRATION_DATE, USER1_EMAIL, USER1_ROLE, USER1_PASSWORD);

    public static final String USER1_DTO_FIRSTNAME = "Ivan";
    public static final String USER1_DTO_LASTNAME = "Ivanov";
    public static final String USER1_DTO_EMAIL = "user1@test.com";
    public static final String USER1_DTO_PHONE = "+7 901-01-01";

    public static final int LOT2_DTO_AUTHOR = 2;
    public static final String LOT2_DTO_IMAGE = "/ads/get_image/2";
    public static final int LOT2_DTO_PK = 2;

}
