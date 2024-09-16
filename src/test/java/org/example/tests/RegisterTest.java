package org.example.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.pages.Homepage;
import org.example.models.RegisterModel;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.example.utils.myUtils.*;

public class RegisterTest extends BaseTest{
    private static final Logger log = LogManager.getLogger();

    @Test
    public void registerUser() {
        String email = generateRandomEmail();
        String password = generateStrongPassword(); // Generuj silne hasło
        log.info("Wygenerowano losowe dane użytkownika: Email: {}, Hasło: {}", email, password);

        RegisterModel.setEmail(email);
        RegisterModel.setPassword(password);
        // Rejestracja użytkownika
        WebElement dashboardLink = new Homepage(driver)
                .openMyAccountPage()
                .registerUserValidData(email, password)
                .getDashboardLink();

        // Sprawdzenie, czy element jest widoczny
        boolean isEntryTitleDisplayed = dashboardLink.isDisplayed();
        log.info("Sprawdzam, czy tytuł strony 'Dashboard' jest widoczny: {}", isEntryTitleDisplayed);
        Assert.assertTrue(isEntryTitleDisplayed);

        // Sprawdzenie, czy tekst tytułu to "Dashboard"
        String actualTitleText = dashboardLink.getText();
        log.info("Sprawdzam tekst tytułu: Oczekiwano: 'Dashboard', Otrzymano: '{}'", actualTitleText);
        Assert.assertEquals(actualTitleText, "Dashboard");

        log.info("Test rejestracji użytkownika zakończony sukcesem.");
    }


    @Test
    public void registerUserWithExistingEmailTest() {

        String email = "emailExtist@o2.pl";
        String password = generateStrongPassword();

        WebElement dashboardLink = new Homepage(driver)
                .openMyAccountPage()
                .registerUserInvalidData(email, password)
                .getErrorLink();

        // Sprawdzenie, czy tekst tytułu to "My account"
        String actualErrorText = dashboardLink.getText();
        log.info("Sprawdzam tekst błędu: Oczekiwano: 'An account is already registered with your email address. Please log in.', Otrzymano: '{}'", actualErrorText);
        Assert.assertTrue(dashboardLink.getText().contains(" An account is already registered with your email address"));

        log.info("Test rejestracji użytkownika zakończony porażką.");
    }
}
