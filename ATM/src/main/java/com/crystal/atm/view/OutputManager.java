package com.crystal.atm.view;

public class OutputManager {

    public void showMessage(String message) {
        System.out.println(message + ConsoleColors.TEXT_RESET);
    }

    public void showLabel(String message) {
        System.out.print( message + ConsoleColors.TEXT_RESET);
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
