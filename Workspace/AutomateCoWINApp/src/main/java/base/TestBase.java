package base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariTechPreviewDriverInfo;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.PropertyReading;
import io.github.bonigarcia.wdm.WebDriverManager;
import listener.WebEventListener;
import util.MyLogger;
import util.TestUtil;

import static base.Constants.*;

public class TestBase 
{
	
	public static WebDriver driver;
	public static EventFiringWebDriver e_driver;
	public static WebEventListener eventListener;
	public static PropertyReading objConfigPropertyReading;
	public static String datafilename;
	public static String driverpath;
	public static String logfilepath;
	public static String logfilenamewebdriverevent,logfilenametestngmethodlog;
	public static String url;
	public static String browserName,extentreport,downloadpath,screenshots;
	//public static MyLogger log;
	//public static Logger debugger;
	public static ThreadLocal<WebDriver> tldriver = new ThreadLocal<WebDriver>();	
	public TestBase()
	{		
				
		objConfigPropertyReading = new PropertyReading();
		extentreport = objConfigPropertyReading.configPropertiesReading().getProperty("extentreportpath");
		downloadpath = objConfigPropertyReading.configPropertiesReading().getProperty("downloadpath");
		screenshots = objConfigPropertyReading.configPropertiesReading().getProperty("screenshots");
		driverpath= objConfigPropertyReading.configPropertiesReading().getProperty("driverpath");
		//logfilepath= objConfigPropertyReading.configPropertiesReading().getProperty("logfilepath");
		//logfilenamewebdriverevent= objConfigPropertyReading.configPropertiesReading().getProperty("logfilenamewebdriverevent");
		url= objConfigPropertyReading.configPropertiesReading().getProperty("url");
		browserName= objConfigPropertyReading.configPropertiesReading().getProperty("browser");		
	}
	
	
	public static WebDriver initialization()
	{
		if(browserName.equals("chrome"))
		{
			//System.setProperty("webdriver.chrome.driver",driverpath+"chromedriver.exe");
			ChromeOptions options = new ChromeOptions();
			WebDriverManager.chromedriver().setup();
			//driver = new ChromeDriver(options);
			//options.addExtensions(new File(driverpath+"pdfviewer.crx"));
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
			prefs.put("download.default_directory", System.getProperty("user.dir")+objConfigPropertyReading.configPropertiesReading().getProperty("downloadpath"));
			//System.out.println(System.getProperty("user.dir")+objConfigPropertyReading.configPropertiesReading().getProperty("downloadpath"));
			options.setExperimentalOption("prefs", prefs);
			//options.addArguments("--lang=en");
			//https://chromium.googlesource.com/chromium/src/+/master/chrome/common/pref_names.cc
			//options.setHeadless(true);
			tldriver.set(new ChromeDriver(options));
			//driver.get(objConfigPropertyReading.configPropertiesReading().getProperty("URL"));
		}
		else if(browserName.equals("FF"))
		{
			//System.setProperty("webdriver.gecko.driver", driverpath+"geckodriver.exe");
			WebDriverManager.firefoxdriver().setup();
			tldriver.set(new FirefoxDriver());
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
			//driver = new FirefoxDriver();
			/*
			 * driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT_TIME_OUT,
			 * TimeUnit.SECONDS); driver.manage().window().maximize();
			 */
			//driver.get(objConfigPropertyReading.configPropertiesReading().getProperty("URL"));	
		}
		else if(browserName.equals("IE"))
		{
			//System.setProperty("webdriver.ie.driver",driverpath+"IEDriverServer.exe");
			WebDriverManager.iedriver().setup();
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
			
			//driver= new InternetExplorerDriver(options);
			tldriver.set(new InternetExplorerDriver(options));
			//driver.manage().window().maximize();
	        //driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT_TIME_OUT, TimeUnit.SECONDS);
	        //driver.get(objConfigPropertyReading.configPropertiesReading().getProperty("URL"));
		}
		else
		{
			//log.writeToDebugLog(debugger,"Please check the browser value & other configuration issues");
		}
		e_driver = new EventFiringWebDriver(getDriver());
		// Now create object of EventListerHandler to register it with EventFiringWebDriver
		eventListener = new WebEventListener();
		e_driver.register(eventListener);
		//tldriver=e_driver;
		tldriver.set(e_driver);
		
		
		getDriver().manage().deleteAllCookies();
		//getDriver().manage().window().fullscreen();
		getDriver().manage().window().maximize();
		getDriver().manage().timeouts().pageLoadTimeout(PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		getDriver().manage().timeouts().implicitlyWait(IMPLICIT_WAIT_TIME_OUT, TimeUnit.SECONDS);		
		getDriver().get(url);
		return getDriver();		
	}
	
	public static synchronized WebDriver getDriver()
	{		
		return tldriver.get();
	}

	
	/**
	 * take screenshot
	 */
		public static String getScreenshot() 
		{
		File src = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + screenshots + System.currentTimeMillis() + ".png";
		
		File destination = new File(path);
		try 
		{
			FileUtils.copyFile(src, destination);
		} 
		catch (IOException e) 
		{
			System.out.println("Capture Failed " + e.getMessage());
		}
		
		return path;
	}
	
}