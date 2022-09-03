package model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HotelMainPage {
    private WebDriver driver;

    public HotelMainPage(WebDriver driver){
        this.driver=driver;
    }

    public void clickSignIn() {
        // Wyszukaj przycisk do logowania
        WebElement signInButton = this.driver.findElement(By.className("user_login"));
        signInButton.click(); // Kliknij na przycisk SignIn

    }

}
