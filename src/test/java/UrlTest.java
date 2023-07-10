import Pages.FileNotFoundPage;
import Pages.MainPage;
import io.qameta.allure.Epic;
import org.openqa.selenium.Dimension;
import org.testng.annotations.Test;
import utils.CompareTwoImages;
import utils.utils;

import static org.testng.Assert.*;

@Epic(value = "Тесты на URL")
public class UrlTest extends BaseTest {
    private String defaultUrl = "https://todomvc.com/examples/react/#/";
    private String brokenUrl = "https://todomvc.com/examples/react1";

    @Test(description = "Проверяем, что при добавлении неожидаемых символов в урл сайт вернет 404")
    public void testNotFoundUrl() {
        FileNotFoundPage page = utils.createPage(FileNotFoundPage.class);
        page.openUrl(brokenUrl);
        assertEquals(page.getH1Text(), "404", "Заголовок h1 отличается!");
    }

    @Test(description = "Проверяем, что при смене демо заметок меняется урл")
    public void checkWhenChangedDemoTodoUrlWillChange() {
        MainPage mainPage = utils.createMainPage().openMainPage();
        mainPage.clickOnTSDemoLink();
        String newUrl = mainPage.getDriver().getCurrentUrl();
        assertFalse(defaultUrl.contains(newUrl), "Урл не изменился!");
    }

    @Test(description = "Проверяем, что при открытии страницы в урле нет ничего лишнего")
    public void checkDefaultUrl() {
        MainPage mainPage = utils.createMainPage().openMainPage();
        assertTrue(mainPage.getDriver().getCurrentUrl().contains(defaultUrl), "Дефолтный урл не совпадает!");
    }
}
