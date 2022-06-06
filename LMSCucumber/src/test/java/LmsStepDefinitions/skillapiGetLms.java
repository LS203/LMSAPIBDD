package LmsStepDefinitions;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.List;
import java.util.Map;

import org.junit.Assert;

import com.github.cliftonlabs.json_simple.JsonObject;

import LmsStepDefinitions.ExcelReader;
import StepDefinitions.Lmsconfig;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.MatcherAssert.assertThat;

public class skillapiGetLms {

	RequestSpecification Req_spec;
	Response resp=null;
	String baseUrl = Lmsconfig.baseURI+"/Skills";
	Map<String, String> currentDataRow;
	
	
	@Given("User is on getmethod with endpoint Skills")
	public void user_is_on_getmethod_with_endpoint_skills() {
	    
		Req_spec =RestAssured.given().auth().basic(Lmsconfig.userId,Lmsconfig.password);		
		Req_spec.get(Lmsconfig.baseURI+"/Skills");
		
		
	}
	
	@When("User sends get request")
	public void user_sends_get_request() {
		
		Req_spec = RestAssured.given().auth().basic(Lmsconfig.userId,Lmsconfig.password);
		resp= Req_spec.when().get(Lmsconfig.baseURI+"/Skills");
		
	   
	}

	@Then("Fetch all skill data")
	public void fetch_all_skill_data() {
	   
		Req_spec = RestAssured.given().auth().basic(Lmsconfig.userId,Lmsconfig.password);
		resp.then().assertThat().statusCode(200);
		String responsebody=resp.getBody().asPrettyString();
		int statuscode=resp.getStatusCode();
		System.out.println(responsebody);
		Assert.assertTrue(responsebody.contains("skill_id"));
		Assert.assertTrue(responsebody.contains("skill_name"));
		 assertThat("Json Schema",responsebody,matchesJsonSchemaInClasspath("SkillApiGet.json"));

		
	}

	@Given("User is on url with endpoints Skills and Id")
	public void user_is_on_url_with_endpoints_skills_and_id() {
		RestAssured.baseURI = baseUrl;
		Req_spec = RestAssured.given().auth().preemptive().basic(Lmsconfig.userId,Lmsconfig.password);
		Req_spec.get(baseUrl);
		Req_spec.header("content-type","application/json");
		
		}

	@When("User sends Get Method by giving particualr Id from excel {int}")
	public void user_sends_get_method_by_giving_particualr_id_from_excel(Integer RowNumber) {
		
		RestAssured.given().auth().preemptive().basic(Lmsconfig.userId,Lmsconfig.password);
		ExcelReader reader=new ExcelReader();
		 List<Map<String, String>> testdata;
		  JsonObject requestParams=new JsonObject();
		  
		try {
			testdata = reader.getData("src/test/resources/TestData/GetId.xlsx","Sheet3");
			
			
			Map<String,String> currentRow = testdata.get(RowNumber);
			String skillid = currentRow.get("skill_id");
			
			String url = baseUrl+"/"+skillid;
			Req_spec.baseUri(url);
			
			currentDataRow = testdata.get(RowNumber);
			currentDataRow.forEach((k,v) -> {
				
					requestParams.put(k,v);
				});
		
				Req_spec.given().body(requestParams.toJson());
		        resp = Req_spec.when().get(url);
		        System.out.println(resp.getBody().asPrettyString());
	       }catch(Exception ex)
			{
	    	   ex.printStackTrace();
			}
		
	    
	}

	@Then("should display Skill detials for that Id")
	public void should_display_skill_detials_for_that_id() {
		resp.then().assertThat().statusCode(200);
		String responsebody=resp.getBody().asPrettyString();
		 System.out.println(resp.getBody());
	}

	@Given("User is on url with endpoint Skills")
	public void user_is_on_url_with_endpoint_skills() {
		Req_spec = RestAssured.given().auth().basic(Lmsconfig.userId,Lmsconfig.password);
	}

	@When("User is on Get Method with invalid endpoint")
	public void user_is_on_get_method_with_invalid_endpoint() {
	    
		resp=Req_spec.when().get(Lmsconfig.baseURI+"/sskills");
		
	}

	@Then("should display error message")
	public void should_display_error_message() {
		
		resp.then().assertThat().statusCode(404);
		System.out.println(resp.getBody());
	   
	}
}
