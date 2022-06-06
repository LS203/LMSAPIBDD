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

public class SkillApiLmsPut {
	
	RequestSpecification Req_spec=null;
	Response resp=null;
	String baseUrl = Lmsconfig.baseURI+"/Skills";
	Map<String, String> currentDataRow;
	
	@Given("user sends the Put request with endpoint url Skills")
	public void user_sends_the_put_request_with_endpoint_url_skills() {
		RestAssured.baseURI = baseUrl;
		Req_spec = RestAssured.given().auth().preemptive().basic(Lmsconfig.userId,Lmsconfig.password);
		Req_spec.get(baseUrl);
		Req_spec.header("content-type","application/json");
		RequestSpecification httpRequest=RestAssured.given().auth().preemptive().basic(Lmsconfig.userId,Lmsconfig.password);
		httpRequest.header("content-type","application/json");

		
		
	}

	@When("user is on  the put request giving  Skill Id value form excel {int}")
	public void user_is_on_the_put_request_giving_skill_id_value_form_excel(Integer rownum) throws IOException {
		ExcelReader reader = new ExcelReader();
		JsonObject requestParams = new JsonObject();
		try {
		List<Map<String,String>>  testData = reader.getData("src/test/resources/TestData/SkillApidata.xlsx", "Sheet2"); 
		
		Map<String,String> currentRow = testData.get(rownum);
		String skillid = currentRow.get("skill_id");
		
		String url = baseUrl+"/"+skillid;
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
	@Then("should display message as successfully updated")
	public void should_display_message_as_successfully_updated() {
		System.out.println(resp.getBody().asPrettyString());
 String responsebody=resp.getBody().asPrettyString();
		resp.then().assertThat().statusCode(201);
		JsonPath eval =  resp.jsonPath();
		Assert.assertEquals(currentDataRow.get("skill_id"), eval.get("skill_id").toString());
		Assert.assertEquals(currentDataRow.get("skill_name"), eval.get("skill_name"));
		 assertThat("Json Schema",responsebody,matchesJsonSchemaInClasspath("SkillApischema.json"));
		
	}
	@Given("User is on the PutRequest  by giving invalid url")
	public void user_is_on_the_put_request_by_giving_invalid_url() {
		RestAssured.baseURI = baseUrl;
		Req_spec = RestAssured.given().auth().preemptive().basic(Lmsconfig.userId,Lmsconfig.password);
		Req_spec.get(baseUrl);
	    
	}
@When("User sends the put Request giving incorrect url")
public void user_sends_the_put_request_giving_incorrect_url() {
    
	resp=  Req_spec.when().get(Lmsconfig.baseURI+"/SKills");
	
	
}
@Then("Errormessage with status code is shown")
public void errormessage_with_status_code_is_shown() {
	
	resp.then().assertThat().statusCode(404);
    int statuscode=resp.getStatusCode();
   
}


}
