package org.example.utils;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;

import java.util.HashMap;
import java.util.Map;

public class DriverFactory {

    private static final Map<String, WebDriver> drivers = new HashMap<>();

    public static WebDriver getDriver(String browserKey) {
        WebDriver driver = drivers.get(browserKey);

        if (driver == null) {
            DriverType driverType = DriverType.valueOf(PropertiesLoader.getProperty(browserKey + ".type").toUpperCase());
            switch (driverType) {
                case CHROME -> {
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("--disable-search-engine-choice-screen");
                    chromeOptions.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.ACCEPT);
                    driver = new ChromeDriver(chromeOptions);
                }
                case FIREFOX -> {
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    driver = new FirefoxDriver(firefoxOptions);
                }
                case OPERA -> {
                    OperaOptions operaOptions = new OperaOptions();
                    driver = new OperaDriver(operaOptions);
                }
                case EDGE -> {
                    EdgeOptions edgeOptions = new EdgeOptions();
                    driver = new EdgeDriver(edgeOptions);
                }
                default -> throw new IllegalArgumentException("Unknown browser: " + driverType);
            }
            driver.manage().window().setSize(new Dimension(
                    PropertiesLoader.getIntProperty("window.width"),
                    PropertiesLoader.getIntProperty("window.height")
            ));
            driver.get(PropertiesLoader.getProperty("base.url"));

            drivers.put(browserKey, driver);
        }
        return driver;
    }
}
