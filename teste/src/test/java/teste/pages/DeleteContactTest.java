
package teste.pages;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DeleteContactTest extends BaseTest {

    @Test
    @DisplayName("Should delete contact successfully")
    public void shouldDeleteContactSuccessfully() throws InterruptedException {
        navigateToContactsPage();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement deleteButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("img[alt='Excluir']")));

        deleteButton.click();
        Thread.sleep(1000);

        driver.switchTo().alert().accept();
        Thread.sleep(1000);

        acceptAlert("Contato excluído com sucesso!");
        Thread.sleep(1000);
    }

    private void navigateToContactsPage() throws InterruptedException {
        driver.get("https://master--agenda-publica.netlify.app/pages/read.html");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#contactsTable")));

        Thread.sleep(2000);
    }
}