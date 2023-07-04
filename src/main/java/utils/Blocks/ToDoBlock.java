package utils.Blocks;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class ToDoBlock extends BaseBlock {
    private ChromeDriver driver;

    private static final By INPUT = new By.ByTagName("input");
    private static final By COUNT = new By.ByClassName("todo-count");
    private static final By ALL_TAB = new By.ByXPath("//a[contains(@href, '#/')]");
    private static final By ACTIVE_TAB = new By.ByXPath("//a[contains(@href, '#/active')]");
    private static final By COMPLETED_TAB = new By.ByXPath("//a[contains(@href, '#/completed')]");
    private static final By TODO_LIST_CHECKBOX = new By.ByXPath("//ul[contains(@class, \"todo-list\")]/li/div/input");
    private static final By TODO_LIST_ELEMENT = new By.ByXPath("//ul[contains(@class, \"todo-list\")]/li");
    private static final By TODO_LIST_ELEMENT_LABEL = new By.ByXPath("//ul[contains(@class, \"todo-list\")]/li/div/label");
    private static final By ALL_TODO_CHECKBOX = new By.ByXPath("//label[contains(@for, \"toggle-all\")]");
    private static final By DELETE_BUTTON = new By.ByXPath("//button[contains(@class, \"destroy\")]");

    public ToDoBlock(ChromeDriver driver) {
       this.driver = driver;
    }

    public ToDoBlock click() {
        driver.findElement(INPUT).click();
        return this;
    }

    public ToDoBlock selectAllTodo() {
        driver.findElement(ALL_TODO_CHECKBOX).click();
        return this;
    }

    public ToDoBlock deleteTodo(int todoNumber) {
        Actions action = new Actions(driver);
        WebElement we = driver.findElements(TODO_LIST_ELEMENT).get(todoNumber - 1);
        action.moveToElement(we).moveToElement(driver.findElements(DELETE_BUTTON).get(todoNumber - 1)).click().build().perform();
        return this;
    }

    public ToDoBlock addTodo(String text) {
        driver.findElement(INPUT).sendKeys(text);
        driver.findElement(INPUT).sendKeys(Keys.RETURN);
        return this;
    }

    public ToDoBlock changeTodoText(int todoNumber, String oldTodoText,String newTodoText) {
        WebElement webElement = driver.findElements(TODO_LIST_ELEMENT).get(todoNumber - 1);
        Actions actions = new Actions(driver);
        actions.doubleClick(webElement).build().perform();
        for (int i = 0; i < oldTodoText.length(); i++) {
            actions.sendKeys(Keys.BACK_SPACE).build().perform();
        }
        actions.sendKeys(newTodoText).build().perform();
        return this;
    }

    public ToDoBlock closeTodo(int todoNumber) {
        driver.findElements(TODO_LIST_CHECKBOX).get(todoNumber - 1).click();
        return this;
    }

    public String getText() {
        return driver.findElement(INPUT).getText();
    }

    public String getTodoTextByText(String text) {
        return driver.findElement(new By.ByXPath(String.format("//label[contains(text(), '%s')]", text))).getText();
    }

    public String getTodoTextByNumber(int todoNumber) {
        return driver.findElements(TODO_LIST_ELEMENT).get(todoNumber - 1).getText();
    }

    public String getTodoCountText() {
        return driver.findElement(COUNT).getText();
    }

    public int getTodoCount() {
        return driver.findElements(TODO_LIST_ELEMENT).size();
    }

    public ToDoBlock clickOnAllTodoTab() {
        driver.findElement(ALL_TAB).click();
        return this;
    }

    public ToDoBlock clickOnActiveTodoTab() {
        driver.findElement(ACTIVE_TAB).click();
        return this;
    }

    public ToDoBlock clickOnCompletedTodoTab() throws InterruptedException {
        driver.findElement(COMPLETED_TAB).click();
        return this;
    }

    public boolean isTabSelected(String tabName) throws Exception {
        return switch (tabName) {
            case "all" -> driver.findElement(ALL_TAB).getAttribute("class").contains("selected");
            case "active" -> driver.findElement(ACTIVE_TAB).getAttribute("class").contains("selected");
            case "completed" -> driver.findElement(COMPLETED_TAB).getAttribute("class").contains("selected");
            default -> throw new Exception("Не указано имя вкладки!");
        };
    }
}
