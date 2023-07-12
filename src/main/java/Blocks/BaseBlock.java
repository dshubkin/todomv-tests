package Blocks;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import utils.ChromeWebDriver;
import utils.Waiter;

public class BaseBlock {
    protected ChromeWebDriver driver;

    public BaseBlock(ChromeWebDriver driver) {
        this.driver = driver;
    }

    public WebElement getElementFromList(By locator, int index) {
        return driver.findElements(locator).get(index);
    }

    public String getText(By locator) {
        return driver.findElement(locator).getText();
    }

    public int getElementCount(By locator) {
        return driver.findElements(locator).size();
    }

    public String getAttributeValue(By locator, String attributeName) {
        return driver.findElement(locator).getAttribute(attributeName);
    }

    public void click(By locator) {
        driver.findElement(locator).click();
        Waiter.waitForPageLoaded();
    }

    public void click(WebElement element) {
        element.click();
        Waiter.waitForPageLoaded();
    }

    public void doubleClick(WebElement element) {
        Actions actions = new Actions(driver);
        actions.doubleClick(element).build().perform();
        Waiter.waitForPageLoaded();
    }

    public void moveToElementAndClick(WebElement element, By locator, int index) {
        moveToElement(element);
        click(getElementFromList(locator, index));
        Waiter.waitForPageLoaded();
    }
    
    public void moveToElement(WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).build().perform();
        Waiter.waitForPageLoaded();
    }

    public void sendKeysAndSubmit(By locator, String text) {
        sendKeys(locator, text);
        sendKeys(locator, String.valueOf(Keys.RETURN));
        Waiter.waitForPageLoaded();
    }
    
    public void sendKeys(By locator, String text) {
        driver.findElement(locator).sendKeys(text);
    }

    public void clearInput(WebElement element, int length) {
        Actions actions = new Actions(driver);
        element.click();
        for (int i = 0; i < length; i++) {
            actions.sendKeys(Keys.BACK_SPACE).build().perform();
        }
    }

    public Boolean isElementCreated(By locator) {
        try {
            Waiter.waitForElementCreated(locator);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public Boolean isElementSelected(By locator) {
        return getAttributeValue(locator, "class").contains("selected");
    }
}
