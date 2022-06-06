package StepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class UserApiGetrequest {
	@Given("user set get method with Endpoint\\/url\\/Users")
	public void user_set_get_method_with_endpoint_url_users() {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new io.cucumber.java.PendingException();
	}

	@When("user sends request")
	public void user_sends_request() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("Json Schema is valid and User receices status code {int} and should gets list of  of all users")
	public void json_schema_is_valid_and_user_receices_status_code_and_should_gets_list_of_of_all_users(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Given("user set get method with Endpoint\\/url\\/Users\\/Id")
	public void user_set_get_method_with_endpoint_url_users_id() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("user sends request for specific User ID")
	public void user_sends_request_for_specific_user_id() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("Json Schema is valid and User receices status code {int} and should gets the details of specific user Id")
	public void json_schema_is_valid_and_user_receices_status_code_and_should_gets_the_details_of_specific_user_id(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("user sends request with invalid user Id")
	public void user_sends_request_with_invalid_user_id() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("user recieves status code {int} and should not gets the details of specific user Id")
	public void user_recieves_status_code_and_should_not_gets_the_details_of_specific_user_id(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("user sends request with blank user Id")
	public void user_sends_request_with_blank_user_id() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("user sends request with Negetive numbers user Id")
	public void user_sends_request_with_negetive_numbers_user_id() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("user sends request with desimal user Id")
	public void user_sends_request_with_desimal_user_id() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

}
