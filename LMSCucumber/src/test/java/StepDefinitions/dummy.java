package StepDefinitions;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;

import com.github.cliftonlabs.json_simple.JsonObject;

import LmsStepDefinitions.ExcelReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static io.restassured.module.jsv.JsonSchemaValidator.*;

import static org.hamcrest.MatcherAssert.assertThat;
public class dummy {
	

	RequestSpecification Req_spec=null;
	Response resp=null;
	String excelpath = "src/test/resources/TestData/LmsUserTestdata.xlsx";
	
	String baseUrl = Lmsconfig.baseURI+"/Users";
	Map<String, String> currentDataRow;
	
	@Given("user is in post method with endpoint url")
	public void user_is_in_post_method_with_endpoint_url() {
		
		RestAssured.baseURI = baseUrl;
		Req_spec = RestAssured.given().auth().preemptive().basic(Lmsconfig.userId,Lmsconfig.password);
		Req_spec.get(baseUrl);
		Req_spec.header("content-type","application/json");
		
	    
	}

	@When("User sends post request giving details from sheet {int}")
	public void user_sends_post_request_giving_details_from_sheet(Integer rownumber) {
	    
		RestAssured.given().auth().preemptive().basic(Lmsconfig.userId,Lmsconfig.password);
		ExcelReader reader=new ExcelReader();
	  List<Map<String, String>> testdata;
	  JsonObject requestParams=new JsonObject();
	  
	try {
		testdata = reader.getData("src/test/resources/TestData/LmsUserTestdata.xlsx","Sheet1");
		
		currentDataRow = testdata.get(rownumber);
		currentDataRow.forEach((k,v) -> {
			if(k != "user_id")
				requestParams.put(k,v);
			});
	
			Req_spec.given().body(requestParams.toJson());
	        resp = Req_spec.when().post();
	        System.out.println(resp.getBody().asPrettyString());
       }catch(Exception ex)
		{
    	   ex.printStackTrace();
		}
	
		
		
	}

	@Then("shows a message sucessfully created updates created user id in excel {int}")
	public void shows_a_message_sucessfully_created_updates_created_user_id_in_excel(Integer rowNumber) throws InvalidFormatException, IOException  {
	    
		System.out.println(resp.getBody().asPrettyString());
    String responsebody=resp.getBody().asPrettyString();
		resp.then().assertThat().statusCode(201);
	    
		int statusCode=resp.getStatusCode();
	    if(statusCode == 200 || statusCode == 201)
	    {
		    JsonPath eval =  resp.jsonPath();
		       
	        Assert.assertEquals(currentDataRow.get("name"), eval.get("name"));
	        Assert.assertEquals(currentDataRow.get("visa_status"), eval.get("visa_status"));
	        Assert.assertEquals(currentDataRow.get("location"), eval.get("location"));
	        Assert.assertEquals(currentDataRow.get("time_zone"),eval.get("time_zone"));
	        Assert.assertEquals(currentDataRow.get("linkedin_url"),eval.get("linkedin_url"));
	        Assert.assertEquals(currentDataRow.get("education_ug"), eval.get("education_ug"));
	        Assert.assertEquals(currentDataRow.get("education_pg"), eval.get("education_pg"));
	        Assert.assertEquals(currentDataRow.get("comments"), eval.get("comments"));
	        
	        
	        String cellvalue = eval.getString("user_id");
	       
	      // rowNum=rowNum+1;
			new ExcelReader().UpdateRow(excelpath, "Sheet1", rowNumber+1,
					9, cellvalue);
			  //schema validation
		 	assertThat("Json Schema",responsebody,matchesJsonSchemaInClasspath("LmsSchema.json"));
			
			// assertThat Schema",Responsebody.replaceAll("NaN","null"),matchesJsonSchemaInClasspath("JsonSchemaJobsApi.json"));
		 	  System.out.println("schema validated");
		 	
			
	}
	}

	
		
		
	}
	
	
	
	


