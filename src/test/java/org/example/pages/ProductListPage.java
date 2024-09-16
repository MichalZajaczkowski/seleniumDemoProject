package org.example.pages;

import net.thucydides.core.annotations.findby.By;
import org.example.tests.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class ProductListPage extends BaseTest {

    public ProductListPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public ProductPage openProduct(String title) {
        driver.findElement(By.xpath("//h2[text()='"+ title +"']")).click();
        return new ProductPage(driver);
    }
}
