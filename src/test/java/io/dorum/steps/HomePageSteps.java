package io.dorum.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.dorum.pages.HomePage;
import io.dorum.utils.WaitUtils;
import org.testng.Assert;

public class HomePageSteps {
    private HomePage homePage;

    @Given("I open the {string} page")
    public void i_open_the_home_page(String url) {
        homePage = new HomePage();
        homePage.openPage(url);
        if (url.contains("adobe")) {
            homePage.isAdobePageLoaded();
        }
        homePage.waitForPageToLoad(5);
    }

    @Then("the title should be {string}")
    public void the_title_should_be(String expectedTitle) {
        String actualTitle = homePage.getPageTitle();
        WaitUtils.waitForUrlToContain(expectedTitle);
        Assert.assertEquals(actualTitle, expectedTitle);
    }
}
