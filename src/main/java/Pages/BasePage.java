package Pages;

import utils.ChromeWebDriver;
import org.openqa.selenium.By;

public class BasePage {
    protected ChromeWebDriver driver;
    private final By H1 = new By.ByTagName("h1");
    private final By DEMO_LINK = new By.ByClassName("demo-link");

    public BasePage() {
        driver = ChromeWebDriver.getInstance();
    }

    public ChromeWebDriver getDriver() {
        return driver;
    }

    public void openUrl(String url) {
        driver.get(url);
    }

    public String getH1Text() {
        return driver.findElement(H1).getText();
    }

    public void clickOnTSDemoLink() {
        driver.findElements(DEMO_LINK).get(2).click();
    }
}
