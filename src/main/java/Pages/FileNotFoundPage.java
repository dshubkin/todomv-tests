package Pages;

import utils.ConfProperties;

public class FileNotFoundPage extends BasePage {
    public FileNotFoundPage openPage() {
        driver.get(ConfProperties.getProperty("brokenUrl"));
        return this;
    }
}
