package io.dorum.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.dorum.pages.LoginPage;
import org.testng.Assert;


public class IGamingSteps {
    private LoginPage loginPage = new LoginPage();

    @Given("I open login page")
    public void i_open_login_page() {
        loginPage.openCaesars();
    }

    @Then("I enter incorrect credentials")
    public void then_and_i_enter_incorrect_credentials() {
        loginPage.openAccount();
        loginPage.fillLoginField();
        loginPage.fillPasswordFiled();
        loginPage.clickLoginBtn();
    }

    @Then("there is an error informing me about incorrect credentials")
    public void then_there_is_an_error_informing_me_about_incorrect_credentials() {
        Assert.assertTrue(loginPage.isErrorMessageVisible(), "Error message is not displayed");
    }
}
