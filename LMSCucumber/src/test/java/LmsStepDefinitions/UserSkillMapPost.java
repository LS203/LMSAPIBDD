package LmsStepDefinitions;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.Assert;

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

public class UserSkillMapPost {
	RequestSpecification Req_spec=null;
	Response resp=null;
	String baseUrl = Lmsconfig.baseURI+"/UserSkills";
	Map<String, String> currentDataRow;
	@Given("User is on Post Method with the end point url UserSkills")
	public void user_is_on_post_method_with_the_end_point_url_user_skills() {
		
		RestAssured.baseURI = baseUrl;
		Req_spec = RestAssured.given().auth().preemptive().basic(Lmsconfig.userId,Lmsconfig.password);
		Req_spec.get(baseUrl);
		Req_spec.header("content-type","application/json");
	}
	@When("User sends Postrequest giving the values for user_skill_id,user_id,Skill_id,months_of_exp from excel {int}")
	public void user_sends_postrequest_giving_the_values_for_user_skill_id_user_id_skill_id_months_of_exp_from_excel(Integer rownum) {
		RestAssured.given().auth().preemptive().basic(Lmsconfig.userId,Lmsconfig.password);
		ExcelReader reader=new ExcelReader();
	  List<Map<String, String>> testdata;
	  JsonObject requestParams=new JsonObject();
	  try {
			testdata = reader.getData("src/test/resources/TestData/UserSkillMapApi.xlsx","Sheet1");
			
			currentDataRow = testdata.get(rownum);
			currentDataRow.forEach((k,v) -> {
					requestParams.put(k,v);
				});
			Req_spec.given().body(requestParams.toJson());
	        resp = Req_spec.when().post();
       } catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	  }

   @Then("MessageResponse is Successfully created is shown")
	public void message_response_is_successfully_created_is_shown() {
		
	   System.out.println(resp.getBody().asPrettyString());
String responsebody=resp.getBody().asPrettyString();
		resp.then().assertThat().statusCode(201);
		JsonPath eval =  resp.jsonPath();
	    Assert.assertEquals(currentDataRow.get("user_id"),eval.get("user_id").toString());
	    Assert.assertEquals(currentDataRow.get("skill_id").toString(),eval.get("skill_id").toString());
	    Assert.assertEquals(currentDataRow.get("months_of_exp").toString(),eval.get("months_of_exp").toString());
	    assertThat("Json Schema",responsebody,matchesJsonSchemaInClasspath("UserSkillMappost.json"));
	  }
   
   
	@Given("User is on Post Method with end point url UserSkills")
	public void user_is_on_post_method_with_end_point_url_user_skills() {
		RestAssured.baseURI = baseUrl;
		Req_spec = RestAssured.given().auth().preemptive().basic(Lmsconfig.userId,Lmsconfig.password);
		Req_spec.get(baseUrl);
		Req_spec.header("content-type","application/json");
	   
	}

	@When("User is on Post request trying to create new User skill id by giving invalid id from excel {int}")
	public void user_is_on_post_request_trying_to_create_new_user_skill_id_by_giving_invalid_id_from_excel(Integer rownum) {
		RestAssured.given().auth().preemptive().basic(Lmsconfig.userId,Lmsconfig.password);
		ExcelReader reader=new ExcelReader();
	  List<Map<String, String>> testdata;
	  JsonObject requestParams=new JsonObject();
	  try {
			testdata = reader.getData("src/test/resources/TestData/UserSkillMapApi.xlsx","Sheet4");
			
			currentDataRow = testdata.get(rownum);
			currentDataRow.forEach((k,v) -> {
					requestParams.put(k,v);
				});
			Req_spec.given().body(requestParams.toJson());
	        resp = Req_spec.when().post();
       } catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

	@Then("Error message should be shown")
	public void error_message_should_be_shown() {
	    
		System.out.println(resp.getBody().asPrettyString());

		resp.then().assertThat().statusCode(404);
	    
	    
	}

	
	
	
	
	
	
	
	
	
	
}
