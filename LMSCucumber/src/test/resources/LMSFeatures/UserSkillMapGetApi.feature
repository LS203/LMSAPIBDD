#UserSkillMapGetApi
@UserSkillMapGetAPI
Feature: To check All Users with all Skill detials
Scenario: To get all Users with all Skills details
    Given User is on endpoint url UserSkillsMap
    When User is on Get method endpoint url UserSkillsMap
    Then List of all users with all Skill details are listed

  Scenario Outline: List particular user with the skill details
    Given User is on endpoint url UserSkillsMap userId
    When User is on Get request with giving specific Id from the excel <rownum>
    Then User Skill details are shown
   Examples:
   |rownum|
   |0| 

  Scenario Outline: List all users details by SKILL_ID
    Given User is on endpoint url/UserSkillsMap/skillId
    When User is on Get request with giving SkillId from the excel <rownum>
    Then List of all User details by skillId is shown
Examples:
|rownum|
|0|

  Scenario: To check if User able to access with invalid url
    Given User is on Get request 
    When User sends get request with incorrect url
    Then Errormessage with status code 404 is shown
