package Blocks.TodoBlock;

import Blocks.BaseBlock;
import utils.ChromeWebDriver;

public class TodoBlock extends BaseBlock {
    public TodoBlock(ChromeWebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public TodoInputBlock getTodoInputBlock() {
        return new TodoInputBlock(driver);
    }

    public TodoListBlock getTodoListBlock() {
        return new TodoListBlock(driver);
    }

    public TodoTabBlock getTodoTabBlock() {
        return new TodoTabBlock(driver);
    }
}
