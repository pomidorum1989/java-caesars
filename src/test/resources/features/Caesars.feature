Feature: Caesar login functionality

  @iGaming
  Scenario: Player can not log with invalid username and password
    When I open login page
    Then I enter incorrect credentials "123" and "123456"
    Then there is an error informing me about incorrect credentials