package com.crystal.hangman.dao.word;

import com.crystal.path.ResourcesPath;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WordDataAccessFromFile implements WordDataAccess {
    Map<String, String> words;

    @Override
    public Map<String, String> getWords() {
        if (words == null) {
            words = loadWords();
        }
        return words;
    }

    public static Map<String, String> loadWords() {
        Map<String, String> result;
        String pathAsString = ResourcesPath.getResourcePathAsString(WordDataAccessFromFile.class) + "HangmanWords.txt";
        try (Stream<String> lines = Files.lines(Path.of(pathAsString))) {
            result = lines.collect(Collectors.toMap(v -> v.split(";")[0], v2 -> v2.split(";")[1]));
        } catch (IOException e) {
            e.printStackTrace();
            result = null;
        }
        return result;
    }

}
