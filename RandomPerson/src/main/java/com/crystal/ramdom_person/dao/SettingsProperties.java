package com.crystal.ramdom_person.dao;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class SettingsProperties {
    private static  final Properties properties = new Properties();
    private static final String path = "RandomPerson/src/main/resources/settings.properties";

  static  {
        try {
            properties.load(new FileReader(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void setChoosesFilePath(String path) {
        properties.setProperty("chosenFilePath", path);
        try {
            properties.store(new FileOutputStream(SettingsProperties.path), null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getChoosesFilePath() {
        return properties.getProperty("chosenFilePath");
    }

    public static void setPeopleFilePath(String path) {
        try {
            properties.setProperty("peopleFilePath", path);
            properties.store(new FileOutputStream(SettingsProperties.path), null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static  String getPeopleFilePath() {
        return properties.getProperty("peopleFilePath");
    }

    /**
     * @return true if is first time the app is running
     */
    public static boolean firstRun() {

        var firstRun = Boolean.parseBoolean(properties.getProperty("firstOpen", "true"));
        try {
            properties.setProperty("firstOpen", "false");
            properties.store(new FileOutputStream(SettingsProperties.path), null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return Boolean.TRUE.equals(firstRun);
    }

    public static void cleanFirstRun() {
        properties.setProperty("firstOpen", "true");
        try {
            properties.store(new FileOutputStream(SettingsProperties.path), null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
