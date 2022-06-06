@feature1
Feature: User is trying to delete
Scenario: To verify if User is able to delete record with valid skill Id
Given User is on Delete method endpoint url
When user send the request with valid skill Id 
Then website should display message stating Record successfully deleted
  Scenario: To Verify if User is able delete a record  with invalid skill Id
  Given User is on Delete method endpoint url
  When user send the request with invalid skill Id 
 Then website should display message Record not found
    
Scenario: To verify if User is able to delete record with valid skill name
Given User is on Delete method endpoint url
When user send the request with valid skill name 
Then website should display message stating Record successfully deleted
  Scenario: To Verify if User is able delete a record  with invalid skill name
 Given User is on Delete method endpoint url
 When user send the request with invalid skill name
 Then website should display message Record not found
    
Scenario: To Verify if User is able delete a record  with valid skill Id and skill name
Given User is on Delete method endpoint url
When user send the request with valid skill Id and skill name
Then website should display message successfully deleted skill Id and skill name
    