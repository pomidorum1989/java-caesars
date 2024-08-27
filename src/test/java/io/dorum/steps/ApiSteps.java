package io.dorum.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;

public class ApiSteps {

    private Response response;

    @Given("I have the base URL")
    public void i_have_the_base_url() {
        RestAssured.baseURI = "https://www.google.com/";
    }

    @When("I send a GET request to {string}")
    public void i_send_a_get_request_to(String endpoint) {
        response = RestAssured.given()
                .filter(new AllureRestAssured())
                .when()
                .get(endpoint);
    }

    @Then("the response status code should be {int}")
    public void the_response_status_code_should_be(int statusCode) {
        Assert.assertEquals(response.getStatusCode(), statusCode);
    }

    @Then("the response content type should be {string}")
    public void the_response_content_type_should_be(String contentType) {
        Assert.assertEquals(response.getContentType(), contentType);
    }
}
