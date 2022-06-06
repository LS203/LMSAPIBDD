#UserSkillMapApi Delete
@UserSkillMapiDelete
Feature: Deleting an existing User
Scenario Outline: To Delete UserSkill details for particular Id
    Given User is on delete Method with the end point url UserSkills and giving Id
    When User sends Delete request giving value from excel <rownum>
    Then Response message User Record is successfully deleted is shown
    Examples:
    |rownum|
    |0|
    
Scenario Outline:To Delete UserSkill_Id  for non existing ID
    Given User is in delete Method with the endpoint url UserSkills and giving  non existing Id
    When User sends the deleterequest giving non existing Id from sheet <rownum>
    Then User Not found is shown
    Examples:
    |rownum|
    |0|
