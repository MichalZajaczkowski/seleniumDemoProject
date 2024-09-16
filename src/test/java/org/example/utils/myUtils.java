package org.example.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;

public class myUtils {
    private static final Logger log = LogManager.getLogger();

    private static final String[] FIRST_NAMES = {"John", "Michael", "Sarah", "Jessica", "David", "Emily", "James", "Sophie"};
    private static final String[] LAST_NAMES = {"Smith", "Johnson", "Williams", "Brown", "Jones", "Miller", "Davis", "Garcia"};
    private static final String[] COMPANY_NAMES = {"TechCorp", "SoftSolutions", "MegaMarket", "GreenEnergy", "BlueSky", "FastTrack", "SmartApps"};
    private static final String[] COUNTRIES = {"Poland"};//, "Germany", "France", "United Kingdom (UK)", "Canada", "Australia"};
    //TODO: do walidacji kody pocztowe dla różnych krajów
    private static final String[] STREETS = {"Main St", "Highland Ave", "Broadway", "Elm St", "Oak St", "Pine St", "Maple Ave"};
    private static final String[] CITIES = {"New York", "Warsaw", "Berlin", "Paris", "London", "Toronto", "Sydney"};

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

    // Metoda do generowania losowej nazwy firmy
    public static String generateRandomCompanyName() {
        return COMPANY_NAMES[random.nextInt(COMPANY_NAMES.length)];
    }

    // Metoda do generowania losowego kraju
    public static String generateRandomCountry() {
        return COUNTRIES[random.nextInt(COUNTRIES.length)];
    }

    // Metoda do generowania losowej ulicy z numerem
    public static String generateRandomStreet() {
        String street = STREETS[random.nextInt(STREETS.length)];
        int streetNumber = random.nextInt(999) + 1; // Losowy numer ulicy od 1 do 999
        return street + " " + streetNumber;
    }

    // Metoda do generowania losowego kodu pocztowego
    public static String generateRandomZipCode() {
        // Przykład formatu kodu pocztowego: 12345 lub 12-345 (można dostosować do lokalizacji)
        int zipCodePart1 = random.nextInt(90) + 10; // Dwu cyfrowa część
        int zipCodePart2 = random.nextInt(900) + 100; // Trzy cyfrowa część
        return zipCodePart1 + "-" + zipCodePart2;
    }

    // Metoda do generowania losowego miasta
    public static String generateRandomCity() {
        return CITIES[random.nextInt(CITIES.length)];
    }

    // Metoda do generowania losowego numeru telefonu
    public static String generateRandomPhone() {
        // Przykład formatu telefonu: +48 123 456 789
        String countryCode = "+48"; // Można dostosować, np. +1 dla USA
        int phonePart1 = random.nextInt(900) + 100; // Trzy cyfry
        int phonePart2 = random.nextInt(900) + 100; // Trzy cyfry
        int phonePart3 = random.nextInt(900) + 100; // Trzy cyfry
        return countryCode + " " + phonePart1 + " " + phonePart2 + " " + phonePart3;
    }

    // Metoda do generowania losowego numeru domu
    public static String generateRandomHouseNumber() {
        int houseNumber = random.nextInt(999) + 1; // Losowy numer domu od 1 do 999
        char houseLetter = (random.nextBoolean()) ? (char)(random.nextInt(26) + 'A') : ' '; // Losowa litera A-Z (opcjonalna)
        return houseLetter == ' ' ? String.valueOf(houseNumber) : houseNumber + "" + houseLetter;
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
