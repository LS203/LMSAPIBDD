@feature1
  Feature: User is trying to modify his profile infrmation
 
  Scenario: To update the record with Valid skill Id, skill name
  Given User is on Put method with end point url
  When user sends the request with inputs valid skill Id, skill name
  Then website should display message stating Profile successfully modified
    
 Scenario: To update the record with InValid skill Id, skill name
 Given User is on Put method with end point url
 When user sends the request with inputs Invalid skill Id, skill name
 Then website should display message stating Invalid skill Id, skill name
 
 Scenario: Verify the Last modified date and time changes
 Given User is on Put method with end point url
 When user sends the request with inputs valid skill Id, skill name
 Then website should display only last modified date and time in the profile
 
 Scenario: User does not enter values in the request fields
 Given User is on Put method with end point url
 When User sends the request with empty fields
 Then website should display message stating Required fields missing values