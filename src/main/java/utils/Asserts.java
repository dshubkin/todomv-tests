package utils;

import io.qameta.allure.Attachment;
import org.testng.Assert;

public class Asserts extends Assert {
    public static void assertTrue(boolean condition, String message) {
        try {
            Assert.assertTrue(condition, message);
        } catch(AssertionError e) {
            getScreenshot();
            throw new AssertionError(e.getMessage());
        }
    }

    public static void assertTrue(boolean condition) {
        try {
            Assert.assertTrue(condition);
        } catch(AssertionError e) {
            getScreenshot();
            throw new AssertionError(e.getMessage());
        }
    }

    public static void assertFalse(boolean condition, String message) {
        try {
            Assert.assertFalse(condition, message);
        } catch(AssertionError e) {
            getScreenshot();
            throw new AssertionError(e.getMessage());
        }
    }

    public static void assertEquals(int actual, int expected, String message) {
        try {
            Assert.assertEquals(actual, expected, message);
        } catch (AssertionError e) {
            getScreenshot();
            throw new AssertionError(e.getMessage());
        }
    }

    public static void assertEquals(String actual, String expected, String message) {
        try {
            Assert.assertEquals(actual, expected, message);
        } catch (AssertionError e) {
            getScreenshot();
            throw new AssertionError(e.getMessage());
        }
    }

    @Attachment(value = "Скриншот падения")
    private static byte[] getScreenshot() {
        return utils.getFileBytes(utils.getScreenshotFile());
    }
}
