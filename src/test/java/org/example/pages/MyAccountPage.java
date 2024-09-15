package org.example.pages;

import org.example.tests.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MyAccountPage extends BaseTest {

    public MyAccountPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(id="reg_email")
    private WebElement regEmailInput;

    @FindBy(id="reg_password")
    private WebElement regPasswordInput;

    @FindBy(name="register")
    private WebElement registerButton;

    @FindBy(id="username")
    private WebElement usernameInput;

    @FindBy(id="password")
    private WebElement passwordInput;

    @FindBy(name="login")
    private WebElement loginButton;


    public LoggedUserPage registerUserValidData(String email, String password) {
        registerUser(email, password);
        return new LoggedUserPage(driver);
    }

    public MyAccountPage registerUserInvalidData(String email, String password) {
        registerUser(email, password);
        return this;
    }

    private void registerUser(String email, String password) {
        regEmailInput.sendKeys(email);
        regPasswordInput.sendKeys(password);
        registerButton.click();
    }

    public WebElement getErrorLink() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // Poprawne użycie ExpectedConditions
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@class='woocommerce-error']//li")));
    }

    public LoggedUserPage loginValidUser(String username, String password) {
        loginUser(username, password);
        return new LoggedUserPage(driver);
    }

    public MyAccountPage loginInvalidUser(String username, String password) {
        loginUser(username, password);
        return this;
    }

    private void loginUser(String username, String password) {
        usernameInput.sendKeys(username);
        passwordInput.sendKeys(password);
        loginButton.click();
    }

}
