package com.crystal.hangman.dao;


import com.crystal.path.ResourcesPath;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class GameProperties {
    private static final String PATH = ResourcesPath.getResourcePathAsString(GameProperties.class) + "application.properties";

    private GameProperties() {

    }

    public static int getAllowedMistakes() {
        try (InputStream inStream = new FileInputStream(PATH)) {
            var properties = new Properties();
            properties.load(inStream);
            return Integer.parseInt(properties.getProperty("AllowedMistakes"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
