Feature: Asians User registration
  As a user, register in Asians group

  @selenium
  Scenario: Register a user with valid data
    Given open the https://console.uat.asians.group/#/domain/list
    And click register button
    When enter random email and password and click register
    Then the page is redirected to homepage
    And logout of the application
    Given open the https://console.uat.asians.group/#/domain/list
    When enter the registered email and password and click login
    Then the page is redirected to homepage
    And logout of the application


  Scenario: Login with existing user - negative
    Given open the https://console.uat.asians.group/#/domain/list
    And click register button
    When enter email as "Test45@gmail.com" password as "12345qwe" and click register
    Then email already exists error message is displayed
    Then "Email already exists" error message is displayed


  Scenario: Login with invalid email - negative
    Given open the https://console.uat.asians.group/#/domain/list
    And click register button
    When enter email as "Testror" password as "12345qwe" and click register
    Then "Invalid email address" error message is displayed


  Scenario: Register with any default field left blank
    Given open the https://console.uat.asians.group/#/domain/list
    And click register button
    When enter "email" only
    Then "Please specify password" error message is displayed
    Given open the https://console.uat.asians.group/#/domain/list
    And click register button
    When enter "password" only
    Then "Please specify email" error message is displayed
    Given open the https://console.uat.asians.group/#/domain/list
    And click register button
    When enter "email and password" only
    Then "Password confirmation doesn't match" error message is displayed