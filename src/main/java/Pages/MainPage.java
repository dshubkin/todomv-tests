package Pages;

import org.openqa.selenium.chrome.ChromeDriver;
import Blocks.BaseBlock;
import Blocks.ToDoBlock;
import utils.TodoRow;
import utils.utils;

public class MainPage extends BasePage {
    private ChromeDriver driver;

    public MainPage(ChromeDriver driver) {
        super(driver);
        this.driver = driver;
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
