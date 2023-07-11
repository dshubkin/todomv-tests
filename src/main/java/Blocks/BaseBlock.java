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

    public WebElement getElementFromList(By selector, int elementIndex) {
        return driver.findElements(selector).get(elementIndex);
    }

    public String getAttributeValue(By selector, String attributeName) {
        return driver.findElement(selector).getAttribute(attributeName);
    }

    public void click(By selector) {
        driver.findElement(selector).click();
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

    public void moveToElement(WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).build().perform();
        Waiter.waitForPageLoaded();
    }

    public void moveToElementAndClick(WebElement element, By selector, int index) {
        moveToElement(element);
        click(getElementFromList(selector, index));
        Waiter.waitForPageLoaded();
    }

    public void sendKeys(By selector, String text) {
        driver.findElement(selector).sendKeys(text);
    }

    public void sendKeysAndSubmit(By selector, String text) {
        sendKeys(selector, text);
        sendKeys(selector, String.valueOf(Keys.RETURN));
        Waiter.waitForPageLoaded();
    }

    public void clearInput(WebElement element, String oldText) {
        Actions actions = new Actions(driver);
        element.click();
        for (int i = 0; i < oldText.length(); i++) {
            actions.sendKeys(Keys.BACK_SPACE).build().perform();
        }
    }

    public String getText(By selector) {
        return driver.findElement(selector).getText();
    }

    public int getElementCount(By selector) {
        return driver.findElements(selector).size();
    }

    public Boolean isElementCreated(By locator) {
        try {
            Waiter.waitForElementCreated(locator);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public Boolean isElementSelected(By selector) {
        return getAttributeValue(selector, "class").contains("selected");
    }
}
