package runner;

import org.junit.runner.RunWith;
import org.testng.annotations.DataProvider;

import io.cucumber.junit.Cucumber;
//import io.cucumber.junit.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
@RunWith(Cucumber.class)

@CucumberOptions(
			features={"src/test/resources/LmsFeatures"},
           plugin={"pretty","html:target/MyReports/report.html"},
           monochrome=true,
         // tags="@LmsUserApiPost",
          glue="LmsStepDefinitions")

public class TestRunner extends io.cucumber.testng.AbstractTestNGCucumberTests{
	
        	   @Override
        	   @DataProvider(parallel=false)
        	   public Object[][]scenarios(){
        	   return super.scenarios();
        	   }
}
