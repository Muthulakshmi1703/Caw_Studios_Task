package caw.test.locators;

import org.openqa.selenium.By;

public class DynamicTablePageLocators {

	public static By tableDataBtn = By.xpath("//*[text()='Table Data']");
	public static By inputDataTextbox = By.xpath("//*[@id='jsondata']");
	public static By refreshTable = By.xpath("//*[@onclick='createTable()']");
	public static By tableRow = By.tagName("tr");
}
