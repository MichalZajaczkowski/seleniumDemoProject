package org.example.pages;

import org.example.models.CustomerModel;
import org.example.tests.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AddressDetailsPage extends BaseTest {

    public AddressDetailsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(id= "billing_first_name")
    private WebElement firstNameInput;

    @FindBy(id= "billing_last_name")
    private WebElement lastNameInput;

    @FindBy(id= "billing_company")
    private WebElement companyInput;

    @FindBy(id= "billing_country")
    private WebElement countryInput;

    @FindBy(id= "billing_address_1")
    private WebElement addressInput;

    @FindBy(id= "billing_postcode")
    private WebElement postCodeInput;

    @FindBy(id= "billing_city")
    private WebElement billingCityInput;

    @FindBy(id= "billing_phone")
    private WebElement phoneInput;

    @FindBy(id= "billing_email")
    private WebElement emailInput;

    @FindBy(id= "place_order")
    private WebElement placeOrderButton;

    public OrderDetailsPage fillAddressDetails(CustomerModel customerModel) {
        firstNameInput.sendKeys(customerModel.getFirstName());
        lastNameInput.sendKeys(customerModel.getLastName());
        companyInput.sendKeys(customerModel.getCompanyName());
        Select select = new Select(countryInput);
        select.selectByVisibleText(customerModel.getCountry());
        addressInput.sendKeys(String.format("%s %s",customerModel.getStreet(), customerModel.getHouseNumber()));
        postCodeInput.sendKeys(customerModel.getZipCode());
        billingCityInput.sendKeys(customerModel.getCity());
        phoneInput.sendKeys(customerModel.getPhone());
        emailInput.sendKeys(customerModel.getEmail());
        placeOrderButton.click();
        return new OrderDetailsPage(driver);
    }
}
