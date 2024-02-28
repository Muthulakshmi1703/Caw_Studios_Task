package caw.base.utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;


public class SeleniumBaseUtils {

	public static WebDriver driver;
	
	public static ExtentSparkReporter reporter;
	public static ExtentReports extent;
	public static ExtentTest test;
	
	@BeforeSuite
	public static void report() {
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyhhmmss");
		String text = sdf.format(d);
		
		reporter = new ExtentSparkReporter("./ExtentReports/Report/Caw_Studios"+text+".html");
		extent = new ExtentReports();
		extent.attachReporter(reporter);
	}
	
	public static void createTestName(String testName) {
		test = extent.createTest(testName);
	}
	
	public static void reportStatus(String status, String description) throws Exception {
		switch(status.toUpperCase()) {
		case "PASS":
			test.log(Status.PASS, description);
			break;
		case "INFO":
			test.log(Status.INFO, description);
			break;
		case "WARNING":
			test.log(Status.WARNING, description);
			break;
		case "PASS WITH SCREENSHOT":
			test.log(Status.PASS, description, MediaEntityBuilder.createScreenCaptureFromPath(addScreenshots())
										.build());
			break;
		case "FAIL":
			test.log(Status.FAIL, description, MediaEntityBuilder.createScreenCaptureFromPath(addScreenshots())
										.build());
			break;
		}
	}
	
	public static synchronized String addScreenshots() throws IOException {
		String ImagePath = "";
		try {
			Date d = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyhhmmss");
			String scrnshotId = sdf.format(d);

			String Path = System.getProperty("user.dir") + "/ExtentReports/Screenshots/";

			File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			String imgPath = Path + scrnshotId + ".png";

			File dest = new File(imgPath);
			FileUtils.copyFile(screenshot, dest);

			ImagePath = "../ExtentReports/Screenshots/" + scrnshotId + ".png";
		} catch (Exception e) {
			e.getMessage();
		}
		return ImagePath;
	}


	
	@AfterMethod
	public static void stopReport() {
		extent.flush();
		pageClose();
	}
	
	public static void browserOpen(String browser) {
		switch (browser) {
		case "chrome":
			driver = new ChromeDriver();
			break;
			
		case "firefox":
			driver = new FirefoxDriver();
			break;
		
		case "ie":
			driver = new InternetExplorerDriver();
			break;
			
		case "edge":
			driver = new EdgeDriver();
			break;
			
		default:
			System.out.println("This browser is not feasible");
			break;
		}

	}
	
	public static void browserLaunch(String browser, String url) {
		if(browser.equals("chrome"))
		{
			driver = new ChromeDriver();
		}
	else if(browser.equals("firefox")) {
			driver = new FirefoxDriver();
		}	
	else if(browser.equals("edge")){
			driver = new EdgeDriver();
		}
	else {
			System.out.println("This Browser NOT Available");
		}
		driver.manage().window().maximize();
		driver.get(url);
	}


	public static void loadUrl(String url) {
		driver.get(url);
	}
	
	public static void windowMaximize() {
		driver.manage().window().maximize();
	}
	public static WebElement findElementById(String id) {
		return driver.findElement(By.id(id));
	}
	
	public static void elementDisplayed(WebElement element, long seconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public static WebElement findElementBy(By by) {
		return driver.findElement(by);
	}
	
	public static List<WebElement> findElementsBy(By by) {
		return driver.findElements(by);
	}
	
	public static WebElement findElementAB(By abs) {
		return driver.findElement(abs);
	}
	
	public static void pageTitle() {
		String pageTitle = driver.getTitle();
		System.out.println(pageTitle);
	}

	public static void pageClose() {
		driver.close();
	}
	
	public static void sleepTime(int time) throws InterruptedException {
		Thread.sleep(time);
	}
	
	public static void dataSend(WebElement element, String data) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOf(element));
		element.sendKeys(data);
	}
	
	public static void clearText(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOf(element));
		element.clear();
	}
	
	public static void click(WebElement element, long seconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
		wait.until(ExpectedConditions.elementToBeClickable(element));
		element.click();
	}
	
	public static void clickAndHold(WebElement element) {
		new Actions(driver).clickAndHold(element).perform();
	}
	
	public static void actionClick(WebElement element) {
		new Actions(driver).moveToElement(element).click().perform();
	}
	
	public static void submitElement(WebElement element) {
		element.submit();
	}
	
	public static void dropDownSelectAValue(String value,WebElement element) {
		Select s = new Select(element);
		s.selectByValue(value);
	}
	
	public static void submit(WebElement element) {
		element.submit();
	}
	
	public static void dropDownAllOptions(WebElement element) {
		Select s = new Select(element);
		List<WebElement> options = s.getOptions();
		for (int i = 0; i < options.size(); i++) {
			String data = options.get(i).getText();
			System.out.println(data);
		}
				
	}
	
	public static String getAttribute(WebElement element, String attrValue) {
		String data = element.getAttribute(attrValue);
		return data;
	}
	
	public static String getCurrentUrl() {
		String currentUrl = driver.getCurrentUrl();
		return currentUrl;
	}
	
	public static void pageSource() {
		String pageSrc = driver.getPageSource();
		System.out.println(pageSrc);
	}
	
	public static void getText(WebElement element) {
		String data = element.getText();
		System.out.println(data);
	}
	
	public static void dragAndDrop(WebElement source,WebElement destination) {
		Actions ac = new Actions(driver);
		ac.dragAndDrop(source, destination).perform();
	}
	
	public static void rightClick(WebElement element) {
		Actions ac = new Actions(driver);
		ac.contextClick(element).perform();
	}
	
	public static void doubleClick(WebElement element) {
		Actions ac = new Actions(driver);
		ac.doubleClick(element).perform();
	}
	
	public static void back() {
		driver.navigate().back();
	}
	
	public static void forward() {
		driver.navigate().forward();
	}
	
	public static void refresh() {
		driver.navigate().refresh();
	}
	
	public static void navigateTo(String element) {
		driver.navigate().to(element);
	}
	
	public static void mouseHover(WebElement element) {
		Actions ac = new Actions(driver);
		ac.moveToElement(element).perform();
	}
	
	public static void simpleAlert() {
		driver.switchTo().alert().accept();
	}
	
	public static void confirmAlert(String option) {
		switch(option) {
		case "accept":
			driver.switchTo().alert().accept();
			break;
		
		case "dismiss":
			driver.switchTo().alert().dismiss();
			break;
		}	
		
	}
	
	public static void promptAlert(String data) {
		Alert element = driver.switchTo().alert();
		element.sendKeys(data);
		element.accept();
	}
	
	public static void frameIndex(int index) {
		driver.switchTo().frame(index);
	}
	
	public static void frameCount() {
		List<WebElement> frameCount = driver.findElements(By.tagName("iframe"));
		int frameList = frameCount.size();
		System.out.println("Number of frame present in the Webpage : "+frameList);
	}
	
	public static void scrnshot(File destiny) throws IOException {
		TakesScreenshot tk = (TakesScreenshot) driver;
		File srcFile = tk.getScreenshotAs(OutputType.FILE);
		//FileHandler.copy(srcFile, destiny);
		FileHandler.copy(srcFile, destiny);
	}
	
	public static void jsSetAttribute(WebElement element, String data) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('value', '"+data+"')", element);
	}
	
	public static void jsGetAttribute(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String data = (String) js.executeScript("return arguments[0].getAttribute('value')", element);
		System.out.println(data);
	}
	
	public static void jsClick(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.elementToBeClickable(element));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click()", element);
	}
	
	public static void jsHighLight(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('style','background:blue ;border: solid 10px green;');",element);
	}
	
	public static void scrollUpAndDown(int startVal, int endVal) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy('"+startVal+"','"+endVal+"')");
		
	}
	
	public static void scrollIntoView(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);",element);
	}
	
	public static String getWindowId() {
		String windowId = driver.getWindowHandle();
		return windowId;
	}
	
	public static void getWindowIds() {
		Set<String> windowHandles = driver.getWindowHandles();
		for (String childId : windowHandles) {
			System.out.println("Child Window ID : "+childId);
		}
	}
	
	public static void tableData() {
		List<WebElement> data = driver.findElements(By.tagName("td"));
		for (int i = 0; i < data.size(); i++) {
			String tableDatas = data.get(i).getText();
			System.out.println(tableDatas);
		}
	}
	
	public static void tableRowCount() {
		int count = 0;
		List<WebElement> data = driver.findElements(By.tagName("tr"));
		for (int i = 0; i < data.size(); i++) {
			count = count+1;
		}
		System.out.println("Number of Rows in Table : "+count);
	}
	
	public static void getCssValue(WebElement element) {
		String fontSize = element.getCssValue("font-size");
		String fontColor = element.getCssValue("color");
		String fontType = element.getCssValue("font-family");
		String lineHeight = element.getCssValue("line-height");
		
		System.out.println("Font Size : "+fontSize);
		System.out.println("Font Colour : "+fontColor);
		System.out.println("Font Type : "+fontType);
		System.out.println("Line Height : "+lineHeight);
	}
	
		public static void getDropDownOptions(WebElement element) {
			Select s = new Select(element);
			List<WebElement> options = s.getOptions();
			for (int i = 0; i < options.size(); i++) {
				System.out.println(options.get(i).getText());
		}
		}
		
		public static void selectByVisibleText(WebElement element,String text) {
			Select s = new Select(element);
			s.selectByVisibleText(text);
		}
		
		public static void selectByIndex(WebElement element,int index) {
			Select s = new Select(element);
			s.selectByIndex(index);
		}
			
		public static void deselectAllOptions(WebElement element) {
			Select s = new Select(element);
			s.deselectAll();
		}
		
		public static void impWait(int sec) {
			driver.manage().timeouts().implicitlyWait(sec, TimeUnit.SECONDS);
		}
		
		public static void captureScreen() throws IOException {
			Date d = new Date();
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileHandler.copy(scrFile, new File("./ScreenShot/" + d.toString().replace(":", "_") + ".png"));
		}
}
