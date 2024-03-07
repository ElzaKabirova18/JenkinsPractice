Feature: user should be able to create a client

  Scenario: verify client is created
    Given new client is created using API
    Then varify client exist in database
    And delete client in database
    Then varidy client does not exist using API