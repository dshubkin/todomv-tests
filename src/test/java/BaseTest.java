import Pages.MainPage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import utils.ChromeWebDriver;

import java.io.FileOutputStream;
import java.io.IOException;

public class BaseTest {
    protected MainPage mainPage;
    private static String browserVersion;
    private static String browserName;

    @AfterMethod(description = "Закрываем драйвер", alwaysRun = true)
    public static void tearDown() {
        browserName = ChromeWebDriver.getInstance().getCapabilities().getCapability("browserName").toString();
        browserVersion = ChromeWebDriver.getInstance().getCapabilities().getCapability("browserVersion").toString();
        ChromeWebDriver.shutdownDriver();
    }

    @AfterSuite(description = "Создаем файл с пропертями для отчета", alwaysRun = true)
    public static void envFile() throws IOException {
        String javaVersion = System.getProperty("java.version");
        String osName = System.getProperty("os.name");
        String osVersion = System.getProperty("os.version");
        String all = "browser = " + browserName + " " + browserVersion + "\n" + "OS = " + osName + " " + osVersion + "\n" + "javaVersion = " + javaVersion;

       try (FileOutputStream fos = new FileOutputStream("./target/allure-results/environment.properties")) {
            fos.write(all.getBytes());
        }
    }
}
