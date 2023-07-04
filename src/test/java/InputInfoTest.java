import org.example.ConfProperties;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.Blocks.ToDoBlock;
import utils.MainPage;
import utils.TodoRow;
import utils.utils;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class InputInfoTest {

    private MainPage mainPage;
    private ChromeDriver driver;
    private static final String infoText1 = "kek";
    private static final String infoText2 = "kek2";

    @BeforeMethod(description = "сетап до теста")
    public void setUp() {
        System.setProperty(ConfProperties.getProperty("driver"), ConfProperties.getProperty("driverPlace"));
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(ConfProperties.getProperty("mainpage"));

        mainPage = new MainPage();
    }

    @Test(description = "Проверяем, что добавив одну заметку, она появится на вкладках all/active, и не попадет на completed")
    public void checkSuccessAddingInfo() throws Exception {
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
    }

    @Test(description = "Проверяем, что добавив две заметки, счетчик заметок покажет текущее колличество")
    public void checkTodoCounter() {
        ToDoBlock toDoBlock = mainPage.getToDoBlock(driver)
                .click()
                .addTodo(infoText1);
        assertEquals(toDoBlock.getTodoCountText(), "1 item left", "Колличество заметок некорректное!");

        toDoBlock.addTodo(infoText2);
        assertEquals(toDoBlock.getTodoCountText(), "2 items left", "Колличество заметок некорректное!");
    }

    @Test(description = "Проверяем, что закрыв заметку, она появится на вкладке с закрытыми туду")
    public void checkCorrectCloseTodo() throws Exception {
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
    }

    @Test(description = "Проверяем, что кнопка выделения всех туду работает корректно")
    public void checkAllTodoSelect() throws InterruptedException {
        ToDoBlock toDoBlock = mainPage.getToDoBlock(driver)
                .click()
                .addTodo(infoText1);
        TodoRow todoRow = new TodoRow(driver.getLocalStorage().getItem("react-todos"));
        utils.createTodoCopies(driver,todoRow, 5);
        toDoBlock.selectAllTodo()
                .clickOnCompletedTodoTab();
        assertEquals(toDoBlock.getTodoCount(), 6, "Колличество заметок отличается!");
    }

    @Test(description = "Проверяем, что туду из пробелов не создастся")
    public void checkIfTodoOnlyWithSpacesNotBeCreated() {
        ToDoBlock toDoBlock = mainPage.getToDoBlock(driver)
                .click()
                .addTodo("      ");
        assertEquals(toDoBlock.getTodoCount(), 0, "Заметка создалась, а не должна!");
    }

    @Test(description = "Проверяем, что удаление и изменение заметки работает корректно")
    public void checkDeleteAndChangeOnTodoActions() throws InterruptedException {
        ToDoBlock toDoBlock = mainPage.getToDoBlock(driver)
                .click()
                .addTodo(infoText1)
                .addTodo(infoText2);

        toDoBlock.deleteTodo(1);
        toDoBlock.changeTodoText(1, infoText2, "kek");
        assertEquals(toDoBlock.getTodoCount(), 1, "Колличество заметок отличается!");
        var a = toDoBlock.getTodoTextByNumber(1);
        while (a.length() == 0) {
            a = toDoBlock.getTodoTextByNumber(1);
        }
        assertTrue(a.contains("kek"));
    }

    @AfterMethod(description = "test")
    public void tearDown() {
        driver.quit();
    }
}
