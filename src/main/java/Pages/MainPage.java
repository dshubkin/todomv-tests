package Pages;

import Blocks.BaseBlock;
import Blocks.ToDoBlock;
import utils.TodoRow;
import utils.utils;
import utils.ConfProperties;

public class MainPage extends BasePage {

    public MainPage openMainPage() {
        driver.get(ConfProperties.getProperty("defaultUrl"));
        return this;
    }

    public ToDoBlock getToDoBlock() {
        return new ToDoBlock(driver);
    }

    public BaseBlock getBaseBlock() {
        return new BaseBlock(driver);
    }

    public void createTodoCopies() {
        TodoRow todoRow = new TodoRow(driver.getLocalStorage().getItem("react-todos"));
        utils.createTodoCopies(driver,todoRow, 5);
    }
}
