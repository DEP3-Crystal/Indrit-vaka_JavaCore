//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.crystal.atm;

import com.crystal.atm.dao.DataAccess;
import com.crystal.atm.dao.DataFromMemory;
import com.crystal.atm.services.LogService;
import com.crystal.atm.services.UserService;
import com.crystal.atm.services.account.AccountService;
import com.crystal.atm.services.account.CardService;
import com.crystal.atm.view.*;

import static com.crystal.atm.view.ConsoleColors.TEXT_BG_BLUE;
import static com.crystal.atm.view.ConsoleColors.TEXT_BG_YELLOW;

public class Main {

    public static void main(String[] args) {
        OutputManager outputManager = new OutputManagerCli();
        InputManager inputManager = new InputManagerCli(outputManager);
        UserService userService = new UserService();
        AccountService accountService = new AccountService();
        CardService cardService = new CardService();
        try {
            DataAccess dataAccess = new DataFromMemory();
            outputManager.showMessage(TEXT_BG_YELLOW
                            + "\t\t\t"
                            + TEXT_BG_BLUE
                            + "  Welcome To ATM  "
                            + TEXT_BG_YELLOW
                            + "\t\t\t",
                    TEXT_BG_BLUE + ConsoleColors.TEXT_BLACK);

            Menu menu = new Menu(dataAccess, userService, accountService, outputManager, inputManager, cardService);
            menu.showMenu();
        } catch (Exception e) {
            LogService.registerException(e);
        }
        // TODO dataAccess form DB
        // TODO Spring boot


    }
}
