package apiAutomation.functionalcomponents;

import java.io.File;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;


/**
 * this class for report generation
 * @author kbolem
 *
 */
public class ReportsGeneration {
	public static ExtentReports Extent;
	public static ExtentTest test;
	static {
		try {

			if (null == Extent) {
				Extent = new ExtentReports(System.getProperty("user.dir") + "/test-output/ApiAutomationReport.html",
						true);
				Extent.addSystemInfo("Hostname", "auotmtion").addSystemInfo("Testing", "APIAUTOMATION");
				Extent.loadConfig(new File(System.getProperty("user.dir") + "/Extentconfig.xml"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
