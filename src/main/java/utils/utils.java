package utils;

import Pages.BasePage;
import Pages.MainPage;
import com.google.gson.JsonObject;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;

public class utils {
    public static void createTodoCopies(ChromeDriver driver, TodoRow todoRow, int numberOfCopies) {
        List<JsonObject> jsonObjectList = new ArrayList<>();

        jsonObjectList.add(todoRow.getJsonObject());
        for (int i = 0; i < numberOfCopies; i++) {
            //jsonObjectList.add(new TodoRow(String.valueOf(i), todoRow.getTitle() + i, todoRow.getCompleted()).getJsonObject());
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
}
