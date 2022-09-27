package com.crystal.atm.io;

public class OutputManager {

    public static void showMessage(String message) {
        System.out.println(message + ConsoleColors.TEXT_RESET);
    }

    public static void showLabel(String message) {
        System.out.print("\t" + message + ConsoleColors.TEXT_RESET);
    }

    public static void showMessage(String message, String color) {
        System.out.println(color + message + ConsoleColors.TEXT_RESET);
    }

    public static void showErrMessage(String message) {
        System.out.println(ConsoleColors.TEXT_RED + message);
        hr();
    }

    public static void hr() {
        System.out.println(ConsoleColors.TEXT_RESET + "====================================================================" + ConsoleColors.TEXT_RESET);
    }

    public static void br() {
        System.out.println();
    }
}
