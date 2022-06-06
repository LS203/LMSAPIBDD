package LmsStepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import com.github.cliftonlabs.json_simple.JsonObject;

import StepDefinitions.Lmsconfig;
//import groovyjarjarantlr.collections.List;
public class UserApiLmsPost_Old {
	
	RequestSpecification Req_spec=null;
	Response resp=null;
	String baseUrl = Lmsconfig.baseURI+"/Users";
	
	@Given("User is on the Endpointurl with post method")
	public void user_is_on_the_endpointurl_with_post_method() {
		RestAssured.baseURI = baseUrl;
		Req_spec = RestAssured.given().auth().preemptive().basic(Lmsconfig.userId,Lmsconfig.password);
		Req_spec.get(baseUrl);
		Req_spec.header("content-type","application/json");
	}

	
	@When("user sends post request with endpoint inputs data from excel {int}")
	public void user_sends_post_request_with_endpoint_inputs_data_from_excel(Integer rownumber) {
		RestAssured.given().auth().preemptive().basic(Lmsconfig.userId,Lmsconfig.password);
		ExcelReader reader=new ExcelReader();
	  List<Map<String, String>> testdata;
	try {
		testdata = reader.getData("src/test/resources/TestData/LmsTestData.xlsx","Sheet1");
	
		String name=testdata.get(rownumber).get("Name");
		String phone_number=testdata.get(rownumber).get("Phone_Number");
		String location=testdata.get(rownumber).get("Location");
		String time_zone=testdata.get(rownumber).get("Time_Zone");
		String linkedin_url=testdata.get(rownumber).get("Linkedin_url");
		String education_ug=testdata.get(rownumber).get("education_ug");
		String education_pg=testdata.get(rownumber).get("education_pg");
		String visa_status=testdata.get(rownumber).get("Visa_Status");
		String comments=testdata.get(rownumber).get("Comments");
		
		JsonObject requestParams=new JsonObject();
		requestParams.put("name", name);
		requestParams.put("phone_number",phone_number);
		requestParams.put("location", location);
		requestParams.put("time_zone", time_zone);
		requestParams.put("linkedin_url", linkedin_url);
		requestParams.put("education_ug", education_ug);
		requestParams.put("education_pg", education_pg);
		requestParams.put("visa_status", visa_status);
		requestParams.put("comments", comments);
		
		Req_spec.given().body(requestParams.toJson());
        resp = Req_spec.when().post();
        
       // int statusCode = resp.getStatusCode();
        
        //Assert.assertEquals(statusCode, 201);
		
        	System.out.println(resp.asPrettyString());
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
		
		@Then("shows sucessfully created message")
		public void shows_sucessfully_created_message() {
			resp.then().assertThat().statusCode(201);
		    resp.getBody();
		    int statuscode=resp.getStatusCode();
		    System.out.println(resp.getBody());
		    System.out.println(statuscode);
		    
		}


@Given("User is on the postrequest  by giving invalid url")
	public void user_is_on_the_postrequest_by_giving_invalid_url() {
	  
	Req_spec = given().auth().basic(Lmsconfig.userId,Lmsconfig.password);
	}

@When("User sends the postMethod with incorrect url")
public void user_sends_the_post_method_with_incorrect_url() {
	resp=  Req_spec.when().post(Lmsconfig.baseURI+"/UserS");  
	
   
}

	@Then("Error message with statuscode is displayed")
	public void error_message_with_statuscode_is_displayed() {
	    
		resp.then().assertThat().statusCode(404);
	    int statuscode=resp.getStatusCode();
	    System.out.println(resp.getBody());
	    System.out.println(statuscode);
	    
	}

	
	
	
	
	
	
	

}
