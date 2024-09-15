package org.example.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.pages.Homepage;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.example.utils.TestData;

public class LoginTest extends BaseTest{
    private static final Logger log = LogManager.getLogger();

    @Test
    public void loginValidUserTest() {
        // Pobierz dane z klasy TestData
        String email = TestData.getEmail();
        String password = TestData.getPassword();

        log.info("Logowanie użytkownika: Email: {}, Hasło: {}", email, password);

        WebElement dashboardLink = new Homepage(driver)
                .openMyAccountPage()
                .loginValidUser(email, password)
                .getDashboardLink();
        // Sprawdzenie, czy element jest widoczny
        boolean isEntryTitleDisplayed = dashboardLink.isDisplayed();
        log.info("Sprawdzam, czy tytuł strony 'Dashboard' jest widoczny: {}", isEntryTitleDisplayed);
        Assert.assertTrue(isEntryTitleDisplayed);

        // Sprawdzenie, czy tekst tytułu to "Dashboard"
        String actualTitleText = dashboardLink.getText();
        log.info("Sprawdzam tekst tytułu: Oczekiwano: 'Dashboard', Otrzymano: '{}'", actualTitleText);
        Assert.assertEquals(actualTitleText, "Dashboard");

        log.info("Test logowania użytkownika zakończony sukcesem.");
    }

    @Test
    public void loginInvalidUserTest() {

        String email = "loginInvalidUserTest1@o2.pl";
        String password = "1qazXSW@3edc1";
        log.info("Logowanie użytkownika niezarejestrowanego: Email: {}, Hasło: {}", email, password);

        WebElement dashboardLink = new Homepage(driver)
                .openMyAccountPage()
                .loginInvalidUser(email, password)
                .getErrorLink();

        String actualErrorText = dashboardLink.getText();
        log.info("Sprawdzam tekst błędu: Oczekiwano: 'ERROR: Incorrect username or password.', Otrzymano: '{}'", actualErrorText);
        Assert.assertTrue(dashboardLink.getText().contains("ERROR: Incorrect username or password."));

        log.info("Test logoiwania użytkownika zakończony porażką.");
    }
}
