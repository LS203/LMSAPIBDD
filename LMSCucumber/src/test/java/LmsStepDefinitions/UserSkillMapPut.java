package LmsStepDefinitions;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.Assert;

import com.github.cliftonlabs.json_simple.JsonObject;

import LmsStepDefinitions.ExcelReader;
import StepDefinitions.Lmsconfig;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class UserSkillMapPut {
	RequestSpecification Req_spec=null;
	Response resp=null;
	String baseUrl = Lmsconfig.baseURI+"/UserSkills";
	Map<String, String> currentDataRow;
	
	@Given("User is on Put Method with the end point url UserSkills")
	public void user_is_on_put_method_with_the_end_point_url_user_skills() {
		RestAssured.baseURI = baseUrl;
		Req_spec = RestAssured.given().auth().preemptive().basic(Lmsconfig.userId,Lmsconfig.password);
		Req_spec.get(baseUrl);
		Req_spec.header("content-type","application/json");
		RequestSpecification httpRequest=RestAssured.given().auth().preemptive().basic(Lmsconfig.userId,Lmsconfig.password);
		httpRequest.header("content-type","application/json");

	}

	@When("User sends the Put request to Update the record and giving Id from excel {int}")
	public void user_sends_the_put_request_to_update_the_record_and_giving_id_from_excel(Integer rownum) throws IOException {
	    
		ExcelReader reader = new ExcelReader();
		JsonObject requestParams = new JsonObject();
		try {
		List<Map<String,String>>  testData = reader.getData("src/test/resources/TestData/UserSkillMapApi.xlsx", "Sheet3"); 
		
		Map<String,String> currentRow = testData.get(rownum);
		String userskillid = currentRow.get("user_skill_id");
		
		String url = baseUrl+"/"+userskillid;
		Req_spec.baseUri(url);
		
		currentDataRow = testData.get(rownum);
		currentDataRow.forEach((k,v) -> {
				requestParams.put(k,v);
			});
			Req_spec.given().body(requestParams.toJson());
	        resp = Req_spec.when().put();
	        
	        String respon = resp.getBody().asPrettyString();
	        System.out.println(respon);
       } catch (InvalidFormatException e) {
		

   		Req_spec.given().body(requestParams.toJson());
           resp = Req_spec.when().put();

   		
   		System.out.println(resp.body().asPrettyString());
       }
		
	}
	@Then("Message response shown as record succesfully updated")
	public void message_response_shown_as_record_succesfully_updated() {
		System.out.println(resp.getBody().asPrettyString());
String responsebody=resp.getBody().asPrettyString();
		resp.then().assertThat().statusCode(201);
		
		JsonPath eval =  resp.jsonPath();
		Assert.assertEquals(currentDataRow.get("user_skill_id"), eval.get("user_skill_id"));
		Assert.assertEquals(currentDataRow.get("user_id"), eval.get("user_id"));
		Assert.assertEquals(currentDataRow.get("skill_id").toString(), eval.get("skill_id").toString());
		Assert.assertEquals(currentDataRow.get("months_of_exp").toString(), eval.get("months_of_exp").toString());

	    assertThat("Json Schema",responsebody,matchesJsonSchemaInClasspath("UserSkillMappost.json"));

		
		
	}

	@Given("User is on Put Method with the end point url")
	public void user_is_on_put_method_with_the_end_point_url() {
		RestAssured.baseURI = baseUrl;
		Req_spec = RestAssured.given().auth().preemptive().basic(Lmsconfig.userId,Lmsconfig.password);
		Req_spec.get(baseUrl);
		Req_spec.header("content-type","application/json");
		RequestSpecification httpRequest=RestAssured.given().auth().preemptive().basic(Lmsconfig.userId,Lmsconfig.password);
		httpRequest.header("content-type","application/json");

		
		
	}

@When("User sends the Put request to Update the details giving non existing Id from excel {int}")
public void user_sends_the_put_request_to_update_the_details_giving_non_existing_id_from_excel(Integer rownum) throws IOException {
    
	ExcelReader reader = new ExcelReader();
	JsonObject requestParams = new JsonObject();
	try {
	List<Map<String,String>>  testData = reader.getData("src/test/resources/TestData/UserSkillMapApi.xlsx", "Sheet4"); 
	
	Map<String,String> currentRow = testData.get(rownum);
	String userskillid = currentRow.get("user_skill_id");
	
	String url = baseUrl+"/"+userskillid;
	Req_spec.baseUri(url);
	
	currentDataRow = testData.get(rownum);
	currentDataRow.forEach((k,v) -> {
			requestParams.put(k,v);
		});
		Req_spec.given().body(requestParams.toJson());
        resp = Req_spec.when().put();
        
        String respon = resp.getBody().asPrettyString();
        System.out.println(respon);
   } catch (InvalidFormatException e) {
	

		Req_spec.given().body(requestParams.toJson());
       resp = Req_spec.when().put();

		
		System.out.println(resp.body().asPrettyString());
   }
	
}


@Then("No User found message is shown")
public void no_user_found_message_is_shown() {
	resp.then().assertThat().statusCode(404);
	
	
}



}
