package utils;

import Pages.BasePage;
import Pages.MainPage;
import com.google.gson.JsonObject;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class utils {
    public static void createTodoCopies(ChromeDriver driver, TodoJson todoJson, int numberOfCopies) {
        List<JsonObject> jsonObjectList = new ArrayList<>();

        jsonObjectList.add(todoJson.getJsonObject());
        for (int i = 0; i < numberOfCopies; i++) {
            jsonObjectList.add(new TodoJson(String.valueOf(i), todoJson.getJsonObject().get("title").getAsString() + i, todoJson.getCompleted()).getJsonObject());
        }
        driver.getLocalStorage().removeItem("react-todos");
        driver.getLocalStorage().setItem("react-todos", jsonObjectList.toString());
        driver.navigate().refresh();
    }

    public static byte[] getFileBytes(File file) {
        try {
            return new FileInputStream(file).readAllBytes();
        } catch (Exception e) {
            return null;
        }

    }

    public static File getScreenshotFile() {
        return ((TakesScreenshot) ChromeWebDriver.getInstance()).getScreenshotAs(OutputType.FILE);
    }

    public static MainPage createMainPage() {
        return createPage(MainPage.class);
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
}
