package teste.pages;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AddPhoneNumberTest extends BaseTest {

    private void navigateToAddPhonePage() {
        // Acessar a página inicial
        driver.get("https://master--agenda-publica.netlify.app/");

        // Adicionar espera explícita para garantir que o botão "Adicionar" esteja presente
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement addButton = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Adicionar")));
        addButton.click();
    }

    @Test
    public void shouldAddContactIfPhoneNumberAndNameAreValid() {
        navigateToAddPhonePage();


        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement nameField = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("nome")));
        WebElement phoneField = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("tel")));


        nameField.sendKeys(faker.name().fullName());
        phoneField.sendKeys(faker.phoneNumber().cellPhone());


        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.button-create")));
        submitButton.click();

        // Verificação do alerta com o texto correto
        acceptAlert("Contato adicionado com sucesso!");
    }


}
