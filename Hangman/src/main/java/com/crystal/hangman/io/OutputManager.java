package com.crystal.hangman.io;

import java.util.Scanner;

public class OutputManager {
    public static int showMenu(Scanner scanner) {
        hr();
        System.out.println("""
                Welcome to Hangman Game!
                Please press:
                    1. to start the game
                    2. to view the game rules
                    3. 
                """);
        hr();
        return InputManager.getInt(scanner);
    }
    //TODO: Finishing the game rules
    public static void showGameRules(){
        hr();
        System.out.println("""
                A random word will be chosen,
                You have to guess right word by suggesting different letters.
                We will give you hints 
                """);
    }


    public static void loseMessage() {
        OutputManager.br();
        OutputManager.hr();
        OutputManager.showMessage(
                ConsoleColors.TEXT_BG_YELLOW + "\t\t>_<\t"
                        + ConsoleColors.TEXT_BG_RED
                        + "  You lose the game  "
                        + ConsoleColors.TEXT_BG_YELLOW + "\t>_<\t\t\t"
                        , ConsoleColors.TEXT_BLACK);
    }

    public static void winMessage() {
        showMessage(ConsoleColors.TEXT_BG_YELLOW
                        + "\t\t*_*\t"
                        + ConsoleColors.TEXT_BG_BLUE
                        + "  Congratulations you have won this round  "
                        + ConsoleColors.TEXT_BG_YELLOW
                        + "\t*_*\t\t",
                ConsoleColors.TEXT_BG_BLUE + ConsoleColors.TEXT_BLACK);
        hr();
    }

    public static void showMessage(String message) {
        System.out.println(message + ConsoleColors.TEXT_RESET);
    }

    public static void showMessage(String message, String color) {
        System.out.println(color + message + ConsoleColors.TEXT_RESET);
    }

    public static void showErrMessage(String message) {
        System.out.println(ConsoleColors.TEXT_RED + message);
        hr();
    }

    public static void hr() {
        System.out.println(ConsoleColors.TEXT_RESET + "==============================================" + ConsoleColors.TEXT_RESET);
    }

    public static void br() {
        System.out.println();
    }
}
