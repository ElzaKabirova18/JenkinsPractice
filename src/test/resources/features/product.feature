Feature: Product controller test

  @productCreate
  Scenario: Create product and validate data
  Given use RequestBody nd create new product
  Then get same product by id and validate data
  Then delete same product by id
