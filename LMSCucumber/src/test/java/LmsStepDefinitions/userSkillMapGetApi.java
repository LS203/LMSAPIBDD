package LmsStepDefinitions;

import io.cucumber.java.en.Given;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.junit.Assert;

import com.github.cliftonlabs.json_simple.JsonObject;

import StepDefinitions.Lmsconfig;
public class userSkillMapGetApi {
	
	RequestSpecification Req_spec;
	Response resp;
	Map<String, String> currentDataRow;
	String baseUrl = Lmsconfig.baseURI+"/UserSkillsMap";
	
	
	
	@Given("User is on endpoint url UserSkillsMap")
	public void user_is_on_endpoint_url_user_skills_map() {
	   
		 Req_spec = given().auth().basic(Lmsconfig.userId,Lmsconfig.password);

		
	}

	@When("User is on Get method endpoint url UserSkillsMap")
	public void user_is_on_get_method_endpoint_url_user_skills_map() {
	 
		Req_spec = given().auth().basic(Lmsconfig.userId,Lmsconfig.password);
	     resp=  Req_spec.when().get(Lmsconfig.baseURI+"/UserSkillsMap");
		
	}

	@Then("List of all users with all Skill details are listed")
	public void list_of_all_users_with_all_skill_details_are_listed() throws IOException {
	  
		Req_spec = given().auth().basic(Lmsconfig.userId,Lmsconfig.password);
		resp.then().assertThat().statusCode(200);
		String responsebody=resp.getBody().asPrettyString();
		int statuscode=resp.getStatusCode();
		//System.out.println(responsebody);
		Assert.assertEquals(statuscode, 200);
		Assert.assertTrue(responsebody.contains("firstName"));
		Assert.assertTrue(responsebody.contains("lastName"));
		Assert.assertTrue(responsebody.contains("id"));
		Assert.assertTrue(responsebody.contains("skill"));
		
		/*FileWriter file = new FileWriter("Response.json");
	    file.write(responsebody);
	    file.close();*/
		assertThat("Json Schema",responsebody,matchesJsonSchemaInClasspath("UserSkillMapgetApi.json"));
		System.out.println("Validation Complete!!");
	}

	@Given("User is on endpoint url UserSkillsMap userId")
	public void user_is_on_endpoint_url_user_skills_map_user_id() {
		RestAssured.baseURI = baseUrl;
		Req_spec = given().auth().basic(Lmsconfig.userId,Lmsconfig.password);
		Req_spec.get(baseUrl);
		Req_spec.header("content-type","application/json");
		

	}

	@When("User is on Get request with giving specific Id from the excel {int}")
	public void user_is_on_get_request_with_giving_specific_id_from_the_excel(Integer rownum) {
		Req_spec = given().auth().basic(Lmsconfig.userId,Lmsconfig.password);
		ExcelReader reader=new ExcelReader();
		List<Map<String, String>> testdata;
		try {
				testdata = reader.getData("src/test/resources/TestData/GetId.xlsx","Sheet2");
				
				
				Map<String,String> currentRow = testdata.get(rownum);
				String uid = currentRow.get("id");
				
				String url = baseUrl+"/"+uid;
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

	@Then("User Skill details are shown")
	public void user_skill_details_are_shown() {
	    
		resp.then().assertThat().statusCode(200);
		String responsebody=resp.getBody().asPrettyString();
		 //System.out.println(resp.getBody());
			Assert.assertTrue(responsebody.contains("id"));
			Assert.assertTrue(responsebody.contains("firstName"));
			Assert.assertTrue(responsebody.contains("lastName"));
			Assert.assertTrue(responsebody.contains("skill"));




	}

	@Given("User is on endpoint url\\/UserSkillsMap\\/skillId")
	public void user_is_on_endpoint_url_user_skills_map_skill_id() {
		Req_spec = given().auth().basic(Lmsconfig.userId,Lmsconfig.password);
		
		
	}

	@When("User is on Get request with giving SkillId from the excel {int}")
	public void user_is_on_get_request_with_giving_skill_id_from_the_excel(Integer rownum) {
		Req_spec = given().auth().basic(Lmsconfig.userId,Lmsconfig.password);
	String skillurl=Lmsconfig.baseURI+"/UsersSkillsMap";
	ExcelReader reader=new ExcelReader();
	 List<Map<String, String>> testdata;
	  
		try {
			testdata = reader.getData("src/test/resources/TestData/GetId.xlsx","Sheet3");
			
			
			Map<String,String> currentRow = testdata.get(rownum);
			String skillid = currentRow.get("skill_id");
			
			String url = skillurl+"/"+skillid;
			Req_spec.baseUri(url);
			
			/*currentDataRow = testdata.get(rownum);
			currentDataRow.forEach((k,v) -> {
				
					requestParams.put(k,v);
				});
		
				Req_spec.given().body(requestParams.toJson());*/
		        resp = Req_spec.when().get(url);
		        //System.out.println(resp.getBody().asPrettyString());
	       }catch(Exception ex)
			{
	    	   ex.printStackTrace();
			}	  
	  
		
	}

	@Then("List of all User details by skillId is shown")
	public void list_of_all_user_details_by_skill_id_is_shown() {
	   
		resp.then().assertThat().statusCode(200);
		//String responsebody=resp.getBody().asPrettyString();
		 //System.out.println(resp.getBody());
		
	}

	@Given("User is on Get request")
	public void user_is_on_get_request() {
		Req_spec = given().auth().basic(Lmsconfig.userId,Lmsconfig.password);
	}

	@When("User sends get request with incorrect url")
	public void user_sends_get_request_with_incorrect_url() {
		resp=  Req_spec.when().get(Lmsconfig.baseURI+"/Userskillsmap");
	}

	@Then("Errormessage with status code {int} is shown")
	public void errormessage_with_status_code_is_shown(Integer int1) {
		resp.then().assertThat().statusCode(404);
	    int statuscode=resp.getStatusCode();
	    //System.out.println(resp.getBody());
	    System.out.println(statuscode);
		
		
	}


	
	
	
	
	
}
