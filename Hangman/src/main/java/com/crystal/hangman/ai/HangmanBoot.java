package com.crystal.hangman.ai;

import com.crystal.hangman.dao.user.UserDataAccess;
import com.crystal.hangman.dao.word.WordDataAccess;
import com.crystal.hangman.io.InputManager;
import com.crystal.hangman.io.OutputManager;
import com.crystal.hangman.model.GameData;
import com.crystal.hangman.model.GameState;
import com.crystal.hangman.model.User;
import com.crystal.hangman.service.GameService;

import java.util.HashSet;
import java.util.Set;

import static com.crystal.hangman.io.ConsoleColors.*;

public class HangmanBoot {


    private final InputManager inputManager = InputManager.getInstance();
    private final OutputManager outputManager = OutputManager.getInstance();
    private final UserDataAccess userDataAccess;
    private final GameService gameService ;

    public HangmanBoot(WordDataAccess wordDataAccess, UserDataAccess userDataAccess) {
        this.userDataAccess = userDataAccess;
        gameService = new GameService(wordDataAccess);
    }


    public void startTheGame(User user) {

        GameData gameData = new GameData();

        Set<Character> usedLetters = new HashSet<>();

        outputManager.hr();
        outputManager.showMessage("\t\t\t" + user.getNickName() + " Welcome to Hang Man game!");
        String givenWord = gameService.getNextLevel();
        String definition = gameService.getDefinition(givenWord);

        StringBuilder inputtedWord = new StringBuilder();
        inputtedWord.append(givenWord.replaceAll("\\w", "-"));


        while (gameData.getGameState() == GameState.IN_PROGRESS) {
            outputManager.hr();
            outputManager.showMessage("Please guess a letter: " + TEXT_BLUE + "0. back to menu");
            outputManager.showMessage(TEXT_BLUE + "Hint: " + TEXT_GREEN + definition);

            outputManager.showMessage(inputtedWord + "", TEXT_BLUE);

            char answer = inputManager.getLetter().toLowerCase().charAt(0);
            if (answer == '0') {
                return;
            }

            if (givenWord.contains(answer + "")) {
                inputtedWord = gameService.replaceLetters(givenWord, inputtedWord, answer);
                gameData.incrementScore();

                if (gameService.hasWon(givenWord, inputtedWord)) {
                    outputManager.winMessage();
                    gameData.won();
                } else {
                    outputManager.showMessage("used letters" + usedLetters);
                }

            } else if (gameService.isUsedLetter(usedLetters, answer)) {
                outputManager.showMessage("You already have used that letter", TEXT_BLUE);
            } else {
                outputManager.showErrMessage("The given letter isn't in the word");
                gameData.wrongAnswer();
                if (gameService.hasLose(gameData)) {
                    gameData.lose();
                    outputManager.loseMessage();
                } else {
                    outputManager.showMessage("You have " + TEXT_RED + gameData.getLeftChances() + TEXT_RESET + " chances");
                }
            }
            usedLetters.add(answer);
        }
        showGameResults(user, gameData);
        gameEnded(user, gameData);
    }

    private void showGameResults(User user, GameData gameData) {
        outputManager.showMessage("High score: " + TEXT_BLUE + user.getHighScore());
        outputManager.showMessage("Score: " + TEXT_BLUE + gameData.getScore());
        outputManager.showMessage("Mistakes: " + TEXT_RED + gameData.getMistakes());
    }


    private void gameEnded(User user, GameData gameData) {
        outputManager.showMessage(TEXT_BLUE + "Do you want to play an other game?"
                + TEXT_RED + " Y/N");
        String ans = inputManager.getLetter();
        if (ans.equals("y"))
            startNewGame(gameData);
        else if (!ans.equals("n")) {
            outputManager.showErrMessage("wrong answer" + TEXT_RED + " Y/N");
        } else {
            userDataAccess.addUser(user);
        }

    }

    private void startNewGame(GameData gameData) {
        gameService.resetGame(gameData);

    }


}
