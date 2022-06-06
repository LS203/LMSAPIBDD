Feature:Test users API Delete method
Scenario: user is trying to send delete request with valid Jasonschema
Given user is on the Endpoint url
When Delete an existing User details
Then user should get the message_response Successfullydeleted
 Scenario: User is trying to send delete request with valid Jasonschema
Given user is on Endpoint url
When User delete the request using invalid user_id
Then user should get the error message_response as failed