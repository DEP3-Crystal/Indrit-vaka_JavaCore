package com.crystal.ramdom_person.utility;

import java.util.Scanner;

import static com.crystal.ramdom_person.io.ConsoleColors.*;
import static com.crystal.ramdom_person.io.ConsoleColors.TEXT_RESET;

public class PersonUtility {
    // TODO [1] Is it okay to keep the scanner obj so i can use that on different places (InputManager, OutputManager)?
    public static Scanner scanner = new Scanner(System.in);
    public static String yn = TEXT_BLUE + "y" + TEXT_RESET + "/" + TEXT_RED + "n" + TEXT_RESET;

}
