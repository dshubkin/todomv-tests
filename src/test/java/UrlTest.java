import Pages.FileNotFoundPage;
import io.qameta.allure.Epic;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.utils;
import utils.ConfProperties;

import static utils.Asserts.*;

@Epic(value = "Тесты на URL")
public class UrlTest extends BaseTest {
    @BeforeMethod(description = "Открываем главную страницу")
    public void setUp() {
        mainPage = utils.createMainPage().openMainPage();
    }

    @Test(description = "Проверяем, что при добавлении неожидаемых символов в урл сайт вернет 404")
    public void testNotFoundUrl() {
        FileNotFoundPage page = utils.createPage(FileNotFoundPage.class).openPage();
        assertEquals(page.getH1Text(), "404", "Заголовок h1 отличается!");
    }

    @Test(description = "Проверяем, что при смене демо заметок меняется урл")
    public void checkWhenChangedDemoTodoUrlWillChange() {
        mainPage.clickOnTSDemoLink();
        String newUrl = mainPage.getDriver().getCurrentUrl();
        assertFalse(ConfProperties.getProperty("defaultUrl").contains(newUrl), "Урл не изменился!");
    }

    @Test(description = "Проверяем, что при открытии страницы в урле нет ничего лишнего")
    public void checkDefaultUrl() {
        assertTrue(mainPage.getDriver().getCurrentUrl().contains(ConfProperties.getProperty("defaultUrl")), "Дефолтный урл не совпадает!");
    }
}
