#SkillAPI Put request
@SkillApiPut
Feature: User is trying to update the existing detials
Scenario Outline: User is on Put method with end point url
Given user sends the Put request with endpoint url Skills
When user is on  the put request giving  Skill Id value form excel <rownum>
Then  should display message as successfully updated
Examples:
|rownum|
|0|
Scenario: To check if User able to access with invalid url
    Given User is on the PutRequest  by giving invalid url
    When User sends the put Request giving incorrect url
    Then Errormessage with status code is shown