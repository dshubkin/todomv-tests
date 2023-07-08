package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

public class BasePage {
    private final ChromeDriver driver;
    private final By H1 = new By.ByTagName("h1");
    private final By DEMO_LINK = new By.ByClassName("demo-link");

    public BasePage(ChromeDriver driver) {
        this.driver = driver;
    }

    public String getH1Text() {
        return driver.findElement(H1).getText();
    }

    public void clickOnTSDemoLink() {
        driver.findElements(DEMO_LINK).get(2).click();
    }
}
