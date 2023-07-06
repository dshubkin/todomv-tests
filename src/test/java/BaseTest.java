import org.example.ConfProperties;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.MainPage;

import java.util.concurrent.TimeUnit;

public class BaseTest {
    protected MainPage mainPage;
    protected ChromeDriver driver;

    @BeforeMethod(description = "сетап до теста")
    public void setUp() {
        System.setProperty(ConfProperties.getProperty("driver"), ConfProperties.getProperty("driverPlace"));
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(ConfProperties.getProperty("mainpage"));

        mainPage = new MainPage(driver);
    }

    @AfterMethod(description = "test")
    public void tearDown() {
        driver.quit();
    }
}
