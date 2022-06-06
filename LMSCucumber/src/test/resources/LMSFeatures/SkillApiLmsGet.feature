#SkillApi Get request
@SkillApiGet
Feature: Fetch all skill data
Scenario: To fetch all skill data for all Users
Given User is on getmethod with endpoint Skills
When User sends get request
Then Fetch all skill data 

Scenario Outline: To fetch all skill data for particular User
Given User is on url with endpoints Skills and Id
When User sends Get Method by giving particualr Id from excel <RowNumber>
Then should display Skill detials for that Id
Examples:
|RowNumber|
|0|

Scenario: To fetch all skill data for all Users giving incorrect end point
Given User is on url with endpoint Skills
When User is on Get Method with invalid endpoint
Then should display error message