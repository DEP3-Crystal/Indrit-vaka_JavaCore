package com.crystal.hangman.model;

import com.crystal.hangman.dao.GameProperties;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class GameData {
    private final int allowedMistakes;
    private GameState gameState = GameState.IN_PROGRESS;
    private int mistakes;
    private int score = 0;
    private final Set<Character> usedLetters = new HashSet<>();

    public GameData() {
        allowedMistakes = GameProperties.getAllowedMistakes();
    }

    public int getLeftChances() {
        return allowedMistakes - mistakes;
    }


    public void incrementScore() {
        score++;
    }

    public void wrongAnswer() {
        mistakes++;
    }

    public void resetData() {
        gameState = GameState.IN_PROGRESS;
        usedLetters.clear();
        score = 0;
        mistakes = 0;
    }

    public void won() {
        gameState = GameState.WON;
    }

    public void lose() {
        gameState = GameState.LOSE;
    }


}
