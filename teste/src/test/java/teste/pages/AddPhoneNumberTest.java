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

    private boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    @Test
    @DisplayName("Should add contact if phone number and name are valid")
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
    @DisplayName("Should not add contact if name has special chars or numbers")
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
    @DisplayName("Should show error for duplicate phone")
    public void shouldShowErrorForDuplicatePhone() throws InterruptedException {
        navigateToAddPhonePage();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement nameField = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("nome")));
        WebElement phoneField = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("tel")));

        Thread.sleep(500);

        String validPhoneNumber = faker.phoneNumber().cellPhone();

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
    @DisplayName("Should show error for empty phone")
    public void shouldShowErrorForEmptyPhone() {
        navigateToAddPhonePage();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement nameField = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("nome")));
        WebElement phoneField = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("tel")));

        Thread.sleep(1000);

        String validName = faker.name().fullName();

        nameField.sendKeys(validName);
        phoneField.sendKeys("");

        Thread.sleep(500);

        acceptAlert("Número de telefone inválido. Por favor, insira um número válido no formato correto. ")
    }


    @Test
    @DisplayName("Should show error for empty name")
    public void shouldShowErrorForEmptyName() throws InterruptedException {
        navigateToAddPhonePage();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement phoneField = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("tel")));

        Thread.sleep(500);

        phoneField.sendKeys(faker.phoneNumber().cellPhone());

        Thread.sleep(500);

        WebElement su   bmitButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.button-create")));
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

    @Test
    @DisplayName("Should accept name with accents or tilde")
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

    @Test
    @DisplayName("Should accept names with abbreviations")
    public void shouldAcceptNamesWithAbbreviations() throws InterruptedException {
        navigateToAddPhonePage();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement nameField = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("nome")));
        WebElement phoneField = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("tel")));

        Thread.sleep(1000);

        // Usar um nome abreviado para testar a validação
        String[] abbreviatedNames = {"Vini Jr.", "Ana Sr."};
        for (String abbreviatedName : abbreviatedNames) {
            nameField.clear();
            phoneField.clear();

            nameField.sendKeys(abbreviatedName);
            phoneField.sendKeys(faker.phoneNumber().cellPhone());

            Thread.sleep(1000);

            WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.button-create")));
            submitButton.click();

            Thread.sleep(1000);

            acceptAlert("Contato adicionado com sucesso!");

            Thread.sleep(1000);

            // Navegar de volta para a página de adição para testar o próximo nome
            navigateToAddPhonePage();
        }
    }





}
