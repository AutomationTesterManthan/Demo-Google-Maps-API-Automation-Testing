Feature: Validating Demo Google Maps API

  Scenario: Verifying add place api
    Given User calls the baseURI
    And Also provides the add place payload in the body
    When User calls the "AddPlace" using "POST" http request
    Then Validate the "addPlaceResponse" response with status 200
    And Also Validate the response "status" as "OK"
    And Extract place ID from the response

  Scenario Outline: Validating the get place api
    Given User calls the baseURI
    When User calls the "GetPlace" using "GET" http request
    Then Validate the "getPlaceResponse" response with status 200
    And Also Validate the response for "<name>" "<address>" "<website>"

    Examples: 
      | name        | address                | website        |
      | Stark Tower | Times Square, New York | www.google.com |

  Scenario: Validating update place api
  	Given User calls the baseURI
    When User calls the "UpdatePlace" using "PUT" http request
    Then Validate the "updatePlaceResponse" response with status 200
    And Also validate the response for "<msg>" 
    
    Examples:
    | msg                          |
    | Address successfully updated |
    
  Scenario Outline: Validating the get place api
    Given User calls the baseURI
    When User calls the "GetPlace" using "GET" http request
    Then Validate the "UpdatedgetPlaceResponse" response with status 200
    And Also Validate the response for "<address>" is updated

    Examples: 
      | address                |
      | Times Square, New York |
      
  Scenario: Deleting a place
  	Given User calls the baseURI
  	When User calls the "DeletePlace" using "DELETE" http request
  	Then Validate the "DeletePlaceResponse" response with status 200
  	And Also Validate the response "status" as "OK"
    