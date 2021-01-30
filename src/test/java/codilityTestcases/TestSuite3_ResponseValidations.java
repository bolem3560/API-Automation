package codilityTestcases;

import static io.restassured.RestAssured.given;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.*;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import apiAutomation.functionalcomponents.ReadJSON;
import apiAutomation.functionalcomponents.ReportsGeneration;
import apiAutomation.functionalcomponents.ResponseValidations;
import apiAutomation.functionalcomponents.TestData;

import io.restassured.response.Response;

public class TestSuite3_ResponseValidations {

	static ReadJSON testdata = new ReadJSON();
	ResponseValidations responseValidations = new ResponseValidations();
	public ReadJSON testdatafile = testdata;
	public Response responseobject;
	public static ExtentTest test;

	/**
	 * this test to validate the Response validation for any micro service.
	 * 
	 * @param data
	 */
	@Test(dataProvider = "getdata")
	public void responseValidations(TestData data) {
		ReportsGeneration.test = ReportsGeneration.Extent.startTest(data.getTestCase());
		try {
			if (data.getrequestType().equals("GET")) {
				given().when().get(data.getendpointurl()).then().log().all().statusCode(data.getstatuscode())
						.body("data.first_name", equalTo(data.getfirst_name())).and()
						.body("data.last_name", equalTo(data.getlast_name())).and()
						.body("data.email", equalTo("janet.weaver@reqres.in")).and().body("support.text",
								equalTo("To keep ReqRes free, contributions towards server costs are appreciated!"));
				ReportsGeneration.test.log(LogStatus.PASS, "GET Response validation successful");
			} else if (data.getrequestType().equals("POST")) {
				given().body("").when().post(data.getendpointurl()).then().log().all().statusCode(data.getstatuscode())
						.body("$", not(hasKey(" ")));
				ReportsGeneration.test.log(LogStatus.PASS, "POST Response validation successful");
			}
		} catch (Exception e) {
			ReportsGeneration.test.log(LogStatus.FAIL, "Response validation NOT successful");
		}
	}

	@DataProvider(name = "getdata")
	public static Object[][] getdata()
			throws JsonIOException, JsonSyntaxException, FileNotFoundException, UnsupportedEncodingException {
		return testdata.jsonread("responsevalidations");

	}

	@AfterMethod
	public void getResult(ITestResult result) {

		ReportsGeneration.Extent.endTest(test);
		ReportsGeneration.Extent.flush();

	}

}
