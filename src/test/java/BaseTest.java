import org.testng.annotations.AfterMethod;
import utils.ChromeWebDriver;

public class BaseTest {

    @AfterMethod(description = "test")
    public void tearDown() {
        ChromeWebDriver.shutdownDriver();
    }
}
