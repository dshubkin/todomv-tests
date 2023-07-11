package utils;

import io.qameta.allure.Allure;

public class Logger {
    public static void log(String text) {
        Allure.step(text);
    }
}
