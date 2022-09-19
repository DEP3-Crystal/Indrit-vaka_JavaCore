package com.crystal.hangman.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class GameProperties {
    public static int getAllowedMistakes() throws IOException {
        InputStream inStream = new FileInputStream("src/main/resources/application.properties");
        var properties = new Properties();
        properties.load(inStream);
        return Integer.parseInt(properties.getProperty("AllowedMistakes"));
    }
}
