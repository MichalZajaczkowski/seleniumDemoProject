package org.example.utils;

import lombok.Getter;


public class TestData {
    @Getter
    private static String email;
    @Getter
    private static String password;

    public static void setEmail(String email) {
        TestData.email = email;
    }

    public static void setPassword(String password) {
        TestData.password = password;
    }
}
