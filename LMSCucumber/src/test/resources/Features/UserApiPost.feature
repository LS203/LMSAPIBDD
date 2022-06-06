Feature: Test users API Put method

  Scenario: User is trying to modify details in request field with valid Jason schema
    Given user is on the Endpoint/url/Users/put request
    When user modify phone_number in request field
    Then user recieves sucess message

  Scenario: User is trying to modify details in request field with valid Jason schema
    Given user is on the Endpoint/url/Users/put request
    When user modify location in request field
    Then user should get the message_response as Successfully Created

  Scenario: User is trying to modify details in request field with valid Jason schema
    Given user is on the Endpoint/url/Users/put request
    When user modify linkedin_url in request field
    Then user should recieve message_response as SuccessfullyCreated

  Scenario: User is trying to modify details in request field with valid Jason schema
    Given user is on the Endpoint/url/Users/put request
    When user modify education_pg in request field
    Then user sucessfully modifies details

  Scenario: User is trying to modify details in request field with valid Jason schema
    Given user is on the Endpoint/url/Users/put request
    When user modify visa_status in request field
    Then user gets the message_response as SuccessfullyCreated

  Scenario: User is trying to modify details in request field with valid Jason schema
    Given user is on the Endpoint/url/Users/put request
    When user modify does not provide modified info in request field
    Then user should get the error message_response as Failed to Create