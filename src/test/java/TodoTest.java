import Blocks.FooterTodoBlock;
import Blocks.HeaderTodoBlock;
import Blocks.MainTodoBlock;
import Blocks.ToDoBlock;
import Pages.MainPage;
import io.qameta.allure.Epic;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.utils;

import static utils.Asserts.*;

@Epic(value = "Тесты на блок заметок")
public class TodoTest extends BaseTest {
    private MainPage mainPage;
    private HeaderTodoBlock headerTodoBlock;
    private MainTodoBlock mainTodoBlock;
    private FooterTodoBlock footerTodoBlock;

    private static final String infoText1 = "kek";
    private static final String infoText2 = "kek2";

    @BeforeMethod(description = "Открываем главную страницу")
    @Override
    public void setUp() {
        mainPage = utils.createMainPage().openMainPage();
        ToDoBlock toDoBlock = mainPage.getToDoBlock();
        headerTodoBlock = toDoBlock.getHeaderTodoBlock();
        mainTodoBlock = toDoBlock.getMainTodoBlock();
        footerTodoBlock = toDoBlock.getFooterTodoBlock();
    }

    @Test(description = "Проверяем, что добавив одну заметку, она появится на вкладках all/active, и не попадет на completed")
    public void checkSuccessAddingInfo() throws Exception {
        headerTodoBlock.clickToInput().addTodo(infoText1);

        assertTrue(footerTodoBlock.isTabSelected("active"), "Дефолтная вкладка отличается!");
        assertEquals(mainTodoBlock.getTodoTextByText(infoText1), infoText1, "Введенный текст отличается!");

        footerTodoBlock.clickOnActiveTodoTab();
        assertTrue(footerTodoBlock.isTabSelected("active"), "Вкладка 'active' не выбрана");
        assertEquals(mainTodoBlock.getTodoCount(), 1, "Колличество активных заметок отличается!");

        footerTodoBlock.clickOnCompletedTodoTab();
        assertTrue(footerTodoBlock.isTabSelected("completed"), "Вкладка 'completed' не выбрана");
        assertEquals(mainTodoBlock.getTodoCount(), 0, "Колличество завершенных заметок > 0 !");
    }

    @Test(description = "Проверяем, что добавив две заметки, счетчик заметок покажет текущее колличество")
    public void checkTodoCounter() {
        headerTodoBlock.clickToInput().addTodo(infoText1);
        assertEquals(footerTodoBlock.getTodoCountText(), "1 item left", "Колличество заметок некорректное!");

        headerTodoBlock.addTodo(infoText2);
        assertEquals(footerTodoBlock.getTodoCountText(), "2 items left", "Колличество заметок некорректное!");
    }

    @Test(description = "Проверяем, что закрыв заметку, она появится на вкладке с закрытыми туду")
    public void checkCorrectCloseTodo() throws Exception {
        headerTodoBlock.clickToInput()
                .addTodo(infoText1);
        headerTodoBlock.addTodo(infoText2);
        mainTodoBlock.completeTodo(1);

        assertEquals(mainTodoBlock.getTodoTextByNumber(0), infoText1, "Текст оставшейся заметки отличается!");

        footerTodoBlock.clickOnCompletedTodoTab();

        assertTrue(footerTodoBlock.isTabSelected("completed"), "Вклада с закрытыми туду не выбрана!");
        assertEquals(mainTodoBlock.getTodoTextByNumber(0), infoText2, "Текст закрытой заметки отличается!");
        assertEquals(footerTodoBlock.getTodoCountText(), "1 item left", "Колличество заметок некорректное!");
    }

    @Test(description = "Проверяем, что кнопка выделения всех туду работает корректно")
    public void checkAllTodoSelect() throws InterruptedException {
        headerTodoBlock.clickToInput().addTodo(infoText1);
        mainPage.createTodoCopies();

        headerTodoBlock.selectAllTodo();
        footerTodoBlock.clickOnCompletedTodoTab();
        assertEquals(mainTodoBlock.getTodoCount(), 6, "Колличество заметок отличается!");
    }

    @Test(description = "Проверяем, что при вводе строки только из пробелов, заметка не создается")
    public void checkIfTodoOnlyWithSpacesNotBeCreated() {
        String text = "      ";
        headerTodoBlock.clickToInput().addTodo(text);
        assertFalse(mainTodoBlock.isTodoVisible(text), "Заметка создалась, а не должна!");
    }

    @Test(description = "Проверяем, что удаление и изменение заметки работает корректно")
    public void checkDeleteAndChangeOnTodoActions() throws InterruptedException {
        headerTodoBlock.clickToInput().addTodo(infoText1);
        headerTodoBlock.addTodo(infoText2);

        mainTodoBlock.deleteTodo(0).changeTodoText(0, infoText2, "kek");
        assertEquals(mainTodoBlock.getTodoCount(), 1, "Колличество заметок отличается!");
        assertTrue(mainTodoBlock.getTodoTextByNumber(0).contains("kek"));
    }
}
