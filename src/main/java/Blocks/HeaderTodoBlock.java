package Blocks;

import org.openqa.selenium.By;
import utils.ChromeWebDriver;

public class HeaderTodoBlock extends BaseBlock {
    private static final By INPUT = new By.ByTagName("input");
    private static final By ALL_TODO_CHECKBOX = new By.ByXPath("//label[contains(@for, \"toggle-all\")]");

    public HeaderTodoBlock(ChromeWebDriver driver) {
        super(driver);
    }

    public HeaderTodoBlock clickToInput() {
        click(INPUT);
        return this;
    }

    public ToDoBlock addTodo(String text) {
        sendKeysAndSubmit(INPUT, text);
        return new ToDoBlock(driver);
    }

    public ToDoBlock selectAllTodo() {
        click(ALL_TODO_CHECKBOX);
        return new ToDoBlock(driver);
    }
}