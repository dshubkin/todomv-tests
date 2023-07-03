package utils;

import org.openqa.selenium.chrome.ChromeDriver;
import utils.Blocks.ToDoBlock;

import java.util.concurrent.TimeUnit;

public class MainPage {
    public ToDoBlock getToDoBlock(ChromeDriver driver) {
        return new ToDoBlock(driver);
    }
}
