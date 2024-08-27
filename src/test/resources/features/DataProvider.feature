Feature: DataProvider Example

  @Smoke
  Scenario Outline: Verify Page Title
    Given I open the "<url>" page
    Then the title should be "<title>"

    Examples:
      | url                    | title  |
      | https://www.google.com | Google |
      | https://www.apple.com  | Apple  |
      | https://www.tesla.com  | Tesla  |
      | https://www.adobe.com  | Adobe  |