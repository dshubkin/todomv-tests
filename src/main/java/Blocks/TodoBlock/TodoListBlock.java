package Blocks.TodoBlock;

import Blocks.BaseBlock;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.ChromeWebDriver;
import utils.Waiter;

public class TodoListBlock extends BaseBlock {
    private static final By ELEMENT = new By.ByXPath("//ul[contains(@class, \"todo-list\")]/li");
    private static final String ELEMENT_WITH_TEXT = "//ul[contains(@class, 'todo-list')]//input[contains(@value, '%s')]/..";
    private static final By EDITING_ELEMENT = new By.ByXPath("//ul[contains(@class, \"todo-list\")]//input[contains(@class, 'edit')]");
    private static final By CHECKBOX = new By.ByXPath("//ul[contains(@class, \"todo-list\")]/li/div/input");
    private static final By DELETE_BUTTON = new By.ByXPath("//button[contains(@class, \"destroy\")]");

    public TodoListBlock(ChromeWebDriver driver) {
        super(driver);
    }

    public String getTodoTextByNumber(int index) {
        return getElementFromList(ELEMENT, index).getText();
    }

    public String getTodoTextByText(String text) {
        return getText(new By.ByXPath(String.format("//label[contains(text(), '%s')]", text)));
    }

    public int getTodoCount() {
        return getElementCount(ELEMENT);
    }

    public TodoListBlock changeTodoText(int index, String oldTodoText, String newTodoText) {
        WebElement element = getElementFromList(ELEMENT, index);
        doubleClick(element);
        clearInput(element, oldTodoText.length());
        sendKeysAndSubmit(EDITING_ELEMENT, newTodoText);
        return this;
    }

    public TodoListBlock closeTodo(int index) {
        getElementFromList(CHECKBOX, index).click();
        Waiter.waitForPageLoaded();
        return this;
    }

    public TodoListBlock deleteTodo(int index) {
        WebElement targetElement = getElementFromList(ELEMENT, index);
        moveToElementAndClick(targetElement, DELETE_BUTTON, 0);
        return this;
    }

    public Boolean isTodoVisible(String text) {
        return isElementCreated(new By.ByXPath(String.format(ELEMENT_WITH_TEXT, text)));
    }
}
