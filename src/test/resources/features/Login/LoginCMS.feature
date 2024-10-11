Feature: Login CMS
    @successfulLogin
  Scenario: Successful login
    Given user on the login page
    When User enter valid email and password
    And User click on the login button
    Then user should be redirected to my account dashboard

      @invalidLogin
  Scenario: Invalid login
    Given user on the login page
    When User enter invalid email and password
    And User click on the login button
    Then user should see an error message
    And remain on the login page


#Feature: Handle DataTable

  @ValidLoginMultiple
  Scenario: DataTable sample
    Given user on the login page
    When user enter valid credentials to login
      | username          | password |
      | user@example.com  | 123      |
      | admin@example.com | 123456   |
    Then user should be redirected to my account dashboard

  @InvalidLoginMultiple
  Scenario: Invalid Login with multiple account
    Given user on the login page
    When user enter invalid credentials to login
      | username          | password |
      | user@example.com  | 123      |
      | admin1@example.com | 123456   |
    Then user should see an error message
    And remain on the login page