Feature: Users
#  Scenario: As a user, I would like to login to app to view all users
#  Given When login with "janice" "pass123"
#  When User calls GET all users
#  Then Client will receives status code of 200
#  And Client will receive user list size more than 0

  Scenario Outline: As a user "<user>", I would like to login to app,
                    view all users and search for my own name
    Given When login with "<username>" "<password>"
    When User calls GET all users
    Then Client will receives status code of 200
    And Client will receive user list size more than 0
    And Client will be able to search for user "<name>"

    Examples:
      | name          | username    | password        |
      | Janice Lim    | janice      | pass123         |
      | Alex Tan      | alex        | pass123         |
      | Kelly Lim     | kelly       | pass123         |
      | Gavin Wong    | gavin       | pass123         |
      | Jonathan Ong  | jonathan    | pass123         |
      | Sally Tang    | sally       | pass123         |

  Scenario: As a user, I would like to create a new user
    Given When login with "janice" "pass123"
    When User calls POST create user by map
      | name         | Mandy        |
      | username     | mandy        |
      | address      | Jurong       |
      | age          | 25           |
      | password     | pass123      |
      | roles        | USER         |
      | permissions  | ACCESS_USER  |
    Then Client will receives status code of 200
    And Client will be able to view user "Mandy"

  Scenario: A year has passed. Mandy has grown and moved house. As a user, I would like to update the user
    Given When login with "janice" "pass123"
    When User calls PUT update user by map
      | name         | Mandy          |
      | username     | mandy          |
      | address      | Bukit Panjang  |
      | age          | 26             |
      | password     | pass123        |
      | roles        | USER           |
      | permissions  | ACCESS_USER    |
    Then Client will receives status code of 200
    And Client will be able to view user "Mandy"

  Scenario: As as user, I would like to verify user created
    Given When login with "janice" "pass123"
    When User calls GET to view user
    Then Client will receives status code of 200
    And Client will be able to verify user by map
      | name         | Mandy          |
      | address      | Bukit Panjang  |
      | age          | 26             |

  Scenario: Mandy decides she no longer wants to be part of the system. As as user, I would like to delete user
    Given When login with "janice" "pass123"
    When User calls DELETE to remove user
    Then Client will receives status code of 200
    When User calls GET all users
    Then Client will receives status code of 200
    And Client will NOT be able to find user "Mandy"