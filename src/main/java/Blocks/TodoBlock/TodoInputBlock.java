package Blocks.TodoBlock;

import Blocks.BaseBlock;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import utils.ChromeWebDriver;

public class TodoInputBlock extends BaseBlock {
    private static final By INPUT = new By.ByTagName("input");
    private static final By ALL_TODO_CHECKBOX = new By.ByXPath("//label[contains(@for, \"toggle-all\")]");

    public TodoInputBlock(ChromeWebDriver driver) {
        super(driver);
    }

    @Step(value = "Кликаем на инпут")
    public TodoInputBlock clickToInput() {
        click(INPUT);
        return this;
    }

    public TodoBlock addTodo(String text) {
        Allure.step(String.format("Вводим в инпут текст '%s' и нажимаем на Enter", text));
        sendKeysAndSubmit(INPUT, text);
        return new TodoBlock(driver);
    }

    @Step(value = "Кликаем на чекбокс с  выбором всех заметок")
    public TodoBlock selectAllTodo() {
        click(ALL_TODO_CHECKBOX);
        return new TodoBlock(driver);
    }
}
