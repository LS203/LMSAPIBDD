package LmsStepDefinitions;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.List;
import java.util.Map;

import org.junit.Assert;

import com.github.cliftonlabs.json_simple.JsonObject;

import LmsStepDefinitions.ExcelReader;
import LmsStepDefinitions.Xlutils;
import StepDefinitions.Lmsconfig;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class UserSkillMapGet {
	
	
	RequestSpecification Req_spec;
	Response resp;
	Map<String, String> currentDataRow;
	String baseUrl = Lmsconfig.baseURI+"/UserSkills";

	@Given("User is on GET Method to fetch all UserSkills details")
	public void user_is_on_get_method_to_fetch_all_user_skills_details() {
		 Req_spec = given().auth().basic(Lmsconfig.userId,Lmsconfig.password);
	}

	@When("User is on Get request with endpoint url UserSkills")
	public void user_is_on_get_request_with_endpoint_url_user_skills() {
	   
		Req_spec = given().auth().basic(Lmsconfig.userId,Lmsconfig.password);
	     resp=  Req_spec.when().get(Lmsconfig.baseURI+"/UserSkills");
	}

	@Then("User recieves Sucess status with all UserSkill data shown")
	public void user_recieves_sucess_status_with_all_user_skill_data_shown() {
		
		Req_spec = given().auth().basic(Lmsconfig.userId,Lmsconfig.password);
		resp.then().assertThat().statusCode(200);
		String responsebody=resp.getBody().asPrettyString();
		int statuscode=resp.getStatusCode();
		System.out.println(responsebody);
		Assert.assertEquals(statuscode, 200);
		Assert.assertTrue(responsebody.contains("user_skill_id"));
		Assert.assertTrue(responsebody.contains("user_id"));
		Assert.assertTrue(responsebody.contains("skill_id"));
		Assert.assertTrue(responsebody.contains("months_of_exp"));
	    
		 assertThat("Json Schema",responsebody,matchesJsonSchemaInClasspath("UserSkillMapget.json"));
	}

	@Given("User is on GETMethod with to fetch UserSkill data")
	public void user_is_on_get_method_with_to_fetch_user_skill_data() {
		RestAssured.baseURI = baseUrl;

		Req_spec = given().auth().basic(Lmsconfig.userId,Lmsconfig.password);
		Req_spec.get(baseUrl);
		Req_spec.header("content-type","application/json");
		
	}

	@When("User sends the request with endpoint by giving Id from excel {int}")
	public void user_sends_the_request_with_endpoint_by_giving_id_from_excel(Integer rownum) {
	  
		Req_spec = given().auth().basic(Lmsconfig.userId,Lmsconfig.password);
		ExcelReader reader=new ExcelReader();
		 List<Map<String, String>> testdata;
		  JsonObject requestParams=new JsonObject();
		  
		  
			try {
				testdata = reader.getData("src/test/resources/TestData/GetId.xlsx","Sheet4");
				
				
				Map<String,String> currentRow = testdata.get(rownum);
				String id = currentRow.get("user_skill_id");
				
				String url = baseUrl+"/"+id;
				Req_spec.baseUri(url);
				
				/*currentDataRow = testdata.get(rownum);
				currentDataRow.forEach((k,v) -> {
					
						requestParams.put(k,v);
					});
			
					Req_spec.given().body(requestParams.toJson());*/
			        resp = Req_spec.when().get(url);
			        System.out.println(resp.getBody().asPrettyString());
		       }catch(Exception ex)
				{
		    	   ex.printStackTrace();
				}
			
		
	}

	@Then("User Skilldetails of given Id is shown")
	public void user_skilldetails_of_given_id_is_shown() {
		resp.then().assertThat().statusCode(200);
		String responsebody=resp.getBody().asPrettyString();
		 System.out.println(resp.getBody());
	}

	@Given("User is on  the Getrequest  giving invalid url")
	public void user_is_on_the_getrequest_giving_invalid_url() {
		Req_spec = given().auth().basic(Lmsconfig.userId,Lmsconfig.password);
	}

	@When("User sends the get request with invalid url")
	public void user_sends_the_get_request_with_invalid_url() {
		resp=  Req_spec.when().get(Lmsconfig.baseURI+"/Userckills");
	}

	@Then("Error message will be shown")
	public void error_message_will_be_shown() {
		resp.then().assertThat().statusCode(404);
	    int statuscode=resp.getStatusCode();
	    System.out.println(resp.getBody());
	    System.out.println(statuscode);
	}

	
	
}
