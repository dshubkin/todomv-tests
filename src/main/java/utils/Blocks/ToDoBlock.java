package utils.Blocks;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;

public class ToDoBlock extends BaseBlock {
    private ChromeDriver driver;

    private static final By INPUT = new By.ByTagName("input");

    public ToDoBlock(ChromeDriver driver) {
       this.driver = driver;
    }

    public ToDoBlock click() {
        driver.findElement(INPUT).click();
        return this;
    }

    public ToDoBlock sendKeys(String text) {
        driver.findElement(INPUT).sendKeys(text);
        driver.findElement(INPUT).sendKeys(Keys.RETURN);
        return this;
    }

    public String getText() {
        return driver.findElement(INPUT).getText();
    }

    public String getTodoText(String text) {
        return driver.findElement(new By.ByXPath(String.format("//label[contains(text(), '%s')]", text))).getText();
    }
}
