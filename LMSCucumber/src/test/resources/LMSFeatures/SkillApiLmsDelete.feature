#SkillApi Delete
@SkillApiDelete
Feature: User is trying to delete 
Scenario Outline: To verify if User is able to delete record with valid skill Id
Given User is on Delete method endpoint url Skills/Id
When user is on delete request with valid skill Id given from excel <rownum>
Then should display message  Record successfully deleted
Examples:
|rownum|
|0|
Scenario: To check if User able to access with invalid url
    Given User is on the GetRequest  by giving incorrect url
    When User sends the getMethod with invalid url
    Then Errormessage is displayed