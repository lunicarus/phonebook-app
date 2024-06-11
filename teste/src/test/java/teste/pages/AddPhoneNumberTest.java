/*package phonebook.test;

import org.junit.*;
import org.junit.jupiter.api.DisplayName;
import teste.pages.BaseTest;


public class AddPhoneNumberTest extends BaseTest {
    private LandingPage landingPage;
    private AddContactPage addContactPage;

    @Before
    public void setUp() {
        super.setUp();
        landingPage = new LandingPage(driver);
        addContactPage = new AddContactPage(driver);
    }

    @Test
    @DisplayName("Should add contact if phonenumber and name are valid")
    public void shouldAddContactIfPhoneNumberAndNameAreValid() {
        landingPage.open();
        landingPage.clickAddButton();
        addContactPage.enterName(faker.leagueOfLegends().champion());
        addContactPage.enterPhone(faker.phoneNumber().cellPhone());
        addContactPage.clickCreate();
        acceptAlert("Contato adicionado com sucesso!");
    }

    @Test
    @DisplayName("Should not add contact if phone number is invalid")
    public void shouldNotAddContactIfPhoneNumberIsInvalid() {
        landingPage.open();
        landingPage.clickAddButton();
        addContactPage.enterName(faker.leagueOfLegends().champion());
        addContactPage.enterPhone(faker.phoneNumber().phoneNumber() + "123");
        addContactPage.clickCreate();
        acceptAlert("Número de telefone inválido. Por favor, insira um número válido no formato correto.");
    }
}*/