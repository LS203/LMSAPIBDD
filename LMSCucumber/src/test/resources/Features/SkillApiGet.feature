@feature1
Feature: New User registration
  Scenario: To  Check Authorization without Providing Username & Password
    Given Users is on Get Method with end point url/Skills
    When User sends the request
    Then User should receive not authorized message stating 401 unauthorized
  Scenario: To check Authorization with Invalid username and correct password
    Given Users is on Get Method with end point url/Skills
    When User sends the request
    Then User should receive not authorized message stating 401 unauthorized
    
   Scenario: To check Authorization with valid username and invalid password
   Given Users is on Get Method with end point url/Skills
   When User sends the request
   Then User should receive not authorized message stating 401 unauthorized
   
   Scenario: To check Authorization with valid username and valid password
   Given  Users is on Get Method with end point url/Skills
   When User sends the request
   Then User should receive authorized message stating 200 Ok login succesful
   
   Scenario: To Check if able to read all the Skills
   Given User is on GET method with end point url
   When User sends the request
   Then website should display all the skills with Skill_id and Skill_name
   
   Scenario: To Check if able to read Skill_id
   Given User is on GET method with end point url
   When User sends the request  
   Then website should display Skill_id details
   
   Scenario: To Check if able to read Skill_name
   Given User is on GET method with end point url
   When User sends the request
   Then website should should display Skill_name
   
   Scenario: To Check if able to read Skill with endpoint url/Skills?{id=value}
   Given User is on GET method with end point url
   When User sends the request
   Then website should diplay Skill_Id with values
   
   Scenario: To Check if able to read with invalid Skill_id
   Given User is on GET method with end point url
   When User sends the request with input invalid Skill_Id
   Then website should display message stating Invalid ID
   
   Scenario: To Check if able to read with invalid Skill_name
   Given User is on GET method with end point url
   When User sends the request with invalid skil name
   Then website should display message Invalid skill name