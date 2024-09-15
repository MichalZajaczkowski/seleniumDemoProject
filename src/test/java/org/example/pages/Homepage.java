package org.example.pages;

import org.example.tests.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Homepage extends BaseTest {

    @FindBy(xpath = "//span[text()='My account']")
    private WebElement myAccount;

    public Homepage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public MyAccountPage openMyAccountPage() {
        myAccount.click();
        return new MyAccountPage(driver);
    }
}
