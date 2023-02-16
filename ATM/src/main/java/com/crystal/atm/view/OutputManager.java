package com.crystal.atm.view;

public interface OutputManager {

    void showMessage(String message);

    void showLabel(String message);

    void showMessage(String message, String color);

    void showErrMessage(String message);

    void hr();

    void br();
}
