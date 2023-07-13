package Pages;

import Blocks.BaseBlock;
import Blocks.TodoBlock.TodoBlock;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import utils.TodoJson;
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

    @Step(value = "Кликаем на ссылку демо сайта с технологие TypeScript")
    public void clickOnTSDemoLink() {
        driver.findElements(DEMO_LINK).get(2).click();
    }

    @Step(value = "Открываем главную страницу")
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

    @Step(value = "Создаем 5 копий заметок")
    public void createTodoCopies() {
        TodoJson todoJson = new TodoJson(driver.getLocalStorage().getItem("react-todos"));
        utils.createTodoCopies(driver, todoJson, 5);
    }
}
