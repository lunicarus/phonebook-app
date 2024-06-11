package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LandingPage {
    private WebDriver driver;
    public LandingPage(WebDriver driver) {
        this.driver = driver;
    }
    public void open() {
        driver.get("http://localhost/phonebook/");
        driver.manage().window().maximize();
    }

    public void clickAddButton() {
        final WebElement button = new WebDriverWait(driver, Duration.ofSeconds(10)) // 10s timeout
                .until(ExpectedConditions.elementToBeClickable(By.linkText("Adicionar")));
        button.click();
    }

    public void clickConsultButton() {
        final WebElement button = new WebDriverWait(driver, Duration.ofSeconds(10)) // 10s timeout
                .until(ExpectedConditions.elementToBeClickable(By.linkText("Consultar")));
        button.click();
    }
}