#Background: Test users API Get request
Feature: Authorization is set as user has already created an Account
Scenario: To Get all users details
Given user set get method with Endpoint/url/Users
When user sends request
Then Json Schema is valid and User receices status code 200 and should gets list of  of all users 
Scenario: To Get User details for specific user Id
Given user set get method with Endpoint/url/Users/Id
When user sends request for specific User ID
Then Json Schema is valid and User receices status code 200 and should gets the details of specific user Id
Scenario: To get User details for invalid user Id
Given user set get method with Endpoint/url/Users/Id
When user sends request with invalid user Id
Then user recieves status code 404 and should not gets the details of specific user Id
Scenario: To get User details for blank user Id
Given user set get method with Endpoint/url/Users/Id
When user sends request with blank user Id
Then user recieves status code 404 and should not gets the details of specific user Id
Scenario: To get User details for user Id with Negetive number
Given user set get method with Endpoint/url/Users/Id
When user sends request with Negetive numbers user Id
Then user recieves status code 404 and should not gets the details of specific user Id
Scenario: To get User details for user Id with decimal
Given user set get method with Endpoint/url/Users/Id
When user sends request with desimal user Id
Then user recieves status code 404 and should not gets the details of specific user Id