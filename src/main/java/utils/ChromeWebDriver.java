package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class ChromeWebDriver extends ChromeDriver {
    private static ChromeWebDriver driver;

    private ChromeWebDriver() {}

    public static ChromeWebDriver getInstance() {
        if (Objects.isNull(driver)) {
            System.setProperty(ConfProperties.getProperty("driver"), ConfProperties.getProperty("driverPlace"));
            driver = new ChromeWebDriver();
            driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);

        }
        return driver;
    }

    public static ChromeWebDriver getCurrentDriver() {
        return driver;
    }

    public WebElement findFirstElement(By locator) {
        return driver.findElements(locator)
                .stream()
                .findFirst()
                .orElse(null);
    }

    public static void shutdownDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
