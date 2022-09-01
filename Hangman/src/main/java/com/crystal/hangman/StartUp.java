package com.crystal.hangman;

import com.crystal.hangman.ai.HangmanBoot;

public class StartUp {
    public static void main(String[] args) {
        HangmanBoot hangmanBoot = new HangmanBoot();
        hangmanBoot.menu();
    }
}
