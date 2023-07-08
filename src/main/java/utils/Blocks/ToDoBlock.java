package utils.Blocks;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class ToDoBlock extends BaseBlock {
    private static final By INPUT = new By.ByTagName("input");
    private static final By COUNT = new By.ByClassName("todo-count");
    private static final By ALL_TAB = new By.ByXPath("//a[contains(@href, '#/')]");
    private static final By ACTIVE_TAB = new By.ByXPath("//a[contains(@href, '#/active')]");
    private static final By COMPLETED_TAB = new By.ByXPath("//a[contains(@href, '#/completed')]");
    private static final By TODO_LIST_CHECKBOX = new By.ByXPath("//ul[contains(@class, \"todo-list\")]/li/div/input");
    private static final By TODO_LIST_ELEMENT = new By.ByXPath("//ul[contains(@class, \"todo-list\")]/li");
    private static final By ALL_TODO_CHECKBOX = new By.ByXPath("//label[contains(@for, \"toggle-all\")]");
    private static final By DELETE_BUTTON = new By.ByXPath("//button[contains(@class, \"destroy\")]");

    public ToDoBlock(ChromeDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public ToDoBlock click() {
        click(INPUT);
        return this;
    }

    public ToDoBlock clickOnAllTodoTab() {
        click(ALL_TAB);
        return this;
    }

    public ToDoBlock clickOnActiveTodoTab() {
        click(ACTIVE_TAB);
        return this;
    }

    public ToDoBlock clickOnCompletedTodoTab() {
        click(COMPLETED_TAB);
        return this;
    }

    public ToDoBlock addTodo(String text) {
        sendKeysAndSubmit(INPUT, text);
        return this;
    }

    public ToDoBlock completeTodo(int index) {
        getElementFromList(TODO_LIST_CHECKBOX, index).click();
        return this;
    }

    public ToDoBlock deleteTodo(int todoIndex) {
        WebElement targetElement = getElementFromList(TODO_LIST_ELEMENT, todoIndex);
        moveToElementAndClick(targetElement, DELETE_BUTTON, 0);
        return this;
    }

    public ToDoBlock selectAllTodo() {
        click(ALL_TODO_CHECKBOX);
        return this;
    }

    public ToDoBlock changeTodoText(int todoIndex, String oldTodoText,String newTodoText) {
        WebElement element = getElementFromList(TODO_LIST_ELEMENT, todoIndex);
        doubleClick(element);
        clearInput(element, oldTodoText);
        sendKeysAndSubmit(INPUT, newTodoText);
        return this;
    }

    public String getTodoTextByText(String text) {
        return getText(new By.ByXPath(String.format("//label[contains(text(), '%s')]", text)));
    }

    public String getTodoTextByNumber(int todoIndex) {
        return getElementFromList(TODO_LIST_ELEMENT, todoIndex).getText();
    }

    public String getTodoCountText() {
        return getText(COUNT);
    }

    public int getTodoCount() {
        return getElementCount(TODO_LIST_ELEMENT);
    }

    public boolean isTabSelected(String tabName) throws Exception {
        return switch (tabName) {
            case "all" -> isElementSelected(ALL_TAB);
            case "active" -> isElementSelected(ACTIVE_TAB);
            case "completed" -> isElementSelected(COMPLETED_TAB);
            default -> throw new Exception("Не указано имя вкладки!");
        };
    }
}
