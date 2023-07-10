package utils;

import Pages.BasePage;
import Pages.MainPage;
import com.google.gson.JsonObject;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class utils {
    public static void createTodoCopies(ChromeDriver driver, TodoRow todoRow, int numberOfCopies) {
        List<JsonObject> jsonObjectList = new ArrayList<>();

        jsonObjectList.add(todoRow.getJsonObject());
        for (int i = 0; i < numberOfCopies; i++) {
            jsonObjectList.add(new TodoRow(String.valueOf(i), todoRow.getJsonObject().get("title").getAsString() + i, todoRow.getCompleted()).getJsonObject());
        }
        driver.getLocalStorage().removeItem("react-todos");
        driver.getLocalStorage().setItem("react-todos", jsonObjectList.toString());
        driver.navigate().refresh();
    }

    public static <T extends BasePage> T createPage(Class<T> clazz) {
        T screen;
        try {
            screen = clazz.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            Throwable cause = e.getCause();
            if (cause != null) {
                throw (UnexpectedJsErrorException) cause;
            }
            throw new RuntimeException(e);
        }
        return screen;
    }

    public static MainPage createMainPage() {
        return createPage(MainPage.class);
    }

    public byte[] getFileBytes(File file) throws IOException {
        return new FileInputStream(file).readAllBytes();
    }

    public File getScreenshotFile() throws IOException {
        return ((TakesScreenshot) ChromeWebDriver.getInstance()).getScreenshotAs(OutputType.FILE);
    }
}
