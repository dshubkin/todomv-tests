import org.example.ConfProperties;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import utils.Blocks.ToDoBlock;
import utils.MainPage;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class InputInfoTest {

    private static final String infoText1 = "kek";
    private static final String infoText2 = "kek2";

    @Test(description = "Проверяем, что добавив одну заметку, она появится на вкладках all/active, и не попадет на completed")
    public void checkSuccessAddingInfo() throws Exception {
        System.setProperty(ConfProperties.getProperty("driver"), ConfProperties.getProperty("driverPlace"));
        ChromeDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(ConfProperties.getProperty("mainpage"));

        MainPage mainPage = new MainPage();
        ToDoBlock toDoBlock = mainPage.getToDoBlock(driver)
                .click()
                .addTodo(infoText1);

        assertTrue(toDoBlock.isTabSelected("all"), "Дефолтная вкладка отличается!");
        assertEquals(toDoBlock.getTodoTextByText(infoText1), infoText1, "Введенный текст отличается!");

        toDoBlock.clickOnActiveTodoTab();
        assertTrue(toDoBlock.isTabSelected("active"), "Вкладка 'active' не выбрана");
        assertEquals(toDoBlock.getTodoCount(), 1, "Колличество активных заметок отличается!");

        toDoBlock.clickOnCompletedTodoTab();
        assertTrue(toDoBlock.isTabSelected("completed"), "Вкладка 'completed' не выбрана");
        assertEquals(toDoBlock.getTodoCount(), 0, "Колличество завершенных заметок > 0 !");

        driver.quit();
    }

    @Test(description = "Проверяем, что добавив две заметки, счетчик заметок покажет текущее колличество")
    public void checkTodoCounter() {
        System.setProperty(ConfProperties.getProperty("driver"), ConfProperties.getProperty("driverPlace"));
        ChromeDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(ConfProperties.getProperty("mainpage"));

        MainPage mainPage = new MainPage();
        ToDoBlock toDoBlock = mainPage.getToDoBlock(driver)
                .click()
                .addTodo(infoText1);
        assertEquals(toDoBlock.getTodoCountText(), "1 item left", "Колличество заметок некорректное!");

        toDoBlock.addTodo(infoText2);
        assertEquals(toDoBlock.getTodoCountText(), "2 items left", "Колличество заметок некорректное!");

        driver.quit();
    }

    @Test(description = "Проверяем, что закрыв заметку, она появится на вкладке с закрытыми туду")
    public void checkCorrectCloseTodo() throws Exception {
        System.setProperty(ConfProperties.getProperty("driver"), ConfProperties.getProperty("driverPlace"));
        ChromeDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(ConfProperties.getProperty("mainpage"));

        MainPage mainPage = new MainPage();
        ToDoBlock toDoBlock = mainPage.getToDoBlock(driver)
                .click()
                .addTodo(infoText1)
                .addTodo(infoText2)
                .closeTodo(2);

        assertEquals(toDoBlock.getTodoTextByNumber(1), infoText1, "Текст оставшейся заметки отличается!");

        toDoBlock.clickOnCompletedTodoTab();

        assertTrue(toDoBlock.isTabSelected("completed"), "Вклада с закрытыми туду не выбрана!");
        assertEquals(toDoBlock.getTodoTextByNumber(1), infoText2, "Текст закрытой заметки отличается!");
        assertEquals(toDoBlock.getTodoCountText(), "1 item left", "Колличество заметок некорректное!");

        driver.quit();
    }
}
