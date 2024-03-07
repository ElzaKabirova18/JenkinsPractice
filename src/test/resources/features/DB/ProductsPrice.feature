Feature: product price

  @priceVerification
  Scenario: verify product prices are within range of 1 to 10000
    Given i set up connection to database
    And i retrieve all product prices
    Then i verify prices are between 1 and 10000
