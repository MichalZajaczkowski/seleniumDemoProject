package org.example.pages;

import org.example.tests.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage extends BaseTest {
    public CartPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(partialLinkText = "Proceed to checkout")
    private WebElement proceedToCheckoutButton;


    public AddressDetailsPage openAddressDetailsPage() {
        proceedToCheckoutButton.click();
        return new AddressDetailsPage(driver);
    }
}
