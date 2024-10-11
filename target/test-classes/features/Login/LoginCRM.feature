Feature: Login to CRM
  As an user, I want to be able to log into CRM system
  So that I can manage customer information

  Background: Load data test from excel
    Given load data test from excel

    @successfulLogin
  Scenario: Successfully login
    Given I am on login page
    When I enter user name and password
    And I click on the login button
    Then I should be taken to the Dashboard page
    And I should see the Customer menu

  Scenario: Failure login
    Given I am on login page
    When I enter user name and password
    And I click on the login button
    Then I should be taken to the Dashboard page
    And I should see the Customer menu

  Scenario Outline: Successfuly login 2
    Given I am on login page
    When I enter "<username>" and "<password>"
    And I click on the login button
    Then I should be taken to the Dashboard page
    And I should see the Customer menu

    Examples:
    | username | password|
    | usernam1  | pass1   |
    | usernam2  | pass2   |

  Scenario: Successfully Login
    When user login to the system
    Then user should see "Cutomers" menu