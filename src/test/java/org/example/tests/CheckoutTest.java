package org.example.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.models.CustomerModel;
import org.example.pages.Homepage;
import org.example.pages.OrderDetailsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckoutTest extends BaseTest{
    private static final Logger log = LogManager.getLogger();

    @Test
    public void CheckoutTest() {
        CustomerModel customerModel = new CustomerModel();

        // Przechodzimy przez proces zakupowy
        OrderDetailsPage orderDetailsPage = new Homepage(driver)
                .openShopPage()
                .openProduct("Java Selenium WebDriver")
                .addProductToCart()
                .viewCart()
                .openAddressDetailsPage()
                .fillAddressDetails(customerModel);

        // Czekamy, aż pojawi się komunikat o zamówieniu
        waitForElementToBeVisible(driver, orderDetailsPage.getOrderNotice());
        Assert.assertEquals(orderDetailsPage.getOrderNotice().getText(),
                "Thank you. Your order has been received.", "Niepoprawny komunikat o zamówieniu!");

        // Czekamy, aż pojawi się informacja o nazwie produktu
        waitForElementToBeVisible(driver, orderDetailsPage.getProductName());
        Assert.assertEquals(orderDetailsPage.getProductName().getText(),
                "Java Selenium WebDriver × 1", "Niepoprawna nazwa produktu w zamówieniu!");
    }
}
