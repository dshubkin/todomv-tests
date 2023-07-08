package Pages;

import utils.ChromeWebDriver;
import utils.ConfProperties;
import org.openqa.selenium.By;
import java.util.concurrent.TimeUnit;

abstract public class BasePage {
    protected ChromeWebDriver driver;
    private final By H1 = new By.ByTagName("h1");
    private final By DEMO_LINK = new By.ByClassName("demo-link");

    public BasePage() {
        System.setProperty(ConfProperties.getProperty("driver"), ConfProperties.getProperty("driverPlace"));
        driver = ChromeWebDriver.getInstance();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public ChromeWebDriver getDriver() {
        return driver;
    }

    public BasePage openUrl(String url) {
        driver.get(url);
        return this;
    }

    public String getH1Text() {
        return driver.findElement(H1).getText();
    }

    public void clickOnTSDemoLink() {
        driver.findElements(DEMO_LINK).get(2).click();
    }
}
