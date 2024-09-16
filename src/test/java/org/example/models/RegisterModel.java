package org.example.models;

import lombok.Getter;


public class RegisterModel {
    @Getter
    private static String email;
    @Getter
    private static String password;

    public static void setEmail(String email) {
        RegisterModel.email = email;
    }

    public static void setPassword(String password) {
        RegisterModel.password = password;
    }
}
