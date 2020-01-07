Feature: Person End Point
  Background:
    * url 'http://localhost:8080'
    * header Accept = 'application/json'

  Scenario: Testing valid GET endpoint
    Given path 'person/all'
    When method GET
    Then status 200
    * def first = response[0]
    And match first contains {firstName:"Aliko"}


  Scenario: Testing OK reponse GET Person by email
    Given  path 'person'
    And param email = 'yelouardi@sqli.com'
    When method GET
    Then status 200
    And match $ contains {firstName:"Yassine"}

  Scenario: Testing error response  GET Person by email
    Given  path 'person'
    And param email = 'ko@sqli.com'
    When method GET
    Then status 404

  Scenario: Testing OK reponse GET Persons by profile
    Given  path 'person/all/profile'
    And param title = 'developpeur'
    When method GET
    Then status 200

  Scenario: Testing error response  GET Persons by profile
    Given  path 'person/all/profile'
    And param title = 'ko'
    When method GET
    Then status 404
    And match $ == "This Title not Found"

  Scenario: Add new Person OK response
    def
    Given  path 'person'
    And request { firstName: 'ETS' ,lastName: 'OOKDn' ,mailAdresses: 'yelo@gm.com' ,birthDate: '1973-11-17' ,profile: 'Business Analyste Junior' }
    When method POST
    Then status 201
    And def person = response

    Given path 'person'
    And param email = person.mailAdresses
    When method GET
    Then status 200
    And match $ contains {firstName:"ETS"}

  Scenario: Add new Person KO response
    Given  path 'person'
    And request { firstName: 'ETS' ,lastName: 'OOKDn' ,mailAdresses: 'yelo@gm.com' ,birthDate: '1973-11-17' ,profile: 'Business Analyste Junior' }
    When method POST
    Then status 302
    And match $ == "This User is Founded"