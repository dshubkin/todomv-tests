package utils;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.List;

public class Waiter {
    public static WebElement waitForElementCreated(By locator) {
        ChromeDriver driver = ChromeWebDriver.getInstance();
        Wait<ChromeDriver> wait = new FluentWait<>(driver).withTimeout(Duration.ofMillis(500)).pollingEvery(Duration.ofMillis(250)).ignoring(NoSuchElementException.class);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static List<WebElement> waitForElementsCreated(By locator) {
        ChromeDriver driver = ChromeWebDriver.getInstance();
        Wait<ChromeDriver> wait = new FluentWait<>(driver).withTimeout(Duration.ofMillis(500)).pollingEvery(Duration.ofMillis(250)).ignoring(NoSuchElementException.class);
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    @Step(value = "Ожидаем обновления DOM'a")
    public static void waitForPageLoaded() {
        ChromeDriver driver = ChromeWebDriver.getInstance();
        ExpectedCondition<Boolean> expectation = x -> ((JavascriptExecutor) x).executeScript(
                "return document.readyState").equals("complete");
        Wait<ChromeDriver> wait = new FluentWait<>(driver).withTimeout(Duration.ofMillis(500)).pollingEvery(Duration.ofMillis(250)).ignoring(NoSuchElementException.class);
        wait.until(expectation);
    }
}
