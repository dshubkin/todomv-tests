import io.qameta.allure.Epic;
import org.testng.annotations.Test;
import utils.utils;
import utils.CompareTwoImages;

import java.io.IOException;

import static utils.Asserts.*;

@Epic(value = "Тест на скришот главной страницы")
public class ScreenshotTest extends BaseTest {
    @Test(description = "Проверяем, что при открытии страницы 1000х1000px, верстка выглядит, как на эталонном скришоте")
    public void checkScreenShotFromMainPage() throws IOException {
        mainPage = utils.createMainPage().openMainPage(false);
        CompareTwoImages cti = new CompareTwoImages();
        cti.setParameters(10, 10);
        cti.compare();
        try {
            assertTrue(cti.isIdentic());
        } catch (AssertionError e) {
            cti.getDefaultBytes();
            throw new AssertionError("Скриншоты отличаются!");
        }
    }
}
