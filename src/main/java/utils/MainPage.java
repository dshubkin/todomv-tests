package utils;

import org.openqa.selenium.chrome.ChromeDriver;
import utils.Blocks.ToDoBlock;

import java.util.concurrent.TimeUnit;

public class MainPage extends BasePage {
    private ChromeDriver driver;

    public MainPage(ChromeDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public ToDoBlock getToDoBlock() {
        return new ToDoBlock(driver);
    }
}
