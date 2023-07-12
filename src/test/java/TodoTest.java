import Blocks.TodoBlock.TodoTabBlock;
import Blocks.TodoBlock.TodoInputBlock;
import Blocks.TodoBlock.TodoListBlock;
import Blocks.TodoBlock.TodoBlock;
import Pages.MainPage;
import io.qameta.allure.Epic;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.utils;

import static utils.Asserts.*;

@Epic(value = "Тесты на блок заметок")
public class TodoTest extends BaseTest {
    private MainPage mainPage;
    private TodoInputBlock todoInputBlock;
    private TodoListBlock todoListBlock;
    private TodoTabBlock todoTabBlock;

    private static final String infoText1 = "kek";
    private static final String infoText2 = "kek2";

    @BeforeMethod(description = "Открываем главную страницу")
    @Override
    public void setUp() {
        mainPage = utils.createMainPage().openMainPage();
        TodoBlock toDoBlock = mainPage.getToDoBlock();
        todoInputBlock = toDoBlock.getHeaderTodoBlock();
        todoListBlock = toDoBlock.getMainTodoBlock();
        todoTabBlock = toDoBlock.getFooterTodoBlock();
    }

    @Test(description = "Проверяем, что добавив одну заметку, она появится на вкладках all/active, и не попадет на completed")
    public void checkSuccessAddingInfo() throws Exception {
        todoInputBlock.clickToInput().addTodo(infoText1);

        assertTrue(todoTabBlock.isTabSelected("active"), "Дефолтная вкладка отличается!");
        assertEquals(todoListBlock.getTodoTextByText(infoText1), infoText1, "Введенный текст отличается!");

        todoTabBlock.clickOnActiveTodoTab();
        assertTrue(todoTabBlock.isTabSelected("active"), "Вкладка 'active' не выбрана");
        assertEquals(todoListBlock.getTodoCount(), 1, "Колличество активных заметок отличается!");

        todoTabBlock.clickOnCompletedTodoTab();
        assertTrue(todoTabBlock.isTabSelected("completed"), "Вкладка 'completed' не выбрана");
        assertEquals(todoListBlock.getTodoCount(), 0, "Колличество завершенных заметок > 0 !");
    }

    @Test(description = "Проверяем, что добавив две заметки, счетчик заметок покажет текущее колличество")
    public void checkTodoCounter() {
        todoInputBlock.clickToInput().addTodo(infoText1);
        assertEquals(todoTabBlock.getTodoCountText(), "1 item left", "Колличество заметок некорректное!");

        todoInputBlock.addTodo(infoText2);
        assertEquals(todoTabBlock.getTodoCountText(), "2 items left", "Колличество заметок некорректное!");
    }

    @Test(description = "Проверяем, что закрыв заметку, она появится на вкладке с закрытыми туду")
    public void checkCorrectCloseTodo() throws Exception {
        todoInputBlock.clickToInput()
                .addTodo(infoText1);
        todoInputBlock.addTodo(infoText2);
        todoListBlock.closeTodo(1);

        assertEquals(todoListBlock.getTodoTextByNumber(0), infoText1, "Текст оставшейся заметки отличается!");

        todoTabBlock.clickOnCompletedTodoTab();

        assertTrue(todoTabBlock.isTabSelected("completed"), "Вклада с закрытыми туду не выбрана!");
        assertEquals(todoListBlock.getTodoTextByNumber(0), infoText2, "Текст закрытой заметки отличается!");
        assertEquals(todoTabBlock.getTodoCountText(), "1 item left", "Колличество заметок некорректное!");
    }

    @Test(description = "Проверяем, что кнопка выделения всех туду работает корректно")
    public void checkAllTodoSelect() throws InterruptedException {
        todoInputBlock.clickToInput().addTodo(infoText1);
        mainPage.createTodoCopies();

        todoInputBlock.selectAllTodo();
        todoTabBlock.clickOnCompletedTodoTab();
        assertEquals(todoListBlock.getTodoCount(), 6, "Колличество заметок отличается!");
    }

    @Test(description = "Проверяем, что при вводе строки только из пробелов, заметка не создается")
    public void checkIfTodoOnlyWithSpacesNotBeCreated() {
        String text = "      ";
        todoInputBlock.clickToInput().addTodo(text);
        assertFalse(todoListBlock.isTodoVisible(text), "Заметка создалась, а не должна!");
    }

    @Test(description = "Проверяем, что удаление и изменение заметки работает корректно")
    public void checkDeleteAndChangeOnTodoActions() throws InterruptedException {
        todoInputBlock.clickToInput().addTodo(infoText1);
        todoInputBlock.addTodo(infoText2);

        todoListBlock.deleteTodo(0).changeTodoText(0, infoText2, "kek");
        assertEquals(todoListBlock.getTodoCount(), 1, "Колличество заметок отличается!");
        assertTrue(todoListBlock.getTodoTextByNumber(0).contains("kek"));
    }
}
