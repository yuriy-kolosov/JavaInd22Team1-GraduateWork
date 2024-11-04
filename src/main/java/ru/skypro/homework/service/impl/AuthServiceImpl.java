package ru.skypro.homework.service.impl;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AuthService;

@Service
@Log4j2
public class AuthServiceImpl implements AuthService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final UserDetailsService manager;
    private final UserRepository userRepository;

    public AuthServiceImpl(UserDetailsService manager, UserRepository userRepository) {
        this.manager = manager;
        this.userRepository = userRepository;
    }

    @Override
    public boolean login(String userName, String password) {
        try {
            UserDetails userDetails = manager.loadUserByUsername(userName);
            return passwordEncoder.matches(password, userDetails.getPassword());
        } catch (RuntimeException r) {
            return false;
        }
    }

    @Override
    public boolean register(Register register) {
        try {
            userRepository.save(register.toRegister(passwordEncoder));
            return true;
        } catch (RuntimeException r) {
            return false;
        }
    }

}
