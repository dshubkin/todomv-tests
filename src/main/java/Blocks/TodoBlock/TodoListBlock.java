package Blocks.TodoBlock;

import Blocks.BaseBlock;
import io.qameta.allure.Allure;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.ChromeWebDriver;

import java.util.List;

public class TodoListBlock extends BaseBlock {
    private static final By ELEMENT = new By.ByXPath("//ul[contains(@class, \"todo-list\")]/li");
    private static final String ELEMENT_WITH_TEXT = "//ul[contains(@class, 'todo-list')]//input[contains(@value, '%s')]/..";

    public TodoListBlock(ChromeWebDriver driver) {
        super(driver);
    }

    public String getTodoTextByNumber(int index) {
        Allure.step(String.format("Грепаем текст из заметки с индексом='%d'", index));
        return getTodoRows().get(index).getText();
    }

    public String getTodoTextByText(String text) {
        Allure.step(String.format("Грепаем текст из заметки с текстом='%s'", text));
        return driver.findElements(ELEMENT)
                .stream()
                .filter(element -> element.getText().contains(text))
                .map(TodoRow::new)
                .findFirst()
                .orElse(null)
                .getText();
    }

    public int getTodoCount() {
        Allure.step("Считаем колличество заметок на странице");
        return getTodoRows().size();
    }

    public TodoListBlock changeTodoText(int index, String newTodoText) {
        Allure.step(String.format("Меняем текст в заметке с индексом='%d'", index));
        return getTodoRows().get(index).changeText(newTodoText);
    }

    public TodoListBlock closeTodo(int index) {
        Allure.step(String.format("Закрываем заметку с индексом='%d'", index));
        return getTodoRows().get(index).close();
    }

    public TodoListBlock deleteTodo(int index) {
        Allure.step(String.format("Удаляем заметку с индексом='%d'", index));
        return getTodoRows().get(index).delete();
    }

    public Boolean isTodoVisible(String text) {
        Allure.step(String.format("Проверяем, видна ли заметка с текстом='%s'", text));
        return isElementCreated(new By.ByXPath(String.format(ELEMENT_WITH_TEXT, text)));
    }

    private List<TodoRow> getTodoRows() {
        return driver.findElements(ELEMENT)
                .stream()
                .map(TodoRow::new)
                .toList();
    }

    private static class TodoRow extends BaseBlock {
        private static final By EDIT = new By.ByXPath("//ul[contains(@class, \"todo-list\")]//input[contains(@class, 'edit')]");
        private static final By DELETE_BUTTON = new By.ByXPath("//button[contains(@class, \"destroy\")]");
        private WebElement todoElementRow;
        private String reactIdValue;

        public TodoRow(WebElement element) {
            super(ChromeWebDriver.getCurrentDriver());
            reactIdValue = element.getAttribute("data-reactid");
            this.todoElementRow = driver.findElement(new By.ByXPath(String.format("//li[contains(@data-reactid, '%s')]", reactIdValue)));
        }

        public String getText() {
            return todoElementRow.getText();
        }

        public TodoListBlock changeText(String newTodoText) {
            int currentTextLength = getText().length();
            WebElement inputElement = driver.findElement(EDIT);
            doubleClick(todoElementRow);
            clearInput(inputElement, currentTextLength);
            sendKeysAndSubmit(inputElement, newTodoText);
            return new TodoListBlock(driver);
        }

        public TodoListBlock close() {
            driver.findElement(new By.ByXPath(
                    String.format("//li[contains(@data-reactid, '%s')]//descendant::input[contains(@class, 'toggle')]", reactIdValue)))
                    .click();
            return new TodoListBlock(driver);
        }

        public TodoListBlock delete() {
            moveToElement(todoElementRow);
            driver.findElement(DELETE_BUTTON).click();
            return new TodoListBlock(driver);
        }
    }
}
