package com.crystal.hangman.dao;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LoadWords {
    public static Map<String, String> loadData(Path path) {
        Map<String, String> result = null;
        try (Stream<String> lines = Files.lines(path)) {
            result = lines.collect(Collectors.toMap(v -> v.split(";")[0], v2 -> v2.split(";")[1]));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
