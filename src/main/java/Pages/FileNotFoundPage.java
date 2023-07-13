package Pages;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import utils.ConfProperties;

public class FileNotFoundPage extends BasePage {
    @Step(value = "Открываем страницу с 404")
    public FileNotFoundPage openPage() {
        driver.get(ConfProperties.getProperty("brokenUrl"));
        setMaxWindowSize();
        return this;
    }
}
