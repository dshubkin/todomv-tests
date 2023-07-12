package Blocks.TodoBlock;

import Blocks.BaseBlock;
import utils.ChromeWebDriver;

public class TodoBlock extends BaseBlock {
    public TodoBlock(ChromeWebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public TodoInputBlock getHeaderTodoBlock() {
        return new TodoInputBlock(driver);
    }

    public TodoListBlock getMainTodoBlock() {
        return new TodoListBlock(driver);
    }

    public TodoTabBlock getFooterTodoBlock() {
        return new TodoTabBlock(driver);
    }
}
