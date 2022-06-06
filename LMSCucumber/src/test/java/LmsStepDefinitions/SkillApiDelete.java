package LmsStepDefinitions;

import static io.restassured.RestAssured.given;

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

public class SkillApiDelete {
	RequestSpecification Req_spec=null;
	Response resp=null;
	String baseUrl = Lmsconfig.baseURI+"/Skills";
	Map<String, String> currentDataRow;
	
	@Given("User is on Delete method endpoint url Skills\\/Id")
	public void user_is_on_delete_method_endpoint_url_skills_id() {
		 RestAssured.baseURI = baseUrl;
			Req_spec = RestAssured.given().auth().preemptive().basic(Lmsconfig.userId,Lmsconfig.password);
			Req_spec.get(baseUrl);
			Req_spec.header("content-type","application/json");
			RequestSpecification httpRequest=RestAssured.given().auth().preemptive().basic(Lmsconfig.userId,Lmsconfig.password);
			httpRequest.header("content-type","application/json");
	   
	}
	@When("user is on delete request with valid skill Id given from excel {int}")
	public void user_is_on_delete_request_with_valid_skill_id_given_from_excel(Integer rownum) throws IOException {
		
		
		ExcelReader reader = new ExcelReader();
		JsonObject requestParams = new JsonObject();
		try {
		List<Map<String,String>>  testData = reader.getData("src/test/resources/TestData/SkillApidata.xlsx", "Sheet3"); 
		
		Map<String,String> currentRow = testData.get(rownum);
		String skillid = currentRow.get("skill_id");
		
		String url = baseUrl+"/"+skillid;
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
	@Then("should display message  Record successfully deleted")
	public void should_display_message_record_successfully_deleted() {
		System.out.println(resp.getBody().asPrettyString());
		System.out.println(resp.getStatusCode());

		
		
	}

@Given("User is on the GetRequest  by giving incorrect url")
	public void user_is_on_the_get_request_by_giving_incorrect_url() {
	Req_spec = given().auth().basic(Lmsconfig.userId,Lmsconfig.password);
	    
	}

	@When("User sends the getMethod with invalid url")
	public void user_sends_the_get_method_with_invalid_url() {
		resp=  Req_spec.when().post(Lmsconfig.baseURI+"/sills");
	    
	}

	@Then("Errormessage is displayed")
	public void errormessage_is_displayed() {
		resp.then().assertThat().statusCode(404);
	    int statuscode=resp.getStatusCode();
	    System.out.println(resp.getBody());
	    System.out.println(statuscode);
	    
	}
}
