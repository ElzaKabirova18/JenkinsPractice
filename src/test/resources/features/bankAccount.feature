Feature: Test bank Accounts

  @bankAccountSeveralUsers
  Scenario Outline: Create many bank accounts
    Given Create bank account "/api/myaccount/bankaccount", Create "<type of pay>", "<bank_account_name>", "<description>", <balance>
    When user gets same bank account by id, check status code is 200
    Then delete bank Account by ID

    Examples:
      | type of pay | bank_account_name | description | balance |
      | CASH        | financial LLC     | money       | 5000    |
      | CASH        | ELZA LLC          | BIGmoney    | 120000  |
      | CASH        | new Co            | debit       | 500     |
      | BANK        | USA Bank          | credit      | 90      |
      | BANK        | Russia Bank       | deposits    | 455     |
      | BANK        | Kazakh Bank       | checks      | 100000  |