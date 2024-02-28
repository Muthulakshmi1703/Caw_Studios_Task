package caw.base.utils;

import java.io.File;
import java.io.FileReader;
import java.time.Duration;
import java.util.Properties;

import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.databind.ObjectMapper;


public class BasePage extends SeleniumBaseUtils{

	/**
	 * This Function is to Open Browser and launch URL
	 * 
	 * @author Muthulakshmi 
	 * Created Date:27/02/2024
	 * 
	 * Modified By: 
	 * Modified Date:
	 * 
	 * @throws Exception 
	 */
	
	@BeforeMethod
	public static void testSetup() throws Exception {
	try {
		browserOpen("chrome");
		windowMaximize();
	}catch(Exception e) {
		reportStatus("FAIL", e.getMessage());
	}
	}
	
	public static void launchTestUrl() throws Exception {
	try {
		loadUrl("https://testpages.herokuapp.com/styled/tag/dynamic-table.html");
	}catch(Exception e) {
		reportStatus("FAIL", e.getMessage());
	}
	}
	
	/**
	 * This Function is Wait for Loader
	 * 
	 * @author Muthulakshmi 
	 * Created Date:27/02/2024
	 * 
	 * Modified By: 
	 * Modified Date:
	 * 
	 * @throws Exception 
	 */
	public static void waitForLoader() throws Exception {
	try {
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(15));
	}catch(Exception e) {
		reportStatus("FAIL", e.getMessage());
	}
	}
	
	/**
	 * This Function is to get the Test data from TestData.json file
	 * 
	 * @author Muthulakshmi 
	 * Created Date:27/02/2024
	 * 
	 * Modified By: 
	 * Modified Date:
	 * 
	 * @throws Exception 
	 */
	public static String getTestData() throws Exception {
		String testData = "";
	try {
		
		ObjectMapper objectMapper = new ObjectMapper();

            File file = new File("./src/test/resources/Data/TestData.json");

            MyDataObject[] dataArray = objectMapper.readValue(file, MyDataObject[].class);

            for (MyDataObject data : dataArray) {
                testData = testData+data;
            }
            testData = "["+testData.substring(0, testData.length()-1)+"]";
	}
	catch(Exception e) {
//		reportStatus("FAIL", e.getMessage());
		System.out.println(e.getLocalizedMessage());
	}
	return testData;
	}
	
	/**
	 * This Function is to get the Test data from TestData.json file
	 * 
	 * @author Muthulakshmi 
	 * Created Date:27/02/2024
	 * 
	 * Modified By: 
	 * Modified Date:
	 * 
	 * @throws Exception 
	 */
	public static ArrayJsonData[] getArrayTestData() throws Exception {
		ArrayJsonData[] data = {};
	try {
		
		ObjectMapper objectMapper = new ObjectMapper();

        File file = new File("./src/test/resources/Data/TestData.json");

        data = objectMapper.readValue(file, ArrayJsonData[].class);
//        for (ArrayJsonData td : data) {
//               System.out.println(td.getName() + " " + td.getAge()+" " + td.getGender());
//        }
        }
	catch(Exception e) {
		reportStatus("FAIL", e.getMessage());
	}
	return data;
	}
	
	/**
	 * This Function is to get the data from TestData.properties file
	 * 
	 * @author Muthulakshmi 
	 * Created Date:28/02/2024
	 * 
	 * Modified By: 
	 * Modified Date:
	 * 
	 * @throws Exception 
	 */
	public static String getPropData(String key) throws Exception {
	String value = null;
	try {
		FileReader read = new FileReader("./src/test/resources/Data/TestData.properties");
		
		Properties prop = new Properties();
		prop.load(read);
		value = prop.getProperty(key);
	}
	catch(Exception e) {
		reportStatus("FAIL", e.getMessage());
	}
	return value;
	}

}
