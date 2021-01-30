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

import apiAutomation.functionalcomponents.ReadJSON;
import apiAutomation.functionalcomponents.ReportsGeneration;
import apiAutomation.functionalcomponents.ResponseValidations;
import apiAutomation.functionalcomponents.TestData;
import io.restassured.response.Response;

public class TestSuite1_StatuscodeValidations {

	static ReadJSON testdata = new ReadJSON();
	ResponseValidations responseValidations = new ResponseValidations();
	public ReadJSON testdatafile = testdata;
	public Response responseobject;
	public static ExtentTest test;

	/**
	 * test case to validate the response codes of any restful service. no need to do any code change.
	 * @param data
	 */
	@Test(dataProvider = "getdata")
	public void statusCodeValidations(TestData data) {
		ReportsGeneration.test = ReportsGeneration.Extent.startTest(data.getTestCase());
		if (data.getrequestType().equals("GET")) {
			responseobject = given().when().get(data.getendpointurl()).then().extract().response();
			int actual = responseobject.statusCode();
			int expected = data.getstatuscode();
			responseValidations.statuscodevalidation(actual, expected);

		} else if (data.getrequestType().equals("POST")) {
			responseobject = given().body("").when().post(data.getendpointurl()).then().extract().response();
			int actual = responseobject.statusCode();
			int expected = data.getstatuscode();
			responseValidations.statuscodevalidation(actual, expected);
		}
	}

	@DataProvider(name = "getdata")
	public static Object[][] getdata()
			throws JsonIOException, JsonSyntaxException, FileNotFoundException, UnsupportedEncodingException {
		return testdata.jsonread("apiDATA");

	}

	@AfterMethod
	public void getResult(ITestResult result) {

		ReportsGeneration.Extent.endTest(test);
		ReportsGeneration.Extent.flush();

	}

}
