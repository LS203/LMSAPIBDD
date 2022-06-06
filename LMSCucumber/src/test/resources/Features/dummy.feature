Feature: to check able to create new user
@dummy
Scenario Outline:To check if user able to create new User
Given user is in post method with endpoint url
When User sends post request giving details from sheet <RowNumber>
Then shows a message sucessfully created updates created user id in excel <RowNumber>
Examples:
|RowNumber|
|0|
|1|


