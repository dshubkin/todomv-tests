import org.example.ConfProperties;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import utils.Blocks.ToDoBlock;
import utils.MainPage;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;

public class InputInfoTest {

    private static final String infoText1 = "kek";
    private static final String infoText2 = "kek2";

    @Test(description = "Проверяем, что добавив одну заметку, она корректно работает")
    public void checkSuccessAddingInfo() {
        System.setProperty(ConfProperties.getProperty("driver"), ConfProperties.getProperty("driverPlace"));
        ChromeDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(ConfProperties.getProperty("mainpage"));

        MainPage mainPage = new MainPage();
        ToDoBlock toDoBlock = mainPage.getToDoBlock(driver)
                .click()
                .addTodo(infoText1);

        assertEquals(toDoBlock.getTodoText(infoText1), infoText1, "Введенный текст отличается!");

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
                .addTodo(infoText1)
                .addTodo(infoText2);

        assertEquals(toDoBlock.getTodoCountText(), "2 items left", "Колличество туду отличается!");

        driver.quit();
    }
}
