Feature: Testing person REST API

  Scenario: Adding new person
    Given following person:
      | firstName | Kairat   |
      | lastName  | Rakhimov |
    When client call "POST" "http://localhost:8888/api/person"
    Then client should receive status 200
    And following person in response:
      | id        | 1        |
      | firstName | Kairat   |
      | lastName  | Rakhimov |

  Scenario: Getting existing person by ID
    Given following person:
      | firstName | Kairat   |
      | lastName  | Rakhimov |
    When client call "GET" "http://localhost:8888/api/person/1"
    Then client should receive status 200
    And following person in response:
      | id        | 1        |
      | firstName | Kairat   |
      | lastName  | Rakhimov |

  Scenario: Updating existing person
    Given following person:
      | firstName | Bob      |
      | lastName  | Marley   |
    When client call "PUT" "http://localhost:8888/api/person/1"
    Then client should receive status 200
    And following person in response:
      | id        | 1        |
      | firstName | Bob      |
      | lastName  | Marley   |

  Scenario: Deleting existing person
    When client call "DELETE" "http://localhost:8888/api/person/1"
    Then client should receive status 200
