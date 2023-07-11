import Pages.MainPage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import utils.ChromeWebDriver;
import utils.utils;

import java.io.FileOutputStream;
import java.io.IOException;

public class BaseTest {
    protected MainPage mainPage;
    protected static String browserWVersion;
    protected static String browserName;

    @BeforeMethod(description = "Открываем главную страницу")
    public void setUp() {
        mainPage = utils.createMainPage().openMainPage();
        browserName = ChromeWebDriver.getInstance().getCapabilities().getCapability("browserName").toString();
        browserWVersion = ChromeWebDriver.getInstance().getCapabilities().getCapability("browserVersion").toString();
    }

    @AfterMethod(description = "Закрываем драйвер", alwaysRun = true)
    public void tearDown() {
        ChromeWebDriver.shutdownDriver();
    }

    @AfterSuite(description = "Создаем файл с пропертями для отчета", alwaysRun = true)
    public static void envFile() throws IOException {
        String javaVersion = System.getProperty("java.version");
        String osName = System.getProperty("os.name");
        String osVersion = System.getProperty("os.version");
        String all = "browser = " + browserName + " " + browserWVersion + "\n" + "OS = " + osName + " " + osVersion + "\n" + "javaVersion = " + javaVersion;

       try (FileOutputStream fos = new FileOutputStream("./src/test/resources/environment.properties")) {
            fos.write(all.getBytes());
        }
    }
}
