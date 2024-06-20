package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ConsultContactPage {
    private WebDriver driver;

    public ConsultContactPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickFirstReadButton() {
        final WebElement button = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector("tr:nth-child(1) .button-read:nth-child(1)")));
        button.click();
    }

    public void clickFirstDeleteButton() {
        final WebElement button = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector("tr:nth-child(1) .button-read:nth-child(2)")));
        button.click();
    }

    public WebElement getFirstContactPhoneInput() {
        return driver.findElement(By.cssSelector("td:nth-child(2) > input"));
    }

    public WebElement getFirstContactNameInput() {
        return driver.findElement(By.cssSelector("td:nth-child(1) > input"));
    }
}
