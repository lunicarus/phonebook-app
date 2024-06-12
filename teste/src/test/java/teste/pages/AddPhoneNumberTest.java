package teste.pages;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AddPhoneNumberTest extends BaseTest {

    private void navigateToAddPhonePage() throws InterruptedException {
        driver.get("https://master--agenda-publica.netlify.app/");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement addButton = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Adicionar")));

        addButton.click();

        Thread.sleep(1000);
    }

    @Test
    public void shouldAddContactIfPhoneNumberAndNameAreValid() throws InterruptedException {
        navigateToAddPhonePage();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement nameField = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("nome")));
        WebElement phoneField = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("tel")));

        Thread.sleep(1000);

        nameField.sendKeys("Vinicius Martins");
        phoneField.sendKeys(faker.phoneNumber().cellPhone());

        Thread.sleep(1000);

        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.button-create")));
        submitButton.click();

        Thread.sleep(1000);

        acceptAlert("Contato adicionado com sucesso!");

        Thread.sleep(1000);
    }

    @Test
    public void shouldNotAddContactIfNameHasSpecialCharsOrNumbers() throws InterruptedException {
        navigateToAddPhonePage();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement nameField = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("nome")));
        WebElement phoneField = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("tel")));

        Thread.sleep(1000);

        nameField.sendKeys("John Doe 123 @#");
        phoneField.sendKeys(faker.phoneNumber().cellPhone());

        Thread.sleep(1000);

        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.button-create")));
        submitButton.click();

        Thread.sleep(1000);

        acceptAlert("Nome inválido. Por favor, insira um nome válido sem números ou caracteres especiais.");

        Thread.sleep(1000);
    }

    @Test
    public void shouldShowErrorForDuplicatePhone() throws InterruptedException {
        navigateToAddPhonePage();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement nameField = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("nome")));
        WebElement phoneField = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("tel")));

        Thread.sleep(1000);

        String validPhoneNumber = faker.phoneNumber().cellPhone();

        // Adicionar um contato válido
        nameField.sendKeys("Valid Name");
        phoneField.sendKeys(validPhoneNumber);

        Thread.sleep(1000);

        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.button-create")));
        submitButton.click();

        Thread.sleep(1000);

        acceptAlert("Contato adicionado com sucesso!");

        Thread.sleep(1000);

        navigateToAddPhonePage();

        nameField = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("nome")));
        phoneField = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("tel")));

        Thread.sleep(1000);

        // Tentar adicionar o mesmo número de telefone novamente
        nameField.sendKeys("Another Valid Name");
        phoneField.sendKeys(validPhoneNumber);

        Thread.sleep(1000);

        submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.button-create")));
        submitButton.click();

        Thread.sleep(1000);

        acceptAlert("Número de telefone já existe. Por favor, insira um número de telefone único.");

        Thread.sleep(1000);
    }



}
