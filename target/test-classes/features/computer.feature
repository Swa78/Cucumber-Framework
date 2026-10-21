
@feature_login
Feature: login Feature

@smoke @login


Scenario: Login test
Given user is on login page
When user enter correct username
And user enter correct password
And user click on login button
Then user should redirect to dashbaord

@Regression @login

Scenario: Invalid Login test
Given user is on login page
When user enter invalid username
And user enter invalid password
And user click on login2 button
Then error message should be displayed