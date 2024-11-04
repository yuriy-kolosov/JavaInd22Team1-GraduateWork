package ru.skypro.homework.service.impl;

import jakarta.servlet.http.HttpServletRequest;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.skypro.homework.controller.AdController;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.repository.AdImageRepository;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.UserRepository;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static ru.skypro.homework.constants.AdServiceTestImpConstants.*;

@WebMvcTest(AdController.class)
public class AdServiceImplTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    SecurityFilterChain filterChain;

    @MockBean
    HttpServletRequest httpServletRequest;

    @MockBean
    UserDetailsService userDetailsService;

    @MockBean
    PasswordEncoder passwordEncoder;

    @MockBean
    private AdRepository adRepository;

    @MockBean
    private AdImageRepository adImageRepository;

    @MockBean
    private UserRepository userRepository;

    @SpyBean
    private AdServiceImpl adServiceImpl;

    @InjectMocks
    private AdController adController;

    Logger logger = LoggerFactory.getLogger(AdServiceImplTest.class);

    @Test
    @WithMockUser(roles = "USER")
    void getAds() throws Exception {
//                                                              Подготовка
        when(adRepository.findAll()).thenReturn(LOTS);

        JSONObject adObject1 = new JSONObject();
        adObject1.put("author", LOT1_DTO_AUTHOR);
        adObject1.put("image", LOT1_DTO_IMAGE);
        adObject1.put("pk", LOT1_DTO_PK);
        adObject1.put("price", LOT1_PRICE);
        adObject1.put("title", LOT1_TITLE);

        JSONObject adObject2 = new JSONObject();
        adObject2.put("author", LOT2_DTO_AUTHOR);
        adObject2.put("image", LOT2_DTO_IMAGE);
        adObject2.put("pk", LOT2_DTO_PK);
        adObject2.put("price", LOT2_PRICE);
        adObject2.put("title", LOT2_TITLE);

        JSONArray adJsonArray = new JSONArray();
        adJsonArray.put(adObject1);
        adJsonArray.put(adObject2);

        JSONObject adsObject = new JSONObject();
        adsObject.put("count", LOTS_DTO_COUNT);
        adsObject.put("results", adJsonArray);
//                                                              Выполнение
        mockMvc.perform(MockMvcRequestBuilders
                        .get(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().json(adsObject.toString()));
    }

    @Test
    @WithMockUser(roles = "USER")
    void getExtendedAd() throws Exception {
//                                                              Подготовка
        AdEntity expectedAdEntity = LOT1;
        UserEntity expectedUserEntity = USER1;

        when(adRepository.findById(any(Long.class))).thenReturn(Optional.of(expectedAdEntity));
        when(userRepository.findById(any(Long.class))).thenReturn(Optional.of(expectedUserEntity));

        JSONObject extendedAdObject = new JSONObject();
        extendedAdObject.put("pk", LOT1_DTO_PK);
        extendedAdObject.put("authorFirstName", USER1_DTO_FIRSTNAME);
        extendedAdObject.put("authorLastName", USER1_DTO_LASTNAME);
        extendedAdObject.put("description", LOT1_DESCRIPTION);
        extendedAdObject.put("email", USER1_DTO_EMAIL);
        extendedAdObject.put("image", LOT1_DTO_IMAGE);
        extendedAdObject.put("phone", USER1_DTO_PHONE);
        extendedAdObject.put("price", LOT1_PRICE);
        extendedAdObject.put("title", LOT1_TITLE);
//                                                              Выполнение
        mockMvc.perform(MockMvcRequestBuilders
                        .get(url + "/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().json(extendedAdObject.toString()));
    }

    @Test
    @WithMockUser(roles = "USER")
    void delete() throws Exception {
//                                                              Подготовка
        logger.debug("\"DELETE\" delete test method was invoke...");

        when(filterChain.matches(httpServletRequest)).thenReturn(true);
        doNothing().when(adRepository).delete(any(AdEntity.class));
//                                                              Выполнение
        mockMvc.perform(MockMvcRequestBuilders
                        .delete(url + "/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}
