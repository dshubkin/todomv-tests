package Blocks;

import org.openqa.selenium.By;
import utils.ChromeWebDriver;

public class FooterTodoBlock extends BaseBlock {
    private static final By COUNT = new By.ByClassName("todo-count");
    private static final By ALL_TAB = new By.ByXPath("//a[contains(@href, '#/')]");
    private static final By ACTIVE_TAB = new By.ByXPath("//a[contains(@href, '#/active')]");
    private static final By COMPLETED_TAB = new By.ByXPath("//a[contains(@href, '#/completed')]");

    public FooterTodoBlock(ChromeWebDriver driver) {
        super(driver);
    }

    public String getTodoCountText() {
        return getText(COUNT);
    }

    public TodoBlock clickOnAllTodoTab() {
        click(ALL_TAB);
        return new TodoBlock(driver);
    }

    public TodoBlock clickOnActiveTodoTab() {
        click(ACTIVE_TAB);
        return new TodoBlock(driver);
    }

    public TodoBlock clickOnCompletedTodoTab() {
        click(COMPLETED_TAB);
        return new TodoBlock(driver);
    }

    public boolean isTabSelected(String tabName) throws Exception {
        return switch (tabName) {
            case "all" -> isElementSelected(ALL_TAB);
            case "active" -> isElementSelected(ACTIVE_TAB);
            case "completed" -> isElementSelected(COMPLETED_TAB);
            default -> throw new Exception("Не указано имя вкладки!");
        };
    }
}
