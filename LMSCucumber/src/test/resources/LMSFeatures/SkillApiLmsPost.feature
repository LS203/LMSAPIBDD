#SkillApi post request
@SkillApiPost
Feature: user tries to create new Skill details

Scenario Outline: to check if User can create newskills
Given User is in the postrequest with endpoint url
When User sends postrequest gives input Skill details from excel <rownum>
Then User recieves message reponse as sucessfully created with skill id and updates in excel <rownum>
Examples:
|rownum|
|0|
Scenario: User tries to access with invalid endpoint
    Given User is on the PostRequest with incorrect endpoint
    When User sends the PostRequest giving invalid endpoint
    Then Error message is shown