package util;

import static base.Constants.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.TestBase;

public class TestUtil extends TestBase 
{
	public static MyLogger log;
	public static Logger debugger;
	
	public TestUtil()
	{
		log = new MyLogger();
		debugger = log.configureLogger(logfilepath, logfilenamewebdriverevent);		
	}

	public static String TESTDATA_SHEET_PATH = "/Users/naveenkhunteta/Documents/workspace"
			+ "/FreeCRMTest/src/main/java/com/crm/qa/testdata/FreeCrmTestData.xlsx";

	static Workbook book;
	static Sheet sheet;
	static JavascriptExecutor js;

	public void switchToFrame(String framename)
	{
		driver.switchTo().frame(framename);
	}

	public static Object[][] getTestData(String sheetName) throws InvalidFormatException 
	{
		FileInputStream file = null;
		try 
		{
			file = new FileInputStream(System.getProperty("user.dir")+datafilename);
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		try 
		{
			book = WorkbookFactory.create(file);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		sheet = book.getSheet(sheetName);
		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		System.out.println(sheet.getLastRowNum() + "--------" +	sheet.getRow(0).getLastCellNum());
		for (int i = 0; i < sheet.getLastRowNum(); i++)
		{
			for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) 
			{
				data[i][k] = sheet.getRow(i + 1).getCell(k).toString();
				// System.out.println(data[i][k]);
			}
		}
		return data;
	}

	
	  public static void takeScreenshotAtEndOfTest() throws IOException { File
	  scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE); String
	  path = System.getProperty("user.dir") + screenshots +
	  System.currentTimeMillis() + ".png"; File destination = new File(path);
	  FileUtils.copyFile(scrFile, destination);
	  log.writeToInfoLog(debugger,"Screenshot taken at path: '" +
	  destination.toString() + "' using browser '"+browserName+"'."); }
	 
	
	
	public static void runTimeInfo(String messageType, String message) throws InterruptedException {
		js = (JavascriptExecutor) driver;
		// Check for jQuery on the page, add it if need be
		js.executeScript("if (!window.jQuery) {"
				+ "var jquery = document.createElement('script'); jquery.type = 'text/javascript';"
				+ "jquery.src = 'https://ajax.googleapis.com/ajax/libs/jquery/2.0.2/jquery.min.js';"
				+ "document.getElementsByTagName('head')[0].appendChild(jquery);" + "}");
		Thread.sleep(5000);

		// Use jQuery to add jquery-growl to the page
		js.executeScript("$.getScript('https://the-internet.herokuapp.com/js/vendor/jquery.growl.js')");

		// Use jQuery to add jquery-growl styles to the page
		js.executeScript("$('head').append('<link rel=\"stylesheet\" "
				+ "href=\"https://the-internet.herokuapp.com/css/jquery.growl.css\" " + "type=\"text/css\" />');");
		Thread.sleep(5000);

		// jquery-growl w/ no frills
		js.executeScript("$.growl({ title: 'GET', message: '/' });");
//'"+color+"'"
		if (messageType.equals("error")) {
			js.executeScript("$.growl.error({ title: 'ERROR', message: '"+message+"' });");
		}else if(messageType.equals("info")){
			js.executeScript("$.growl.notice({ title: 'Notice', message: 'your notice message goes here' });");
		}else if(messageType.equals("warning")){
			js.executeScript("$.growl.warning({ title: 'Warning!', message: 'your warning message goes here' });");
		}else
			System.out.println("no error message");
		// jquery-growl w/ colorized output
//		js.executeScript("$.growl.error({ title: 'ERROR', message: 'your error message goes here' });");
//		js.executeScript("$.growl.notice({ title: 'Notice', message: 'your notice message goes here' });");
//		js.executeScript("$.growl.warning({ title: 'Warning!', message: 'your warning message goes here' });");
		Thread.sleep(5000);
	}
	
	public static void killprocess()
	{
		driver.quit();
		//log.writeToDebugLog(debugger, "Killing the all process");
	}
	public static String resourcesDirectory()
	{
		File resourcesDirectory = new File(System.getProperty("user.dir")+datafilename);
		//System.out.println(resourcesDirectory.getAbsolutePath());
		return resourcesDirectory.toString();
	}
	
	public void waitTillPresenceOfElementLocated(WebDriverWait wait,int seconds, By locator)
	{
		wait = new WebDriverWait(driver, seconds);
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	public void waitForElementUsingFluentWeight(final By locator)
	{
			FluentWait<WebDriver> wait123 = new FluentWait<WebDriver>(driver)
			        .withTimeout(FLUENT_WAIT_TIME_OUT, TimeUnit.SECONDS)
			        .pollingEvery(POLLING_TIME_OUT, TimeUnit.SECONDS)
			        .ignoring(NoSuchElementException.class);
			
					wait123.until(new Function<WebDriver, WebElement>() 
					 {
						 public WebElement apply(WebDriver driver) 
						 {
							 return driver.findElement(locator);
						 }
					 });			
	}
	
	public boolean betweenIntRange(String i, int minValueInclusive, int maxValueInclusive)
	{
	    return (Integer.parseInt(i) >= minValueInclusive && Integer.parseInt(i) <= maxValueInclusive);
	}
	//To retrieve No Of Rows from .xls file's sheets. 
	public Map testsplitdata(String str)
 	{
 		Map<String, String> map = new HashMap<String, String>();
 		for(String keyValue : str.split(","))
 		{
 			String[] pairs = keyValue.split("=");
 			map.put(pairs[0], pairs.length == 1 ? "": pairs[1]);
 		}			 			
 		
 		/*for(String s : map.keySet()) 
 		{
 	        System.out.println(s + " is " + map.get(s));
 	    }*/
 		
		return map; 		
 	}
	
	public String checkValueofHashMapkey(String str,String key)
 	{
 		Map<String, String> map = new HashMap<String, String>();
 		for(String keyValue : str.split(","))
 		{
 			String[] pairs = keyValue.split("=");
 			map.put(pairs[0], pairs.length == 1 ? "": pairs[1]);
 		}			
 		return map.get(key); 		
 	}
		
}