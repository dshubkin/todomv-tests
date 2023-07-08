package utils.Blocks;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class BaseBlock {
    protected ChromeDriver driver;

    public BaseBlock(ChromeDriver driver) {
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
    }

    public void click(WebElement element) {
        element.click();
    }

    public void doubleClick(WebElement element) {
        Actions actions = new Actions(driver);
        actions.doubleClick(element).build().perform();
    }

    public void moveToElement(WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).build().perform();
    }

    public void moveToElementAndClick(WebElement element, By selector, int index) {
        moveToElement(element);
        click(getElementFromList(selector, index));
    }

    public void sendKeys(By selector, String text) {
        driver.findElement(selector).sendKeys(text);
    }

    public void sendKeysAndSubmit(By selector, String text) {
        sendKeys(selector, text);
        sendKeys(selector, String.valueOf(Keys.RETURN));
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

    public Boolean isElementSelected(By selector) {
        return getAttributeValue(selector, "class").contains("selected");
    }
}
