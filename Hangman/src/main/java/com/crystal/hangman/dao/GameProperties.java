package com.crystal.hangman.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class GameProperties {
    private GameProperties(){

    }
    public static int getAllowedMistakes()  {
        try (InputStream inStream = new FileInputStream("src/main/resources/application.properties")) {
            var properties = new Properties();
            properties.load(inStream);
            return Integer.parseInt(properties.getProperty("AllowedMistakes"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
