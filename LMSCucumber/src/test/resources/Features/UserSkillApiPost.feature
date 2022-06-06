
# POSTuserFeature
Feature: Title of your create feature
  Scenario: To Check if the User is able to Create New User Record
    Given User is on Post Method with the end point url
    When User sends the request with user_skill_id,user_id,Skill_id,months_of_exp
    Then Message_Response is Successfully

  Scenario: To Check  if the User is not able to Create Record
    Given User is on Post Method with the end point url
    When User sends the invalid request
    Then Message _Response is Failed to Create the Record

  Scenario: To Check Skill_Id and User_Id is in the database
    Given User is on Post Method with the end point url
    When User sends the request with Skill_Id and User_id along with other inputs
    Then Message_Response is Successfully Created