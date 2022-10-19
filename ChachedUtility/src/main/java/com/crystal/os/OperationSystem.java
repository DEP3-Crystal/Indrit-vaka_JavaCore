package com.crystal.os;

public class OperationSystem {
    private OperationSystem(){

    }
    public static String getOperatingSystem() {
        return System.getProperty("os.name");
    }
}
