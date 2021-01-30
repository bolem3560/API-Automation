package apiAutomation.functionalcomponents;

public class TestData {
	private String endpointurl;
	private String requestType;
	private String testCase;
	private String ContentType;
	private int statuscode;
	private String first_name;
	private String last_name;

	public String getendpointurl() {
		return endpointurl;
	}

	public void setendpointurl(String endpointurl) {
		endpointurl = endpointurl;
	}

	public String getrequestType() {
		return requestType;
	}

	public void setrequestType(String requestType) {
		requestType = requestType;
	}

	public String getContentType() {
		return ContentType;
	}

	public void setContentType(String ContentType) {
		this.ContentType = ContentType;
	}

	public String getTestCase() {
		return testCase;
	}

	public void setTestCase(String testCase) {
		this.testCase = testCase;
	}

	public int getstatuscode() {
		return statuscode;
	}

	public void setstatuscode(int statuscode) {
		this.statuscode = statuscode;
	}

	public String getlast_name() {
		return last_name;
	}

	public void setlast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getfirst_name() {
		return first_name;
	}

	public void setfirst_name(String first_name) {
		this.first_name = first_name;
	}

	@Override
	public String toString() {
		return "TestData{" +

				", endpointurl='" + endpointurl + '\'' + ", requestType='" + requestType + '\'' + ",ContentType='"
				+ ContentType + '\'' + ",first_name='" + first_name + '\'' + ",last_name='" + last_name + '\''
				+ ", testCase='" + testCase + '\'' +

				'}';
	}
}