package codilityTestcases;

import static io.restassured.RestAssured.given;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

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
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

public class TestSuite2_SchemaValidations {

	static ReadJSON testdata = new ReadJSON();
	ResponseValidations responseValidations = new ResponseValidations();
	public ReadJSON testdatafile = testdata;
	public Response responseobject;
	public static ExtentTest test;

	/**
	 * this test to validate the JSON response schema for any micro service.
	 * 
	 * @param data
	 */
	@Test(dataProvider = "getdata")
	public void schemavalidations(TestData data) {
		ReportsGeneration.test = ReportsGeneration.Extent.startTest(data.getTestCase());

		try {

			if (data.getrequestType().equals("GET")) {
				ValidatableResponse res = given().when().get(data.getendpointurl()).then().assertThat()
						.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("getAPIResponseschema.json"))
						.statusCode(200);
				ReportsGeneration.test.log(LogStatus.PASS, "GET Response Schema validation successful");
			} else if (data.getrequestType().equals("POST")) {
				given().body("").when().post(data.getendpointurl()).then().log().all().statusCode(data.getstatuscode())
						.assertThat()
						.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("postAPIResponseschema.json"));
				ReportsGeneration.test.log(LogStatus.PASS, "POST Response Schema validation successful");
			}

		} catch (Exception e) {
			ReportsGeneration.test.log(LogStatus.FAIL, " Response Schema validation NOT successful");
		}
	}

	@DataProvider(name = "getdata")
	public static Object[][] getdata()
			throws JsonIOException, JsonSyntaxException, FileNotFoundException, UnsupportedEncodingException {
		return testdata.jsonread("schemavalidations");

	}

	@AfterMethod
	public void getResult(ITestResult result) {

		ReportsGeneration.Extent.endTest(test);
		ReportsGeneration.Extent.flush();

	}

}
