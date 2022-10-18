package com.crystal.hangman.io;


public class OutputManager {
    private OutputManager(){
    }
    private static OutputManager instance;
    public static synchronized OutputManager getInstance() {
        if(instance == null){
            instance = new OutputManager();
        }
        return instance;
    }

    public void showGameRules() {
        hr();
        System.out.println("""
                A random word will be chosen,
                    You have to guess right word by suggesting different letters.
                    We will give you hints for each word.
                    For each letter you find you will get 1 score""".indent(1));
    }

    public void loseMessage() {
        br();
        hr();
        showMessage(
                ConsoleColors.TEXT_BG_YELLOW + "\t\t>_<\t"
                        + ConsoleColors.TEXT_BG_RED
                        + "  You lose the game  "
                        + ConsoleColors.TEXT_BG_YELLOW + "\t>_<\t\t\t"
                , ConsoleColors.TEXT_BLACK);
    }

    public void winMessage() {
        showMessage(ConsoleColors.TEXT_BG_YELLOW
                        + "\t\t*_*\t"
                        + ConsoleColors.TEXT_BG_BLUE
                        + "  Congratulations you have won this round  "
                        + ConsoleColors.TEXT_BG_YELLOW
                        + "\t*_*\t\t",
                ConsoleColors.TEXT_BG_BLUE + ConsoleColors.TEXT_BLACK);
        hr();
    }

    public void showMessage(String message) {
        System.out.println(message + ConsoleColors.TEXT_RESET);
    }

    public void showMessage(String message, String color) {
        System.out.println(color + message + ConsoleColors.TEXT_RESET);
    }

    public void showErrMessage(String message) {
        System.out.println(ConsoleColors.TEXT_RED + message);
        hr();
    }

    public void hr() {
        System.out.println(ConsoleColors.TEXT_RESET + "====================================================================" + ConsoleColors.TEXT_RESET);
    }

    public void br() {
        System.out.println();
    }


}
