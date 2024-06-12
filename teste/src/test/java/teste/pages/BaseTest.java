package teste.pages;

import com.github.javafaker.Faker;
import org.junit.After;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;


public class BaseTest {
    protected WebDriver driver;
    protected  WebDriver wait;
    protected Faker faker;
    @BeforeEach
    public void setUp(){
        driver = DriverSetup.getDriver();
        faker = new Faker();
    }

    @After
    public void tearDown(){
        if(driver != null) driver.quit();
    }
    protected void acceptAlert(String expectedAlertText) {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            Assert.assertEquals(expectedAlertText, alertText);
            alert.accept();
        } catch (NoAlertPresentException e) {
            Assert.fail("Alert not accepted");
        }
    }

    protected  void awaitForElementToLoad(WebDriver driver, By locator){
        new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(700))
                .until(ExpectedConditions.elementToBeClickable(locator))
                .click();
    }

}
