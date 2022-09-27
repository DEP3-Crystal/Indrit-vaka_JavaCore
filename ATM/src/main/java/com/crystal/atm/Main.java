//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.crystal.atm;

import com.crystal.atm.dao.DataAccess;
import com.crystal.atm.dao.DataFromMemory;
import com.crystal.atm.io.ConsoleColors;
import com.crystal.atm.io.OutputManager;
import com.crystal.atm.services.LogService;

import static com.crystal.atm.io.ConsoleColors.TEXT_BG_BLUE;
import static com.crystal.atm.io.ConsoleColors.TEXT_BG_YELLOW;

public class Main {

    public static void main(String[] args) {
        try {
            DataAccess dataAccess = new DataFromMemory();
            OutputManager.showMessage(TEXT_BG_YELLOW
                            + "\t\t\t"
                            + TEXT_BG_BLUE
                            + "  Welcome To ATM  "
                            + TEXT_BG_YELLOW
                            + "\t\t\t",
                    TEXT_BG_BLUE + ConsoleColors.TEXT_BLACK);
            Menu menu = new Menu(dataAccess);
            menu.showMenu();
        } catch (Exception var2) {
            LogService.registerException(var2);
        }

    }
}
