package LmsStepDefinitions;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import com.github.cliftonlabs.json_simple.JsonObject;

import LmsStepDefinitions.ExcelReader;
import StepDefinitions.Lmsconfig;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class UserSkillMapDeleteApi {
	
	
	RequestSpecification Req_spec=null;
	Response resp=null;
	String baseUrl = Lmsconfig.baseURI+"/UserSkills";
	Map<String, String> currentDataRow;
	
	@Given("User is on delete Method with the end point url UserSkills and giving Id")
	public void user_is_on_delete_method_with_the_end_point_url_user_skills_and_giving_id() {
		RestAssured.baseURI = baseUrl;
		Req_spec = RestAssured.given().auth().preemptive().basic(Lmsconfig.userId,Lmsconfig.password);
		Req_spec.get(baseUrl);
		Req_spec.header("content-type","application/json");
		RequestSpecification httpRequest=RestAssured.given().auth().preemptive().basic(Lmsconfig.userId,Lmsconfig.password);
		httpRequest.header("content-type","application/json");
	}

	@When("User sends Delete request giving value from excel {int}")
	public void user_sends_delete_request_giving_value_from_excel(Integer rownum) throws IOException {

		ExcelReader reader = new ExcelReader();
		JsonObject requestParams = new JsonObject();
		try {
		List<Map<String,String>>  testData = reader.getData("src/test/resources/TestData/UserSkillMapApi.xlsx", "Sheet5"); 
		
		Map<String,String> currentRow = testData.get(rownum);
		String userskillId = currentRow.get("user_skill_id");
		
		String url = baseUrl+"/"+userskillId;
		Req_spec.baseUri(url);
		
		currentDataRow = testData.get(rownum);
		currentDataRow.forEach((k,v) -> {
				requestParams.put(k,v);
			});
			Req_spec.given().body(requestParams.toJson());
	        resp = Req_spec.when().delete();
	        
	        String respon = resp.getBody().asPrettyString();
	        System.out.println(respon);
       } catch (InvalidFormatException e) {
		
		
		Req_spec.given().body(requestParams.toJson());
        resp = Req_spec.when().delete();

		
		System.out.println(resp.body().asPrettyString());
       }
	}

	@Then("Response message User Record is successfully deleted is shown")
	public void response_message_user_record_is_successfully_deleted_is_shown() {
	  
		System.out.println(resp.getBody().asPrettyString());
		System.out.println(resp.getStatusCode());

		
		
	}

	@Given("User is in delete Method with the endpoint url UserSkills and giving  non existing Id")
	public void user_is_in_delete_method_with_the_endpoint_url_user_skills_and_giving_non_existing_id() {
		 RestAssured.baseURI = baseUrl;
			Req_spec = RestAssured.given().auth().preemptive().basic(Lmsconfig.userId,Lmsconfig.password);
			Req_spec.get(baseUrl);
			Req_spec.header("content-type","application/json");
			RequestSpecification httpRequest=RestAssured.given().auth().preemptive().basic(Lmsconfig.userId,Lmsconfig.password);
			httpRequest.header("content-type","application/json");
	}

	@When("User sends the deleterequest giving non existing Id from sheet {int}")
	public void user_sends_the_deleterequest_giving_non_existing_id_from_sheet(Integer int1) {
		resp=  Req_spec.when().get(Lmsconfig.baseURI+"/UserSkills/J90");
		
		
	}

	@Then("User Not found is shown")
	public void user_not_found_is_shown() {
		resp.then().assertThat().statusCode(404);
	}

	

	
}

