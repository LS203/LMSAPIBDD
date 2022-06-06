#UserSkillMap Post
@UserSkillMappost
Feature: To check if able to create New User

  Scenario Outline: User creating a new record using post
    Given User is on Post Method with the end point url UserSkills
    When User sends Postrequest giving the values for user_skill_id,user_id,Skill_id,months_of_exp from excel <rownum>
    Then MessageResponse is Successfully created is shown
Examples:
|rownum|
|0|
  Scenario Outline: User creating a new record with invalid user id
    Given User is on Post Method with end point url UserSkills
    When User is on Post request trying to create new User skill id by giving invalid id from excel <rownum>
    Then Error message should be shown
    Examples:
    |rownum|
    |0|