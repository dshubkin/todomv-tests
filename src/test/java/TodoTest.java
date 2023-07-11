import io.qameta.allure.Epic;
import org.testng.annotations.Test;
import Blocks.ToDoBlock;

import static utils.Asserts.*;

@Epic(value = "Тесты на блок заметок")
public class TodoTest extends BaseTest {
    private static final String infoText1 = "kek";
    private static final String infoText2 = "kek2";

    @Test(description = "Проверяем, что добавив одну заметку, она появится на вкладках all/active, и не попадет на completed")
    public void checkSuccessAddingInfo() throws Exception {
        ToDoBlock toDoBlock = mainPage.getToDoBlock()
                .click()
                .addTodo(infoText1);

        assertTrue(toDoBlock.isTabSelected("active"), "Дефолтная вкладка отличается!");
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
        ToDoBlock toDoBlock = mainPage.getToDoBlock()
                .click()
                .addTodo(infoText1);
        assertEquals(toDoBlock.getTodoCountText(), "1 item left", "Колличество заметок некорректное!");

        toDoBlock.addTodo(infoText2);
        assertEquals(toDoBlock.getTodoCountText(), "2 items left", "Колличество заметок некорректное!");
    }

    @Test(description = "Проверяем, что закрыв заметку, она появится на вкладке с закрытыми туду")
    public void checkCorrectCloseTodo() throws Exception {
        ToDoBlock toDoBlock = mainPage.getToDoBlock()
                .click()
                .addTodo(infoText1)
                .addTodo(infoText2)
                .completeTodo(1);

        assertEquals(toDoBlock.getTodoTextByNumber(0), infoText1, "Текст оставшейся заметки отличается!");

        toDoBlock.clickOnCompletedTodoTab();

        assertTrue(toDoBlock.isTabSelected("completed"), "Вклада с закрытыми туду не выбрана!");
        assertEquals(toDoBlock.getTodoTextByNumber(0), infoText2, "Текст закрытой заметки отличается!");
        assertEquals(toDoBlock.getTodoCountText(), "1 item left", "Колличество заметок некорректное!");
    }

    @Test(description = "Проверяем, что кнопка выделения всех туду работает корректно")
    public void checkAllTodoSelect() throws InterruptedException {
        ToDoBlock toDoBlock = mainPage.getToDoBlock()
                .click()
                .addTodo(infoText1);
        mainPage.createTodoCopies();

        toDoBlock.selectAllTodo()
                .clickOnCompletedTodoTab();
        assertEquals(toDoBlock.getTodoCount(), 6, "Колличество заметок отличается!");
    }

    @Test(description = "Проверяем, что при вводе строки только из пробелов, заметка не создается")
    public void checkIfTodoOnlyWithSpacesNotBeCreated() {
        String text = "      ";
        ToDoBlock toDoBlock = mainPage.getToDoBlock()
                .click()
                .addTodo(text);
        assertFalse(toDoBlock.isTodoVisible(text), "Заметка создалась, а не должна!");
    }

    @Test(description = "Проверяем, что удаление и изменение заметки работает корректно")
    public void checkDeleteAndChangeOnTodoActions() throws InterruptedException {
        ToDoBlock toDoBlock = mainPage.getToDoBlock()
                .click()
                .addTodo(infoText1)
                .addTodo(infoText2);

        toDoBlock.deleteTodo(0);
        toDoBlock.changeTodoText(0, infoText2, "kek");
        assertEquals(toDoBlock.getTodoCount(), 1, "Колличество заметок отличается!");
        assertTrue(toDoBlock.getTodoTextByNumber(0).contains("kek"));
    }
}
