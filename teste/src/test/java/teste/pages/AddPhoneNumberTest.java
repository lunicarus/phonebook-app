package teste.pages;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertTrue;

public class AddPhoneNumberTest extends BaseTest {

    private void navigateToAddPhonePage() throws InterruptedException {
        driver.get("https://master--agenda-publica.netlify.app/");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement addButton = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Adicionar")));

        addButton.click();

        Thread.sleep(500);
    }

    @Test
    public void shouldAddContactIfPhoneNumberAndNameAreValid() throws InterruptedException {
        navigateToAddPhonePage();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement nameField = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("nome")));
        WebElement phoneField = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("tel")));

        Thread.sleep(500);

        nameField.sendKeys("Vinicius Martins");
        phoneField.sendKeys(faker.phoneNumber().cellPhone());

        Thread.sleep(500);

        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.button-create")));
        submitButton.click();

        Thread.sleep(500);

        acceptAlert("Contato adicionado com sucesso!");

        Thread.sleep(500);
    }

    @Test
    public void shouldNotAddContactIfNameHasSpecialCharsOrNumbers() throws InterruptedException {
        navigateToAddPhonePage();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement nameField = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("nome")));
        WebElement phoneField = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("tel")));

        Thread.sleep(500);

        nameField.sendKeys("John Doe 123 @#");
        phoneField.sendKeys(faker.phoneNumber().cellPhone());

        Thread.sleep(500);

        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.button-create")));
        submitButton.click();

        Thread.sleep(500);

        acceptAlert("Nome inválido. Por favor, insira um nome válido sem números ou caracteres especiais.");

        Thread.sleep(500);
    }

    @Test
    public void shouldShowErrorForDuplicatePhone() throws InterruptedException {
        navigateToAddPhonePage();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement nameField = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("nome")));
        WebElement phoneField = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("tel")));

        Thread.sleep(500);

        String validPhoneNumber = faker.phoneNumber().cellPhone();

        // Adicionar um contato válido
        nameField.sendKeys("Valid Name");
        phoneField.sendKeys(validPhoneNumber);

        Thread.sleep(500);

        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.button-create")));
        submitButton.click();

        Thread.sleep(500);

        acceptAlert("Contato adicionado com sucesso!");

        Thread.sleep(500);

        navigateToAddPhonePage();

        nameField = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("nome")));
        phoneField = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("tel")));

        Thread.sleep(500);

        // Tentar adicionar o mesmo número de telefone novamente
        nameField.sendKeys("Another Valid Name");
        phoneField.sendKeys(validPhoneNumber);

        Thread.sleep(500);

        submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.button-create")));
        submitButton.click();

        Thread.sleep(500);

        acceptAlert("Número de telefone já existe. Por favor, insira um número de telefone único.");

        Thread.sleep(500);
    }

    @Test
    public void shouldShowErrorForEmptyName() throws InterruptedException {
        navigateToAddPhonePage();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement phoneField = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("tel")));

        Thread.sleep(500);

        phoneField.sendKeys(faker.phoneNumber().cellPhone());

        Thread.sleep(500);

        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.button-create")));
        submitButton.click();

        Thread.sleep(500);

        // Verificar se o campo nome está com a validação required
        WebElement nameField = driver.findElement(By.id("nome"));
        assertTrue(nameField.getAttribute("required") != null);

        // Verificar que o alerta não é emitido porque o formulário não é submetido
        boolean alertPresent = isAlertPresent();
        assertTrue(!alertPresent);

        Thread.sleep(500);
    }

    private boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    @Test
    public void shouldAcceptNameWithAccentsOrTilde() throws InterruptedException {
        navigateToAddPhonePage();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement nameField = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("nome")));
        WebElement phoneField = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("tel")));

        Thread.sleep(500);

        String nameWithAccents = "José da Silva São";

        nameField.sendKeys(nameWithAccents);
        phoneField.sendKeys(faker.phoneNumber().cellPhone());

        Thread.sleep(500);

        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.button-create")));
        submitButton.click();

        Thread.sleep(500);

        // Verificar se o alerta de sucesso é exibido
        acceptAlert("Contato adicionado com sucesso!");

        Thread.sleep(500);
    }



}
