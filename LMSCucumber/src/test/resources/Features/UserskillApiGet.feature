#UserSkillAPI GET METHOD
@GetMethodUserSkillApi
Feature: GET user Feature

  Scenario: To Check user_skill_id,user_id,Skill_Id,months_of_exp
    Given User is on GETMethod with the endpoint url UserSkills
    When User sends the request
    Then User recieves Sucess status

  Scenario: To Check user_skill_id
    Given User is on GET Method To Check user_skill_id with the end point url
    When User sends the request To Check user_skill_id
    Then User able to see the records

  Scenario: To Check if the User is able to read all the records
    Given User is on GET Method  with endpoints  url
    When User sends the request toread all the records
    Then User recieves 200 ok status

  Scenario: To Check if the User is able to read Specific User record
    Given User is on GET Method  with endpoints  url
    When User sends the request with existing User_id
    Then User able to read all the records

  #Get All Skills All Users
  Scenario: Get Request for All Users and All Skills are mapped and present in the database
    Given User is on Get Method with end point url
    When User sends the request
    Then The required Fields are displayed as ""id"",""firstName"",""lastName"",""skillmap""

  Scenario: To Check User and his  Skills are mapped and present in the database
    Given User is on Get Method with the end point url
    When User sends the request
    Then The User and his Skillmaps are displayed recieves 200 status

  Scenario: To Check User and his Skill ID present in the database
    Given User is on Get Method with the end point url
    When User sends the request
    Then User Id and particular Skill ID is displayed recieves 200 status

  Scenario: To Check User and Invalid Skill_ID present in the database
    Given User is on Get Method with the end point url
    When User sends the request
    Then User Id with First name and Lastname are displayed recieves 404 Not Found status
  