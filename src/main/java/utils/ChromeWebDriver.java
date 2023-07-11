package utils;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class ChromeWebDriver extends ChromeDriver {

    private static ChromeWebDriver MY_DRIVER;

    private ChromeWebDriver() {}

    public static ChromeWebDriver getInstance() {
        if (Objects.isNull(MY_DRIVER)) {
            System.setProperty(ConfProperties.getProperty("driver"), ConfProperties.getProperty("driverPlace"));
            MY_DRIVER = new ChromeWebDriver();
            MY_DRIVER.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);

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
