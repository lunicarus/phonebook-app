package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AddContactPage {
    private WebDriver driver;
    public AddContactPage(WebDriver driver) {
        this.driver = driver;
    }
    public void enterName(String name) {
        driver.findElement(By.id("nome")).sendKeys(name);
    }

    public void enterPhone(String phone) {
        driver.findElement(By.id("tel")).sendKeys(phone);
    }

    public void clickCreate() {
        final WebElement button = new WebDriverWait(driver, Duration.ofSeconds(10)) // 10s timeout
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector(".button-create")));
        button.click();
    }
}