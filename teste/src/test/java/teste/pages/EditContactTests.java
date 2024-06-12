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

}

