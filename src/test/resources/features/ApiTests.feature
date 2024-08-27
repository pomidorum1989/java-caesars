Feature: API Testing with REST-assured

  @API
  Scenario: Check Google maps
    Given I have the base URL
    When I send a GET request to "maps"
    Then the response status code should be 200
    And the response content type should be "text/html; charset=UTF-8"