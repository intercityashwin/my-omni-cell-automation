@Register @Reg
Feature: Register users
  I want to reguster new/duplicate users on myomni cell website

  @NewUser
  Scenario: Register new user
    Given MyOmni website is up and running
    When I fill up registration form
    Then New user should be registered