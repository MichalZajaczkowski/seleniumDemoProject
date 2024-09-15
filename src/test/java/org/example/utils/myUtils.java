package org.example.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;

public class myUtils {
    private static final Logger log = LogManager.getLogger();

    private static final String[] FIRST_NAMES = {"John", "Michael", "Sarah", "Jessica", "David", "Emily", "James", "Sophie"};
    private static final String[] LAST_NAMES = {"Smith", "Johnson", "Williams", "Brown", "Jones", "Miller", "Davis", "Garcia"};
    private static final String UPPER_CASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWER_CASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String DIGITS = "0123456789";
    private static final String SPECIAL_CHARACTERS = "!@#$%^&*()-_=+{}[]|:;<>?,./";
    private static final String ALL_CHARACTERS = UPPER_CASE + LOWER_CASE + DIGITS + SPECIAL_CHARACTERS;
    private static final String DOMAIN = "@example.com";
    private static final Random random = new Random();

    // Metoda do generowania losowego imienia
    public static String generateRandomFirstName() {
        return FIRST_NAMES[random.nextInt(FIRST_NAMES.length)];
    }

    // Metoda do generowania losowego nazwiska
    public static String generateRandomLastName() {
        return LAST_NAMES[random.nextInt(LAST_NAMES.length)];
    }

    // Metoda do generowania losowego poprawnego adresu e-mail
    public static String generateRandomEmail() {
        String firstName = generateRandomFirstName().toLowerCase();
        String lastName = generateRandomLastName().toLowerCase();
        int number = random.nextInt(1000); // Dodaj losowy numer na końcu, aby uniknąć powtórzeń
        return firstName + "." + lastName + number + DOMAIN;
    }

    // Metoda do generowania losowego hasła
    public static String generateRandomPassword() {
        int passwordLength = 8 + random.nextInt(9); // Losowa długość od 8 do 16 znaków
        StringBuilder password = new StringBuilder();

        // Dodaj losowo wybrane znaki do hasła
        for (int i = 0; i < passwordLength; i++) {
            char randomChar = ALL_CHARACTERS.charAt(random.nextInt(ALL_CHARACTERS.length()));
            password.append(randomChar);
        }

        return password.toString();
    }

    // Metoda do generowania silnego hasła
    public static String generateStrongPassword() {
        String password;
        do {
            password = generateRandomPassword();
        } while (!isPasswordStrong(password));
        return password;
    }

    // Metoda do sprawdzania siły hasła
    public static boolean isPasswordStrong(String password) {
        boolean hasUpperCase = !password.equals(password.toLowerCase());
        boolean hasLowerCase = !password.equals(password.toUpperCase());
        boolean hasDigit = password.matches(".*\\d.*");
        boolean hasSpecialChar = password.matches(".*[!@#$%^&*(),.?\":{}|<>].*");
        return password.length() >= 12 && hasUpperCase && hasLowerCase && hasDigit && hasSpecialChar;
    }
}
