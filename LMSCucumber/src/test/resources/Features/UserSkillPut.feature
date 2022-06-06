@UserskillAPI
Feature: User is updating user and skill api

  Scenario: To Check if the User is able to Update the  Record
    Given User is on Put Method with the end point url
    When User sends the request to Update the record
    Then The record succesfully modified

  Scenario: To Update the User_ID and Skill_ID  in the database
    Given User is on Put Method with the end point url
    When User sends the request to Update the record
    Then The record has been modified

  Scenario: To Check the Lastmodified time should be set to current date time for PUT request
    Given User is on Put Method with the end point url
    When User Updates the record
    Then The Record has been modified and Lastmodified time is matched  to currentdate and time
