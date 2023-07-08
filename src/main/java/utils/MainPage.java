package utils;

import org.openqa.selenium.chrome.ChromeDriver;
import utils.Blocks.BaseBlock;
import utils.Blocks.ToDoBlock;

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
}
