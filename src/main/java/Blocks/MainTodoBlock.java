package Blocks;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.ChromeWebDriver;
import utils.Waiter;

public class MainTodoBlock extends BaseBlock {
    private static final By TODO_LIST_CHECKBOX = new By.ByXPath("//ul[contains(@class, \"todo-list\")]/li/div/input");
    private static final By TODO_LIST_ELEMENT = new By.ByXPath("//ul[contains(@class, \"todo-list\")]/li");
    private static final By TODO_LIST_EDITING_ELEMENT = new By.ByXPath("//ul[contains(@class, \"todo-list\")]/li[contains(@class, 'editing')]");
    private static final String TODO_LIST_ELEMENT_WITH_TEXT = "//ul[contains(@class, 'todo-list')]//input[contains(@value, '%s')]/..";
    private static final By DELETE_BUTTON = new By.ByXPath("//button[contains(@class, \"destroy\")]");

    public MainTodoBlock(ChromeWebDriver driver) {
        super(driver);
    }

    public MainTodoBlock completeTodo(int index) {
        getElementFromList(TODO_LIST_CHECKBOX, index).click();
        Waiter.waitForPageLoaded();
        return this;
    }

    public MainTodoBlock deleteTodo(int todoIndex) {
        WebElement targetElement = getElementFromList(TODO_LIST_ELEMENT, todoIndex);
        moveToElementAndClick(targetElement, DELETE_BUTTON, 0);
        return this;
    }

    public MainTodoBlock changeTodoText(int todoIndex, String oldTodoText,String newTodoText) {
        WebElement element = getElementFromList(TODO_LIST_ELEMENT, todoIndex);
        doubleClick(element);
        clearInput(element, oldTodoText);
        sendKeysAndSubmit(TODO_LIST_EDITING_ELEMENT, newTodoText);
        return this;
    }

    public String getTodoTextByText(String text) {
        return getText(new By.ByXPath(String.format("//label[contains(text(), '%s')]", text)));
    }

    public String getTodoTextByNumber(int todoIndex) {
        return getElementFromList(TODO_LIST_ELEMENT, todoIndex).getText();
    }

    public int getTodoCount() {
        return getElementCount(TODO_LIST_ELEMENT);
    }

    public Boolean isTodoVisible(String text) {
        return isElementCreated(new By.ByXPath(String.format(TODO_LIST_ELEMENT_WITH_TEXT, text)));
    }
}
