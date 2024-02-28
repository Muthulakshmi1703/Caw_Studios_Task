package caw.test.logics;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import caw.base.utils.ArrayJsonData;
import caw.base.utils.BasePage;
import caw.base.utils.MyDataObject;
import caw.base.utils.SeleniumBaseUtils;
import caw.test.locators.DynamicTablePageLocators;

public class DynamicTablePage extends SeleniumBaseUtils{
	public static DynamicTablePageLocators loc = new DynamicTablePageLocators();
	
	/**
	 * This Function is to Verify Dynamic Table Test page is launched
	 * 
	 * @author Muthulakshmi 
	 * Created Date:27/02/2024
	 * 
	 * Modified By: 
	 * Modified Date:
	 * 
	 * @throws Exception 
	 */
	public boolean verifyDynamicTableTestPageLaunched(String url) throws Exception {
	try {
		String currentUrl = getCurrentUrl();
		if(currentUrl.equals(url)) {
			reportStatus("PASS", "Dynamic Table Test page launched successfully");
		}else {
			reportStatus("FAIL", "Dynamic Table Test page not launched");
		}
	}catch(Exception e) {
		reportStatus("FAIL", e.getMessage());
	}
	return true;	
	}

	/**
	 * This Function is to Click on Table Data 
	 * 
	 * @author Muthulakshmi 
	 * Created Date:27/02/2024
	 * 
	 * Modified By: 
	 * Modified Date:
	 * 
	 * @throws Exception 
	 */
	public boolean clickOnTableData() throws Exception {
	try {
		click(findElementBy(loc.tableDataBtn), 15);
		reportStatus("PASS", "Table Data clicked");
	}catch(Exception e) {
		reportStatus("FAIL", e.getMessage());
	}	
	return true;
	}
	
	/**
	 * This Function is to Enter Test Data in textarea field
	 * 
	 * @author Muthulakshmi 
	 * Created Date:27/02/2024
	 * 
	 * Modified By: 
	 * Modified Date:
	 * 
	 * @throws Exception 
	 */
	public boolean enterTestDataInTextareaField(String testData) throws Exception {
	try {
		clearText(findElementBy(loc.inputDataTextbox));
		dataSend(findElementBy(loc.inputDataTextbox), testData);
		reportStatus("PASS", "Test Data entered into the Textarea");
	}catch(Exception e) {
		reportStatus("FAIL", e.getMessage());
	}	
	return true;
	}
	
	/*
	 * This Function is to Click on Refresh Table 
	 * 
	 * @author Muthulakshmi 
	 * Created Date:27/02/2024
	 * 
	 * Modified By: 
	 * Modified Date:
	 * 
	 * @throws Exception 
	 */
	public boolean clickOnRefreshTable() throws Exception {
	try {
		jsClick(findElementBy(loc.refreshTable));
		List<WebElement> rows = findElementsBy(loc.tableRow);
		if(rows.size()>3) {
			reportStatus("PASS", "Refresh Table clicked");
		}else {
			jsClick(findElementBy(loc.refreshTable));
			reportStatus("PASS", "Refresh Table clicked");
		}
	}
	catch(Exception e) {
		reportStatus("FAIL", e.getMessage());
	}	
	return true;
	}
	
	/*
	 * This Function is to Click on Refresh Table 
	 * 
	 * @author Muthulakshmi 
	 * Created Date:27/02/2024
	 * 
	 * Modified By: 
	 * Modified Date:
	 * 
	 * @throws Exception 
	 */
	public boolean verifyEnteredTestDataAddedInTheTable() throws Exception {
	try {		
		String actualName = "";
		String age = "";
		String actualGender = "";
		String expectedName = "";
		int expectedAge = 0;
		String expectedGender = "";
		int actualAge = 0;
		
		List<WebElement> rows = findElementsBy(loc.tableRow);
		ArrayJsonData[] data1 = BasePage.getArrayTestData();

		for (int i=1; i<=rows.size()-1;i++) {
			String actualData = rows.get(i).getText();
			String[] arrayData = actualData.split(" ");
			
			actualName = arrayData[0];
			age = arrayData[1];
			actualGender = arrayData[2];
			
			actualAge = Integer.parseInt(age);
			for (int j = 0; j < i; j++) {
				expectedName = data1[j].getName();
				expectedAge = data1[j].getAge();
				expectedGender = data1[j].getGender();
			}
			
			Assert.assertEquals(actualName, expectedName);
			Assert.assertEquals(actualAge, expectedAge);
			Assert.assertEquals(actualGender, expectedGender);
		}
		
		reportStatus("PASS", "Newly Entered testdata added into the Dynamic WebTable");
	}catch(Exception e) {
		reportStatus("FAIL", e.getMessage());
	}	
	return true;
	}
}
