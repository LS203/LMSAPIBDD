#UserSkillMap Put request
@UserSkillMapPut
Feature: Updating the existing UserSkill detials

  Scenario Outline: To Check if the User is able to Update the  Record
    Given User is on Put Method with the end point url UserSkills 
    When User sends the Put request to Update the record and giving Id from excel <rownum>
    Then Message response shown as record succesfully updated
    Examples:
    |rownum|
    |0|

Scenario Outline: To check update for non existing Id
    Given User is on Put Method with the end point url 
    When User sends the Put request to Update the details giving non existing Id from excel <rownum>
    Then No User found message is shown
    Examples:
    |rownum|
    |0|
  
