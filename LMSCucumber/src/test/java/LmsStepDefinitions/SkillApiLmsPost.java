package LmsStepDefinitions;

import static io.restassured.RestAssured.given;
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

public class SkillApiLmsPost {
	
	RequestSpecification Req_spec=null;
	Response resp=null;
	String baseUrl = Lmsconfig.baseURI+"/Skills";
	Map<String, String> currentDataRow;
	String excelpath = "src/test/resources/TestData/SkillApidata.xlsx";
	
	@Given("User is in the postrequest with endpoint url")
	public void user_is_in_the_postrequest_with_endpoint_url() {
		RestAssured.baseURI = baseUrl;
		Req_spec = RestAssured.given().auth().preemptive().basic(Lmsconfig.userId,Lmsconfig.password);
        Req_spec.get(baseUrl);
		Req_spec.header("content-type","application/json");

	    
	}

	@When("User sends postrequest gives input Skill details from excel {int}")
	public void user_sends_postrequest_gives_input_skill_details_from_excel(Integer rownum) {
	
		
		RestAssured.given().auth().preemptive().basic(Lmsconfig.userId,Lmsconfig.password);
		ExcelReader reader=new ExcelReader();
		List<Map<String, String>> testdata;
		  JsonObject requestParams=new JsonObject();
		  
			try {
				testdata = reader.getData("src/test/resources/TestData/SkillApidata.xlsx","Sheet1");
				
				currentDataRow = testdata.get(rownum);
				currentDataRow.forEach((k,v) -> {
						requestParams.put(k,v);
					});
				Req_spec.given().body(requestParams.toJson());
		        resp = Req_spec.when().post();
			}catch (InvalidFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}

	@Then("User recieves message reponse as sucessfully created with skill id and updates in excel {int}")
	public void user_recieves_message_reponse_as_sucessfully_created_with_skill_id_and_updates_in_excel(Integer rowNumber) throws InvalidFormatException, IOException {
	    
		
		System.out.println(resp.getBody().asPrettyString());
		String responsebody=resp.getBody().asPrettyString();
				resp.then().assertThat().statusCode(201);
				JsonPath eval =  resp.jsonPath();
			    Assert.assertEquals(currentDataRow.get("skill_name"),eval.get("skill_name"));
			    String cellvalue = eval.getString("skill_id");
			    new ExcelReader().UpdateRow(excelpath, "Sheet1", rowNumber+1,
						1, cellvalue);
			    assertThat("Json Schema",responsebody,matchesJsonSchemaInClasspath("SkillApischema.json"));
		
	}


	@Given("User is on the PostRequest with incorrect endpoint")
	public void user_is_on_the_post_request_with_incorrect_endpoint() {
		Req_spec = given().auth().basic(Lmsconfig.userId,Lmsconfig.password);
	}

	@When("User sends the PostRequest giving invalid endpoint")
	public void user_sends_the_post_request_giving_invalid_endpoint() {
		resp=  Req_spec.when().post(Lmsconfig.baseURI+"/sills");
	}

	@Then("Error message is shown")
	public void error_message_is_shown() {
		
		resp.then().assertThat().statusCode(404);
	    int statuscode=resp.getStatusCode();
	    System.out.println(resp.getBody());
	    System.out.println(statuscode);
	   
	}

}
