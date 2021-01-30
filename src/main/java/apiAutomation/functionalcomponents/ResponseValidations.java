package apiAutomation.functionalcomponents;

import com.relevantcodes.extentreports.LogStatus;

public class ResponseValidations {
  
	/**
	 * this method to validate the status code of any service
	 * @param actual
	 * @param expected
	 */
	public void statuscodevalidation(int actual, int expected) {

		if (actual == expected) {
			ReportsGeneration.test.log(LogStatus.PASS, "Response : " + actual);
		} else {
			ReportsGeneration.test.log(LogStatus.FAIL,
					"Response : " + "Expected:" + expected + "\n\n" + "Actual:" + actual);
		}
	}

}
