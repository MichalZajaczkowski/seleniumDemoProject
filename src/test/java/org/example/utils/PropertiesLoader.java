package org.example.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesLoader {

    private static final Properties properties = new Properties();

    static {
        try (InputStream input = PropertiesLoader.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new RuntimeException("Sorry, unable to find config.properties");
            }
            properties.load(input);
        } catch (IOException ex) {
            throw new RuntimeException("Failed to load properties file", ex);
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    public static int getIntProperty(String key) {
        String value = properties.getProperty(key);
        if (value != null) {
            return Integer.parseInt(value);
        } else {
            throw new IllegalArgumentException("Property " + key + " is not found.");
        }
    }
}
