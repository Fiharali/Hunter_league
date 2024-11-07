package com.ali.hunter.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtil {

    private static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    public static String hashPassword(String password) {
        return passwordEncoder.encode(password);
    }
}
