package com.flipkart.TestBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.flipkart.ExcelReader.ExcelReader;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class TestBase {

	public static final Logger log = Logger.getLogger(TestBase.class.getName());
	public static WebDriver driver ;
	public WebDriverWait wait ;
	public static ExtentReports extent ;
	public static ExtentTest test ;
	public ITestResult result ;
	
	public Properties OR = new Properties();
	public Properties APP = new Properties();
	
	
	public void loadFromORproperties() throws IOException {
		File path = new File(System.getProperty("user.dir")+"//src//main//java//com//flipkart//Config//OR.properties");
		FileInputStream fis = new FileInputStream(path);
		OR.load(fis);
	}
	
	public void loadFromAPPproperties() throws IOException {
		File path = new File(System.getProperty("user.dir")+"//src//main//java//com//flipkart//Config//APP.properties");
		FileInputStream fis = new FileInputStream(path);
		APP.load(fis);
	}
	
	static {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("dd_MM_YYYY_HH_mm_ss");
		extent = new ExtentReports(System.getProperty("user.dir")+"//src//main//java//com//flipkart//Reports//"+formatter.format(cal.getTime())+".html",false);
	}
	
	public String[][] readExcel(String sheetName,String excelName) {
		String path = System.getProperty("user.dir")+"//src//main//java//com//flipkart//Data//testData.xlsx" ;
		ExcelReader excel = new ExcelReader(path);
		String[][] testData = excel.getDataFromSheet(excelName, sheetName);
		return testData ;
	}
	
	//use this in test case
	/*@DataProvider(name="LoginData")
	public String[][] getTestData(){
		String[][] TestData = ReadExcel("TC_002_VerifyCustomerName","TestData.xlsx");
		return TestData ;
	}
	
	@Test(dataProvider="LoginData")
	*/
	
	
	public void init() throws IOException {
		loadFromAPPproperties();
		loadFromORproperties();
		selectBrowser(OR.getProperty("browserName"));
		wait = new WebDriverWait(driver, 60);
		getBaseURL(OR.getProperty("URL"));
		waitForElementToLoad();
		PropertyConfigurator.configure("log4j.properties");
	}
	
	public void selectBrowser(String browserName) {
		if(browserName.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir")+"//Drivers//geckodriver.exe");
			driver = new FirefoxDriver();
			
		}
		else if(browserName.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"//Drivers//chromedriver.exe");
			driver=new ChromeDriver();
		}
	}
	
	
	public void getBaseURL(String url) {
		driver.get(url);
		try {
			driver.manage().window().maximize();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void waitForElementToLoad() {
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	}
	
	public void waitUntilURLcontains(String url) {
		wait.until(ExpectedConditions.urlContains(url));
	}
	
	public void waitUntilTitleContains(String title) {
		wait.until(ExpectedConditions.titleContains(title));
	}
	
	
	public void waitForPageLoad() {
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
	}
	
	public void clearHistory() {
		driver.manage().deleteAllCookies();
	}
	
	public void refreshPage() {
		driver.navigate().refresh();
		
	}
	
	public void previousPage() {
		driver.navigate().back();
	}
	
	public String getPageTitle() {
		return driver.getTitle();
	}
	
	public String getURL() {
		return driver.getCurrentUrl();
	}
	
	public String getScreenshot(String methodName) {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("dd_MM_YYYY_hh_mm_ss");
		
		try {
			File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			String reportDir = System.getProperty("user.dir")+"//src//main//java//com//flipkart//Screenshots//" ;
			String destination = reportDir + methodName+"_"+formatter.format(cal.getTime())+".png" ;
			File destFile = new File(destination);
			FileHandler.copy(srcFile, destFile);
			return destination ;
		} catch (WebDriverException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public void selectFromAutoSuggestionList(String name) {
		List<WebElement> list = driver.findElements(By.tagName("li"));
		for(WebElement we : list) {
			String temp = we.getText();
			if(temp.contains(name)) {
				we.click();
			}
		}
	}
	
	public void clickOnTheLink(String linkName) {
		WebElement link = driver.findElement(By.xpath("//*[contains(text(),'"+linkName+"')]"));
		if(link!=null) {
			link.click();
		}
		else {
			System.out.println(linkName+" link not found");
		}
	}
	
	
	
	public boolean isFileDownloaded(String fileName,String downloadPath) {
		
		boolean downloadFlag = false ;
		
		File file = new File(downloadPath);
		File[] directory = file.listFiles();
		for(int i=0; i<=directory.length; i++) {
			if(directory[i].getName().equalsIgnoreCase(fileName)) {
				downloadFlag = true ;
			}
		}
		return downloadFlag ;
	}
	
	public boolean isExpectedTextPresentInPage(String text) {
		boolean textPresent = false ;
		String expectedText=driver.findElement(By.xpath("//*[contains(text(),'"+text+"')]")).getText();
		if(expectedText.equalsIgnoreCase(text)) {
			textPresent=true ;
		}
		return textPresent ;
	}
	
	//GET BZROKEN LINKS
	
	public void verifyURLstatus(String url) {
		HttpClient client = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet(url);
		try {
			HttpResponse response = client.execute(request);
			if(response.getStatusLine().getStatusCode()==200) {
				System.out.println("valid link : "+url);
			}
			else {
				System.out.println("broken link : "+url);
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void getBrokenLinks() {
		List<WebElement> li = driver.findElements(By.tagName("a"));
		for(WebElement we : li) {
			if (we!=null) {
				String url = we.getAttribute("href");
				if (url != null && !url.contains("javascript")) {
					verifyURLstatus(url);
				}
				else {
					System.out.println("invalid url");
				}
			}
		}
	}
	
	public void getResult(ITestResult result) {
		if(result.getStatus()==ITestResult.SUCCESS) {
			test.log(LogStatus.PASS, result.getName()+" Test is Passed");
		}
		else if(result.getStatus()==ITestResult.FAILURE) {
			test.log(LogStatus.FAIL, result.getName()+ "Test is Failed");
			test.log(LogStatus.FAIL, test.addScreenCapture(Thread.currentThread().getStackTrace()[1].getMethodName()));
		}
		else if(result.getStatus()==ITestResult.SKIP) {
			test.log(LogStatus.SKIP, result.getName()+" Test is skipped");
		}
		else if(result.getStatus()==ITestResult.STARTED) {
			test.log(LogStatus.INFO, result.getName()+" Test is Started");
		}
	}
	
	@BeforeMethod
	public void beforeMethod(Method result) {
		test = extent.startTest(result.getName());
		test.log(LogStatus.INFO, result.getName()+ " Test is started");
	}
	
	@AfterMethod
	public void afterMethod(ITestResult result) {
		getResult(result);
	}
	
	@AfterClass(alwaysRun=true)
	public void endTest() {
		driver.close();
		extent.endTest(test);
		extent.flush();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

