//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.crystal.atm;

import com.crystal.atm.dao.DataAccess;
import com.crystal.atm.dao.DataFromMemory;
import com.crystal.atm.services.UserService;
import com.crystal.atm.view.ConsoleColors;
import com.crystal.atm.view.InputManager;
import com.crystal.atm.view.InputManagerCli;
import com.crystal.atm.view.OutputManager;
import com.crystal.atm.services.LogService;
import com.crystal.atm.services.account.AccountService;
import com.crystal.atm.view.Menu;

import static com.crystal.atm.view.ConsoleColors.TEXT_BG_BLUE;
import static com.crystal.atm.view.ConsoleColors.TEXT_BG_YELLOW;

public class Main {

    public static void main(String[] args) {
        OutputManager outputManager = new OutputManager();
        InputManager inputManager = new InputManagerCli(outputManager);
        UserService userService = new UserService();
        AccountService accountService = new AccountService();
        try {
            DataAccess dataAccess = new DataFromMemory();
            outputManager.showMessage(TEXT_BG_YELLOW
                            + "\t\t\t"
                            + TEXT_BG_BLUE
                            + "  Welcome To ATM  "
                            + TEXT_BG_YELLOW
                            + "\t\t\t",
                    TEXT_BG_BLUE + ConsoleColors.TEXT_BLACK);

            Menu menu = new Menu(dataAccess, userService, accountService, outputManager, inputManager);
            menu.showMenu();
        } catch (Exception var2) {
            LogService.registerException(var2);
        }

    }
}
