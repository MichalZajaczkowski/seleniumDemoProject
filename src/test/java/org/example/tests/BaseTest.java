package org.example.tests;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.model.Media;
import org.apache.commons.io.FileUtils;
import org.example.utils.DriverFactory;
import org.example.utils.PropertiesLoader;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = DriverFactory.getDriver("browser");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(PropertiesLoader.getProperty("base.url"));
    }

    @AfterMethod
    public void tearDown(ITestResult testResult) throws IOException {
        if (testResult.getStatus() == ITestResult.FAILURE) {
            takeScreenShotOnFailure(testResult);
        }
        if (driver != null) {
            driver.quit();
        }
    }

    @AfterMethod
    public void takeScreenShotOnFailure(ITestResult testResult) throws IOException {
        if (testResult.getStatus() == ITestResult.FAILURE) {
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File("errorScreenshots\\" + testResult.getName() + "-"
                    + Arrays.toString(testResult.getParameters()) + ".png"));
        }
    }

    public static void waitForElementToExist(WebDriver driver, By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static void waitForElementToBeVisible(WebDriver driver, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitForNotEmptyList(WebDriver driver, By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(browser -> !browser.findElements(locator).isEmpty());
    }

    public Media getScr(WebDriver driver) {
        String path = takeScreenshot(driver);
        return MediaEntityBuilder.createScreenCaptureFromPath(path).build();
    }

    public String takeScreenshot(WebDriver driver) {
        int randomNumber = (int) (Math.random() * 1000);
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File screenshotFile = screenshot.getScreenshotAs(OutputType.FILE);
        String path = new File("src/test/resources/screenshots/screenshot" + randomNumber + ".png").getAbsolutePath();
        try {
            FileUtils.copyFile(screenshotFile, new File(path));
        } catch (IOException e) {
            throw new RuntimeException("Failed to save screenshot: " + e.getMessage(), e);
        }
        return path;
    }
}
