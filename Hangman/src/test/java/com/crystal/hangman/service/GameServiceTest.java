package com.crystal.hangman.service;

import com.crystal.hangman.dao.user.UserDataAccess;
import com.crystal.hangman.dao.user.UserDataAccessFromProperties;
import com.crystal.hangman.dao.word.WordDataAccess;
import com.crystal.hangman.dao.word.WordDataAccessFromFile;
import com.crystal.hangman.model.GameData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class GameServiceTest {
    private final UserDataAccess userDataAccess = new UserDataAccessFromProperties();
    private final WordDataAccess wordDataAccess = new WordDataAccessFromFile();
    private final GameService gameService = new GameService(wordDataAccess, userDataAccess);
    String word = "brick";

    @Test
    void testDashedWordLength() {
        int actual = gameService.getDashedWord(word).length();
        Assertions.assertEquals(word.length(), actual);

    }

    @Test
    void testDashedWord() {
        StringBuilder actual = gameService.getDashedWord(word);
        Assertions.assertEquals("-----", actual.toString());
    }

    @Test
    void testReplaceLetter() {
        StringBuilder dashedWord = gameService.getDashedWord(word);
        String actual = gameService.replaceLetters(word, dashedWord, 'b').toString();
        Assertions.assertEquals("b----", actual);
    }

    @Test
    void hasWon() {
        //entire word is found
        Assertions.assertTrue(gameService.hasWon(word, new StringBuilder(word)));
        //only a part of word is found
        Assertions.assertFalse(gameService.hasWon(word, new StringBuilder("br---")));
    }

    @Test
    void hasLose() {

        GameData gameData = new GameData();
        //Mistakes by default are 0, Haven't loosen yet
        Assertions.assertFalse(gameService.hasLose(gameData));

        //Making the user to lose
        gameData.setMistakes(gameData.getAllowedMistakes());

        Assertions.assertTrue(gameService.hasLose(gameData));
    }

    @Test
    void isUsedLetter() {
        GameData gameData = new GameData();
        //By default, there are no used letters at all
        Assertions.assertFalse(gameService.isUsedLetter(gameData, 'a'));
        char letter = 'b';
        gameService.addUsedLetter(gameData, letter);
        Assertions.assertTrue(gameService.isUsedLetter(gameData, letter));
    }
}