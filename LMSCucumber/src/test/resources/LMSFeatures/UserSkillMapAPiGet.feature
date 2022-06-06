#UserSkillMapApi
@UserSkillMap
Feature: GET user Feature
Scenario: To Check all UserSkill data
    Given User is on GET Method to fetch all UserSkills details
    When User is on Get request with endpoint url UserSkills
    Then User recieves Sucess status with all UserSkill data shown
Scenario Outline: To Check the UserSkill of particular Id
Given User is on GETMethod with to fetch UserSkill data
    When User sends the request with endpoint by giving Id from excel <rownum>
    Then User Skilldetails of given Id is shown
    
    Examples:
    |rownum|
    |0|
    
    
Scenario: To check if User able to access with invalid url
    Given User is on  the Getrequest  giving invalid url
    When User sends the get request with invalid url
    Then Error message will be shown