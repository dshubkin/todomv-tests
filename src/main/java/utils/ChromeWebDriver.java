package utils;

import org.openqa.selenium.chrome.ChromeDriver;
import java.util.Objects;

public class ChromeWebDriver extends ChromeDriver {

    private static ChromeWebDriver MY_DRIVER;

    private ChromeWebDriver() {}

    public static ChromeWebDriver getInstance() {
        if (Objects.isNull(MY_DRIVER)) {
            MY_DRIVER = new ChromeWebDriver();
        }
        return MY_DRIVER;
    }

    public static ChromeWebDriver getCurrentDriver() {
        return MY_DRIVER;
    }

    public static void shutdownDriver() {
        if (MY_DRIVER != null) {
            MY_DRIVER.quit();
            MY_DRIVER = null;
        }
    }
}
