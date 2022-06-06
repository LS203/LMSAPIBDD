Feature: Test users API Put method

  Scenario: User is trying to modify details in request field with valid Jason schema
    Given user is on the Endpoint/url/Users/put request
    When user modify phone_number in request field
    Then user should get the message_response as SuccessfullyCreated

  Scenario: User is trying to modify details in request field with valid Jason schema
    Given user is on the Endpoint/url/Users/put request
    When user modify location in request field
    Then user should get message_response SuccessfullyCreated

  Scenario: User is trying to modify details in request field with valid Jason schema
    Given user is on the Endpoint/url/Users/put request
    When user modify linkedin_url in request field
    Then user should get the message_response as SuccessfullyCreated

  Scenario: User is trying to modify details in request field with valid Jason schema
    Given user is on the Endpoint/url/Users/put request
    When user modify education_pg in request field
    Then user should get message_response with message as sucess

  Scenario: User is trying to modify details in request field with valid Jason schema
    Given user is on the Endpoint/url/Users/put request
    When user modify visa_status in request field
    Then user should get the message_response with Successfull message

  Scenario: User is trying to modify details in request field with valid Jason schema
    Given user is on the Endpoint/url/Users/put request
    When user modify does not provide modified info in request field
    Then user should get the message_response with failed to create
