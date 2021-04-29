package common;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

//import org.apache.commons.lang.time.DateUtils;
import org.apache.log4j.Logger;
//import org.apache.maven.shared.utils.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import logger.MyLogger;
import static common.Constants.*;

public class DriverConfig
{
	static WebDriver driver;
	JavascriptExecutor js= (JavascriptExecutor)driver;
	
	// property reading config
	
	//log initialization			
	static PropertyReading objConfigPropertyReading = new PropertyReading();
	static String datafilename= objConfigPropertyReading.configPropertiesReading().getProperty("datafilename");
	static String driverpath= objConfigPropertyReading.configPropertiesReading().getProperty("driverpath");
	static String logfilepath= objConfigPropertyReading.configPropertiesReading().getProperty("logfilepath");
	static String logfilename= objConfigPropertyReading.configPropertiesReading().getProperty("logfilename");
	
	
	
	static MyLogger log = new MyLogger();
	static Logger debugger = log.configureLogger(logfilepath, logfilename);
	
	public DriverConfig(WebDriver driver)
	{
		this.driver = driver;		
	}
	
	public  WebDriver getDriver() 
	{
		return driver;
    }
	
	public static void killprocess(WebDriver driver)
	{
		driver.quit();
		/*
		 * WindowsUtils.killByName("IEDriverServer.exe");
		 * WindowsUtils.killByName("iexplore.exe");
		 * WindowsUtils.killByName("chromedriver.exe");
		 * WindowsUtils.killByName("geckodriver.exe");
		 */
		driver=null;
		log.writeToDebugLog(debugger, "Killing the all process");
		
	}
	
	public void browserSelection(String browser)
	{
		
		//String browser = objConfigPropertyReading.configPropertiesReading().getProperty("browser");
		System.out.println(browser);
		
		if(browser.equalsIgnoreCase("firefox"))
		{
			System.setProperty("webdriver.gecko.driver", driverpath+"geckodriver.exe");
			/*
			FirefoxOptions options = new FirefoxOptions();
			DesiredCapabilities dc = new DesiredCapabilities();
			dc.setCapability(CapabilityType.SUPPORTS_ALERTS, true);
			dc.setCapability(CapabilityType.SUPPORTS_JAVASCRIPT, true);
			dc.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			//dc.setCapability(CapabilityType.UNHANDLED_PROMPT_BEHAVIOUR, true);
			//dc.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, true);
			dc.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
			dc.setCapability(CapabilityType.ELEMENT_SCROLL_BEHAVIOR, true);
			options.merge(dc);
			options.addPreference("browser.zoom.siteSpecific", false);
			options.addPreference("browser.download.folderList",2);
			options.addPreference("browser.download.manager.showWhenStarting",false);
			//options.addPreference("browser.download.dir",System.getProperty("user.dir")+objConfigPropertyReading.configPropertiesReading().getProperty("downloadpath"));
			options.addPreference("browser.helperApps.neverAsk.saveToDisk","application/pdf,text/csv,application/x-msexcel,application/excel,application/x-excel,application/vnd.ms-excel,image/png,image/jpeg,text/html,text/plain,application/msword,application/xml,application/zip,application/octet-stream,application/x-zip,application/x-zip-compressed,application/download,multipart/x-zip");
			options.addPreference("browser.helperApps.neverAsk.openFile","application/pdf,text/csv,application/x-msexcel,application/excel,application/x-excel,application/vnd.ms-excel,image/png,image/jpeg,text/html,text/plain,application/msword,application/xml,application/zip,application/octet-stream,application/x-zip,application/x-zip-compressed,application/download,multipart/x-zip");
			options.addPreference("browser.helperApps.alwaysAsk.force", false);
			options.addPreference("browser.download.manager.alertOnEXEOpen", false);
			options.addPreference("browser.download.manager.focusWhenStarting", false);
			options.addPreference("browser.download.manager.useWindow", false);
			options.addPreference("browser.download.manager.showAlertOnComplete", true);
			options.addPreference("browser.download.manager.closeWhenDone", false);
			options.addPreference("pdfjs.disabled", true);
			options.addPreference("plugin.scan.plid.all", false);
			options.addPreference("plugin.scan.Acrobat", "99.0");
			driver = new FirefoxDriver(options);
			*/
			driver = new FirefoxDriver();
			driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT_TIME_OUT, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			//driver.get(objConfigPropertyReading.configPropertiesReading().getProperty("URL"));	
		}
		else if(browser.equalsIgnoreCase("chrome"))
		{
			System.setProperty("webdriver.chrome.driver",driverpath+"chromedriver.exe");
			ChromeOptions options = new ChromeOptions();
			options.addExtensions(new File(driverpath+"pdfviewer.crx"));
			DesiredCapabilities dc = new DesiredCapabilities();
			dc.setCapability(CapabilityType.SUPPORTS_ALERTS, true);
			dc.setCapability(CapabilityType.SUPPORTS_JAVASCRIPT, true);
			dc.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			//dc.setCapability(CapabilityType.UNHANDLED_PROMPT_BEHAVIOUR, true);
			//dc.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, true);
			dc.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
			dc.setCapability(CapabilityType.ELEMENT_SCROLL_BEHAVIOR, true);
			dc.setCapability("pdfjs.disabled", true);
			options.merge(dc);
			options.addArguments("--start-maximized");
			options.addArguments("disable-infobars");
			Map<String, Object> prefs = new HashMap<String, Object>();
			prefs.put("download.prompt_for_download", false);
			//prefs.put("download.default_directory", System.getProperty("user.dir")+objConfigPropertyReading.configPropertiesReading().getProperty("downloadpath"));
			//System.out.println(System.getProperty("user.dir")+objConfigPropertyReading.configPropertiesReading().getProperty("downloadpath"));
			options.setExperimentalOption("prefs", prefs);
			//options.addArguments("--lang=en");
			//https://chromium.googlesource.com/chromium/src/+/master/chrome/common/pref_names.cc
			//options.setHeadless(true);
			driver = new ChromeDriver(options);
			driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT_TIME_OUT, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			//driver.get(objConfigPropertyReading.configPropertiesReading().getProperty("URL"));			
	    }
		else if(browser.equalsIgnoreCase("IE"))
		{
			System.setProperty("webdriver.ie.driver",driverpath+"IEDriverServer.exe");
			InternetExplorerOptions options = new InternetExplorerOptions();
			DesiredCapabilities dc = new DesiredCapabilities();
			//dc.setCapability(CapabilityType.BROWSER_NAME, "internet explorer");
			dc.setCapability(CapabilityType.SUPPORTS_ALERTS, true);
			dc.setCapability(CapabilityType.SUPPORTS_JAVASCRIPT, true);
			dc.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			//dc.setCapability(CapabilityType.UNHANDLED_PROMPT_BEHAVIOUR, true);
			//dc.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, true);
			//dc.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
			dc.setCapability(CapabilityType.ELEMENT_SCROLL_BEHAVIOR, true);
			dc.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			//dc.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
			options.merge(dc);
			driver= new InternetExplorerDriver(options);
			driver.manage().window().maximize();
	        driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT_TIME_OUT, TimeUnit.SECONDS);
	        //driver.get(objConfigPropertyReading.configPropertiesReading().getProperty("URL"));	
	   	}
		else
		{
			log.writeToDebugLog(debugger,"Please check the browser value & other configuration issues");
		}			
	}		
	
	public static String resourcesDirectory()
	{
		File resourcesDirectory = new File(System.getProperty("user.dir")+datafilename);
		//System.out.println(resourcesDirectory.getAbsolutePath());
		return resourcesDirectory.toString();
	}
	
	public void waitTillPresenceOfElementLocated(WebDriver driver,WebDriverWait wait,int seconds, By locator)
	{
		wait = new WebDriverWait(driver, seconds);
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	public void waitForElementUsingFluentWeight(WebDriver driver, final By locator)
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
}
