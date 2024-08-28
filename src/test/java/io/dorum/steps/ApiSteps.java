package io.dorum.steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.dorum.dto.CommentsDTO;
import io.dorum.dto.PostDTO;
import io.dorum.utils.ApiHelper;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static io.dorum.utils.ApiHelper.readJsonFromFile;

public class ApiSteps {

    private static final ThreadLocal<Response> threadLocalResponse = new ThreadLocal<>();

    @When("I send a GET request to {string}")
    public void i_send_a_get_request_to(String endpoint) {
        threadLocalResponse.set(RestAssured.given()
                .filter(new AllureRestAssured())
                .when()
                .get(endpoint));
    }

    @Then("the response status code should be {int}")
    public void the_response_status_code_should_be(int statusCode) {
        Assert.assertEquals(threadLocalResponse.get().getStatusCode(), statusCode, "Incorrect response code: "
                + threadLocalResponse.get().getStatusLine());
    }

    @Then("the response content type should be {string}")
    public void the_response_content_type_should_be(String contentType) {
        Assert.assertEquals(threadLocalResponse.get().getContentType(), contentType);
    }

    @Then("the response body should be the following JSON:")
    public void the_response_body_should_be_json(String json) {
        Assert.assertEquals(threadLocalResponse.get().getBody().as(PostDTO.class), ApiHelper.convertJsonStringToDTO(json, PostDTO.class));
    }

    @Then("the response body should be the following Data table:")
    public void the_response_body_should_be_data_table(DataTable table) {
        List<Map<String, String>> data = table.asMaps(String.class, String.class);
        Map<String, String> dataRow = data.getFirst();
        PostDTO expectedBody = new PostDTO();
        expectedBody.setUserId(dataRow.get("userId"));
        expectedBody.setId(dataRow.get("id"));
        expectedBody.setTitle(dataRow.get("title"));
        expectedBody.setBody(dataRow.get("body"));
        PostDTO actualBody = threadLocalResponse.get().getBody().as(PostDTO.class);
        Assert.assertEquals(actualBody, expectedBody);
    }

    @Then("the response body with id {int} should be the following JSON:")
    public void the_response_body_should_be_json_1(int id) throws IOException {
        LinkedHashMap<String, Object> map = threadLocalResponse.get().jsonPath().get(String.format("find { it.id == %d }", id));
        CommentsDTO expectedPost = ApiHelper.convertJsonStringToDTO(readJsonFromFile("src/test/resources/json/comments.json"), CommentsDTO.class);
        CommentsDTO actualPost = new CommentsDTO();
        actualPost.setPostId((Integer) map.get("postId"));
        actualPost.setId((Integer) map.get("id"));
        actualPost.setName((String) map.get("name"));
        actualPost.setEmail((String) map.get("email"));
        actualPost.setBody((String) map.get("body"));
        Assert.assertEquals(actualPost, expectedPost);
    }

    @When("I send a get request to {string} with token {string} with parameter {string} and {string} value")
    public void i_send_a_get_request_with_auth_to(String url, String token, String parameter, String value) {
        threadLocalResponse.set(RestAssured.given()
                .filter(new AllureRestAssured())
                .header("Authorization", "Bearer " + token)
                .header("Accept", "application/json")
                .queryParam(parameter, value)
                .when()
                .get(url));
    }
}
