Feature: create new seller and get it data using his seller_id

  @singleSeller
  Scenario: user should be able to create single seller and get its data using his ID
    Given path "/api/myaccount/sellers"
    When user add variables for creating a new single seller
    When user creates seller_id, should be able to get seller_id
    Then user should be able get this seller using seller_id and using path "/api/myaccount/sellers/"
    And  shoul be able validate all variables from response like: seller_name, email, address, phone_number
#    Then delete same product by id