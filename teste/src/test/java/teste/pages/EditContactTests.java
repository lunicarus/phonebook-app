package teste.pages;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import pages.AddContactPage;
import pages.ConsultContactPage;
import pages.LandingPage;

class EditContactTests extends BaseTest {
    private LandingPage landingPage;
    private ConsultContactPage consultContactPage;
    private AddContactPage addContactPage;

    @BeforeEach
    public void setUp() {
        super.setUp();
        landingPage = new LandingPage(driver);
        consultContactPage = new ConsultContactPage(driver);
        addContactPage = new AddContactPage(driver);
    }

    @Test
    @DisplayName("Should edit the name of a contact")
    void shouldEditTheNameOfAContact() {
        landingPage.open();
        landingPage.clickAddButton();
        addContactPage.enterName(faker.leagueOfLegends().champion());
        addContactPage.enterPhone(faker.phoneNumber().cellPhone());
        addContactPage.clickCreate();
        acceptAlert("Contato adicionado com sucesso!");
        landingPage.open();
        landingPage.clickConsultButton();
        consultContactPage.clickFirstReadButton();
        WebElement nameInput = consultContactPage.getFirstContactNameInput();
        nameInput.click();
        nameInput.clear();
        nameInput.sendKeys(faker.name().firstName());
        consultContactPage.clickFirstReadButton();
        acceptAlert("Contato atualizado com sucesso!");
    }

    @Test
    @DisplayName("Should edit the phone number of a contact")
    void shouldEditThePhoneOfAContact() {
        landingPage.open();
        landingPage.clickAddButton();
        addContactPage.enterName(faker.leagueOfLegends().champion());
        addContactPage.enterPhone(faker.phoneNumber().cellPhone());
        addContactPage.clickCreate();
        acceptAlert("Contato adicionado com sucesso!");
        landingPage.open();
        landingPage.clickConsultButton();
        consultContactPage.clickFirstReadButton();
        WebElement phoneNumberInput = consultContactPage.getFirstContactPhoneInput();
        phoneNumberInput.click();
        phoneNumberInput.clear();
        phoneNumberInput.sendKeys(faker.phoneNumber().cellPhone());
        consultContactPage.clickFirstReadButton();
        acceptAlert("Contato atualizado com sucesso!");
    }

    @Test
    @DisplayName("Should edit name and phone number of a contact")
    void shouldEditNameAndPhoneOfAContact() {
        landingPage.open();
        landingPage.clickAddButton();
        addContactPage.enterName(faker.leagueOfLegends().champion());
        addContactPage.enterPhone(faker.phoneNumber().cellPhone());
        addContactPage.clickCreate();
        acceptAlert("Contato adicionado com sucesso!");
        landingPage.open();
        landingPage.clickConsultButton();
        consultContactPage.clickFirstReadButton();
        WebElement phoneNumberInput = consultContactPage.getFirstContactPhoneInput();
        phoneNumberInput.click();
        phoneNumberInput.clear();
        phoneNumberInput.sendKeys(faker.phoneNumber().cellPhone());
        WebElement nameInput = consultContactPage.getFirstContactNameInput();
        nameInput.click();
        nameInput.clear();
        nameInput.sendKeys(faker.name().firstName());
        consultContactPage.clickFirstReadButton();
        acceptAlert("Contato atualizado com sucesso!");
    }

    @Test
    @DisplayName("Should show error for invalid name on edit")
    public void shouldShowErrorForInvalidNameOnEdit() {
        landingPage.open();
        landingPage.clickAddButton();
        addContactPage.enterName(faker.leagueOfLegends().champion());
        addContactPage.enterPhone(faker.phoneNumber().cellPhone());
        addContactPage.clickCreate();
        acceptAlert("Contato adicionado com sucesso!");
        landingPage.open();
        landingPage.clickConsultButton();
        consultContactPage.clickFirstReadButton();
        WebElement nameInput = consultContactPage.getFirstContactNameInput();
        nameInput.click();
        nameInput.clear();
        nameInput.sendKeys("a");
        consultContactPage.clickFirstReadButton();
        acceptAlert("Nome inválido. Por favor, insira um nome válido sem números ou caracteres especiais.");
    }

    @Test
    @DisplayName("Should show error for duplicate phone on edit")
    public void shouldShowErrorForDuplicatePhoneOnEdit() {
        landingPage.open();
        landingPage.clickAddButton();
        addContactPage.enterName(faker.leagueOfLegends().champion());
        addContactPage.enterPhone(faker.phoneNumber().cellPhone());
        addContactPage.clickCreate();
        acceptAlert("Contato adicionado com sucesso!");
        landingPage.open();
        landingPage.clickAddButton();
        addContactPage.enterName(faker.leagueOfLegends().champion());
        addContactPage.enterPhone("(16) 99999-9999");
        addContactPage.clickCreate();
        acceptAlert("Contato adicionado com sucesso!");
        landingPage.open();
        landingPage.clickConsultButton();
        consultContactPage.clickFirstReadButton();
        WebElement phoneNumberInput = consultContactPage.getFirstContactPhoneInput();
        phoneNumberInput.click();
        phoneNumberInput.clear();
        phoneNumberInput.sendKeys("(16) 99999-9999");
        consultContactPage.clickFirstReadButton();
        acceptAlert("Número de telefone inválido. Por favor, insira um número válido no formato correto.");
    }
}

