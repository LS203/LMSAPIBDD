@feature1
    Feature: User is registered and is trying to update his profile 
  
    Scenario: To Check if able to create new Skill Record with invalid values
    Given User is on POST method with end point url/Skills
    When User sends the request with input as invalid skill_id and skill_name
    Then website should display message stating Invalid ID and skill name
    
    Scenario: To Check if able to create new skill record
    Given User is on POST method with end point url/Skills
    When User sends the request with valid input as Skill_id ,Skill_name
    Then website should display a message stating profile successfully updated
    
   Scenario: To Check for Creation Time and Last Modified Time
   Given: User is on POST method with end point url/Skills
   When User sends the request with input as Skill_id ,Skill_name
   Then website should display Creation and Last Modified Time with current date/time in the profile
   
   Scenario: To check if able to create record when input fields are empty
   Given User is on POST method with end point url/Skills
   When User does not enter values in request fields 
   Then website should display message stating Required fields please enter values