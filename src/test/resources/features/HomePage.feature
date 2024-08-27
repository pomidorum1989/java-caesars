Feature: Home Page

  @Smoke
  Scenario: Verify Apple Page Title
    Given I open the "https://www.apple.com" page
    Then the title should be "Apple"

  @Smoke
  Scenario: Verify Google Page Title
    Given I open the "https://www.google.com" page
    Then the title should be "Google"

  @Smoke
  Scenario: Verify Tesla Page Title
    Given I open the "https://www.tesla.com" page
    Then the title should be "Tesla"

  @Smoke
  Scenario: Verify Adobe Page Title
    Given I open the "https://www.adobe.com" page
    Then the title should be "Adobe"