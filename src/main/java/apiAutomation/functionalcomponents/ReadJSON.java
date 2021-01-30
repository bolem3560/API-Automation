package apiAutomation.functionalcomponents;

import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

public class ReadJSON {

	/**
	 * this method for reading the data from Json file 
	 * @param data
	 * @return
	 * @throws JsonIOException
	 * @throws JsonSyntaxException
	 * @throws FileNotFoundException
	 * @throws UnsupportedEncodingException
	 */
	public Object[][] jsonread(String data) throws JsonIOException, JsonSyntaxException, FileNotFoundException, UnsupportedEncodingException {

		
		
		JsonElement jsonData = new JsonParser().parse(new InputStreamReader(this.getClass().getResourceAsStream("/testdata.json"), "UTF-8"));
		JsonElement dataSet = jsonData.getAsJsonObject().get(data);
		List<TestData> testData = new Gson().fromJson(dataSet, new TypeToken<List<TestData>>() {
		}.getType());
		Object[][] returnValue = new Object[testData.size()][1];
		int index = 0;
		for (Object[] each : returnValue) {
			each[0] = testData.get(index++);
		}
		return returnValue;

	}

}
