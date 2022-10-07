//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.crystal.atm.services;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class LogService {
    public LogService() {
    }

    public static void registerException(Exception e) {
        try (var file = new FileWriter("ATM/src/main/resources/log.txt",true)) {
            file.write(e.toString());
        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        throw new RuntimeException("method not implemented yet");
    }
}
