package Pages;

import org.openqa.selenium.Dimension;
import utils.ChromeWebDriver;
import org.openqa.selenium.By;

public class BasePage {
    private final By H1 = new By.ByTagName("h1");

    protected ChromeWebDriver driver;

    public BasePage() {
        driver = ChromeWebDriver.getInstance();
    }

    public ChromeWebDriver getDriver() {
        return driver;
    }

    public String getH1Text() {
        return driver.findElement(H1).getText();
    }

    public void setWindowSize(int width, int height) {
        driver.manage().window().setSize(new Dimension(width, height));
    }

    public void setMaxWindowSize() {
        driver.manage().window().maximize();
    }

    public void openUrl(String url) {
        driver.get(url);
    }
}
