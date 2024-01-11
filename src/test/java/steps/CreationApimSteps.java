package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.plugin.event.StepDefinition;
import org.example.Pages.*;
import org.example.Pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class CreationApimSteps {
    LoginPage pagelogin;
    PimPage pagePim;
    DashboardPage pageDashbord;
    PersonnalDetails detailPersonne;

    public CreationApimSteps(StandardsSteps std) {
        WebDriver driver = std.getDriver();
        pagelogin = PageFactory.initElements(driver, LoginPage.class);
    }

    @And("j'entre un username {string}")
    public void jentre_unsername(String usename) {
        pagelogin.inputUserName(usename);
    }

    @And("j'entre un passeword {string}")
    public void jentre_password(String psw) {
        pagelogin.inputPassword(psw);

    }

    @And("je clique sur le bouton login")
    public DashboardPage cliqueLogin() {
        pageDashbord = pagelogin.clickLogin();
        return pageDashbord;

    }

    @And("je clique sur Apim")
    public PimPage cliqueApim() {
        pagePim = pageDashbord.gotoPIM();
        return pagePim;
    }

    @And("je clique sur le bouton Add")
    public void cliqueAddButton() {
     pagePim.clickAddButton();

    }
    @And("je renseigne un firstname {string}")
    public void jentreUserName(String userName) {
        pagePim.inputFirstName(userName);

    }
    @And("je renseigne un middlename {string}")
    public void jentremiddlename(String middleName) {
        pagePim.inputMiddleName(middleName);

    }
    @And("je renseigne un lastname {string}")
    public void jentreLastName(String lastName) {
        pagePim.inputLastName(lastName);

    }
    @And("je click sur le bouton save")
    public PersonnalDetails cliqueSaveButton() {
       detailPersonne= pagePim.createUser();
       return detailPersonne;

    }

    @Then("la page Personal Details saffiche {string}")
    public void goToPersonnalDetails(String t) {
        detailPersonne.checkPageTitle(t);

    }



}
