import Pages.MainPage;
import io.qameta.allure.Epic;
import org.openqa.selenium.Dimension;
import org.testng.annotations.Test;
import utils.utils;
import utils.CompareTwoImages;

import static org.testng.Assert.assertTrue;

@Epic(value = "Тест на скришот главной страницы")
public class ScreenshotTest {
    @Test(description = "Проверяем, что при открытии страницы 1000х1000px, верстка выглядит, как на эталонном скришоте")
    public void checkScreenShotFromMainPage() throws Exception {
        MainPage mainPage = utils.createMainPage().openMainPage();
        mainPage.getDriver().manage().window().setSize(new Dimension(1000, 1000));

        CompareTwoImages cti = new CompareTwoImages();
        cti.setParameters(10, 10);
        cti.compare();
        try {
            assertTrue(cti.isIdentic());
        } catch (AssertionError e) {
            cti.getActualBytes();
            cti.getDefaultBytes();
            throw new AssertionError("Скриншоты отличаются!");
        }
    }
}
