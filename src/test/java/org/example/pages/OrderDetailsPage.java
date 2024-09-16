package org.example.pages;

import lombok.Getter;
import org.example.tests.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Getter
public class OrderDetailsPage extends BaseTest {
    public OrderDetailsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }


    @FindBy(xpath = "//div[@class='woocommerce-order']//p")
    public WebElement orderNotice;

    @FindBy(xpath = "//td[contains(@class,'product-name')]")
    public WebElement productName;

}
