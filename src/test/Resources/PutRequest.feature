Feature: Hit Put request

  Background: User create the request
  Scenario: hit and validate the schema
    Given User create the request and hits the API
    Then Schema will be validated
