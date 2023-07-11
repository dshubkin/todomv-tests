import Pages.MainPage;
import io.qameta.allure.Epic;
import org.openqa.selenium.Dimension;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.utils;
import utils.CompareTwoImages;

import java.io.IOException;

import static org.testng.Assert.assertTrue;

@Epic(value = "Тест на скришот главной страницы")
public class ScreenshotTest extends BaseTest {
    @BeforeMethod(description = "Открываем главную страницу")
    @Override
    public void setUp() {
        mainPage = utils.createMainPage().openMainPage(false);
        browserName = mainPage.getDriver().getCapabilities().getCapability("browserName").toString();
        browserWVersion = mainPage.getDriver().getCapabilities().getCapability("browserVersion").toString();
    }

    @Test(description = "Проверяем, что при открытии страницы 1000х1000px, верстка выглядит, как на эталонном скришоте")
    public void checkScreenShotFromMainPage() throws IOException {
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
