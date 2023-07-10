import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import utils.ChromeWebDriver;

import java.io.FileOutputStream;
import java.io.IOException;

public class BaseTest {
    @AfterMethod(description = "test", alwaysRun = true)
    public void tearDown() {
        ChromeWebDriver.shutdownDriver();
    }

    @AfterSuite(description = "Создаем файл с пропертями для отчета", alwaysRun = true)
    public static void envFile() throws IOException {
        String javaVersion = System.getProperty("java.version");
        String osName = System.getProperty("os.name");
        String osVersion = System.getProperty("os.version");
        String browserName = ChromeWebDriver.getInstance().getCapabilities().getCapability("browserName").toString();
        String browserWVersion = ChromeWebDriver.getInstance().getCapabilities().getCapability("browserVersion").toString();
        String all = "browser = " + browserName + " " + browserWVersion + "\n" + "OS = " + osName + " " + osVersion + "\n" + "javaVersion = " + javaVersion;

       try (FileOutputStream fos = new FileOutputStream("./src/test/resources/environment.properties")) {
            fos.write(all.getBytes());
        }
    }
}
