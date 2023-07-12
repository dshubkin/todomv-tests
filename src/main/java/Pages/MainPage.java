package Pages;

import Blocks.BaseBlock;
import Blocks.TodoBlock.TodoBlock;
import org.openqa.selenium.By;
import utils.TodoRow;
import utils.utils;
import utils.ConfProperties;

public class MainPage extends BasePage {
    private static final By DEMO_LINK = new By.ByClassName("demo-link");

    public TodoBlock getToDoBlock() {
        return new TodoBlock(driver);
    }

    public BaseBlock getBaseBlock() {
        return new BaseBlock(driver);
    }

    public void clickOnTSDemoLink() {
        driver.findElements(DEMO_LINK).get(2).click();
    }

    public MainPage openMainPage(Boolean withMaxSize) {
        driver.get(ConfProperties.getProperty("defaultUrl"));
        if (withMaxSize) {
            setMaxWindowSize();
        } else setWindowSize(1000, 1000);
        return this;
    }

    public MainPage openMainPage() {
        return openMainPage(true);
    }

    public void createTodoCopies() {
        TodoRow todoRow = new TodoRow(driver.getLocalStorage().getItem("react-todos"));
        utils.createTodoCopies(driver,todoRow, 5);
    }
}
