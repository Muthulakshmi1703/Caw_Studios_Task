package caw.test.execution;

import org.testng.Assert;
import org.testng.annotations.Test;

import caw.base.utils.BasePage;
import caw.test.logics.DynamicTablePage;

public class DynamicTablePageTest extends BasePage{
	
	public static DynamicTablePage dynamicTablePage = new DynamicTablePage();

	
	/**
	 * Verify User able Search data
	 * 
	 * @author: Muthulakshmi
	 * Created Date: 27/02/2024
	 * 
	 * Modified By: <Name>
	 * Modified Date: <Date>
	 * 
	 * @throws Exception
	 */
	@Test(groups = { "Regression", "DynamicTableTestPage" },
			description = "Verify User able Search data", 
			testName = "Dynamic_Table_Page_01")
	public void verifyUserAbleToSearchData() throws Exception {
			
		String url = getPropData("TEST_URL");
		String testData = getTestData();
		
		createTestName("Dynamic_Table_Page_01");
				
		launchTestUrl();
		waitForLoader();
		Assert.assertTrue(dynamicTablePage.verifyDynamicTableTestPageLaunched(url));
		Assert.assertTrue(dynamicTablePage.clickOnTableData());
		Assert.assertTrue(dynamicTablePage.enterTestDataInTextareaField(testData));
		Assert.assertTrue(dynamicTablePage.clickOnRefreshTable());
		Assert.assertTrue(dynamicTablePage.verifyEnteredTestDataAddedInTheTable());
	}

}
