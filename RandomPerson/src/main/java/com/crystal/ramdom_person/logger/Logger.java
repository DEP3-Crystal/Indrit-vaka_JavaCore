package com.crystal.hangman.logger;

import com.crystal.hangman.io.OutputManager;
import com.crystal.path.ResourcesPath;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.FileWriter;
import java.io.IOException;

public class Logger {
    private Logger() {

    }

    public static void logException(Exception ex) {
        OutputManager outputManager = OutputManager.getInstance();
        outputManager.showErrMessage(ex.getMessage());
        writeException(ex);
    }

    private static void writeException(Exception ex) {
        String path = ResourcesPath.getResourcePathAsString(Logger.class) + "log.txt";

        try (CSVPrinter printer = new CSVPrinter(new FileWriter(path),
                CSVFormat.Builder.create()
                        .setHeader("Message", "Stack Trace")
                        .build())) {

            printer.printRecord(ex.getMessage(), ex.getStackTrace());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}

