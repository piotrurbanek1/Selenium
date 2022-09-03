package pl.coderslab;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.Random;

import static org.junit.Assert.assertEquals;

public class AccountTest {

    private WebDriver driver;

    @Before
    public void setUp(){
        //        Ustaw gdzie jest chromedriver -> STEROWNIK
        System.setProperty("webdriver.chrome.driver",
                "src/main/resources/drivers/chromedriver.exe");
        //        Otworz przegladarke
        this.driver = new ChromeDriver();
        //        Jesli test nie przechodzi poprawnie, to pewnie za wolno laduje sie strona -> Dodaj czekanie.
        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @After
    public void tearDown(){
        driver.quit();
    }

    @Test
    public void testCreateNewUser() {

        String email = new Random().nextInt(100000000) + "TEA26@test.com";
        String expectedAlertText = "Your account has been created.";

        // Wejdz na strone glowna
        this.driver.get("https://hotel-testlab.coderslab.pl/en/");

        // Wyszukaj przycisk do logowania
        WebElement signInButton = this.driver.findElement(By.className("user_login"));
        signInButton.click(); // Kliknij na przycisk SignIn

        WebElement registerEmailInput = this.driver.findElement(By.id("email_create"));
        registerEmailInput.clear(); // Wyczysc pole tekstowe przed wpisaniem
        registerEmailInput.sendKeys(email);
        registerEmailInput.submit(); // Potwierdz email do rejestracji

        // Wyszukaj podstawowe elementy do zalozenia uzytkownika
        WebElement customerFirstNameInput = this.driver.findElement(By.id("customer_firstname"));
        WebElement customerLastNameInput = this.driver.findElement(By.id("customer_lastname"));
//        WebElement customerEmail = driver.findElement(By.id("email"));
        WebElement customerPassword = this.driver.findElement(By.id("passwd"));

        // Wypelnij brakujace informacje o uzytkowniku
        customerFirstNameInput.clear();
        customerFirstNameInput.sendKeys("Janusz");
        customerLastNameInput.clear();
        customerLastNameInput.sendKeys("Januszewski");

        customerPassword.clear();
        customerPassword.sendKeys("12345");

        // Zaloz konto
        WebElement submitButton = this.driver.findElement(By.id("submitAccount"));
        submitButton.click();

        // Sprawdz czy konto poprawnie zalozone
        WebElement successAlertField = this.driver.findElement(By.className("alert-success"));

        // Pobierz informacje o poprawnym zalozeniu konta
        String alertText = successAlertField.getText();

        // Zweryfikuj komunikat.
        assertEquals(expectedAlertText, alertText);

        String pageTitle = this.driver.getTitle();

        assertEquals("My account - MyBooking", pageTitle);
    }

}
