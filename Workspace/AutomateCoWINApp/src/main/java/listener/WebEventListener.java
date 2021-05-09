package listener;
/*************************************** PURPOSE **********************************
 - This class implements the WebDriverEventListener, which is included under events.
 The purpose of implementing this interface is to override all the methods and define certain useful  Log statements 
 which would be displayed/logged as the application under test is being run.
 Do not call any of these methods, instead these methods will be invoked automatically
 as an when the action done (click, findBy etc). 
 */

import java.io.IOException;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;
import util.MyLogger;
import base.TestBase;
import util.TestUtil;

public class WebEventListener extends TestBase implements WebDriverEventListener
{
	 
	 Logger logger = Logger.getLogger(WebEventListener.class.getName());		
	
	public WebEventListener()
	{	
		
		//DOMConfigurator.configure("log4j.xml");
		PropertyConfigurator.configure("log4j.properties");  
	}
		
	public void beforeNavigateTo(String url, WebDriver driver)
	{
		logger.info("Before navigating to: '" + url + "' using browser '"+browserName+"'.");
		//System.out.println("Before navigating to: '" + url + "'");
	}

	public void afterNavigateTo(String url, WebDriver driver)
	{
		logger.info("Navigated to:'" + url + "'");
		//System.out.println("Navigated to:'" + url + "'");
	}

	public void beforeChangeValueOf(WebElement element, WebDriver driver) 
	{
		//log.writeToInfoLog(debugger,"Value of the:" + element.toString() + " before any changes made");
		//System.out.println("Value of the:" + element.toString() + " before any changes made");
	}

	public void afterChangeValueOf(WebElement element, WebDriver driver) 
	{
		//log.writeToInfoLog(debugger,"Element value changed to: " + element.toString());
		//System.out.println("Element value changed to: " + element.toString());
	}

	public void beforeClickOn(WebElement element, WebDriver driver) 
	{
		logger.info("Trying to click on: "+ element.toString());
		//System.out.println("Trying to click on: " + element.toString());
	}

	public void afterClickOn(WebElement element, WebDriver driver) 
	{
		logger.info("Clicked on: "+ element.toString());
		//System.out.println("Clicked on: " + element.toString());
	}

	public void beforeNavigateBack(WebDriver driver) 
	{
		//log.writeToInfoLog(debugger,"Navigating back to previous page");
		//System.out.println("Navigating back to previous page");
	}

	public void afterNavigateBack(WebDriver driver)
	{
		//log.writeToInfoLog(debugger,"Navigated back to previous page");
		//System.out.println("Navigated back to previous page");
	}

	public void beforeNavigateForward(WebDriver driver) 
	{
		//log.writeToInfoLog(debugger,"Navigating forward to next page");
		//System.out.println("Navigating forward to next page");
	}

	public void afterNavigateForward(WebDriver driver) 
	{
		//log.writeToInfoLog(debugger,"Navigated forward to next page");
		//System.out.println("Navigated forward to next page");
	}

	public void onException(Throwable error, WebDriver driver)
	{
		//System.out.println("Exception occured: " + error);
		//log.writeToInfoLog(debugger,"Exception occured: " + error);
		//getScreenshot();		
		
		/*
		 * try { TestUtil.takeScreenshotAtEndOfTest(); } catch (IOException e) {
		 * e.printStackTrace(); }
		 */
		 
	}

	public void beforeFindBy(By by, WebElement element, WebDriver driver) 
	{
		//log.writeToInfoLog(debugger,"Trying to find Element By : " + by.toString());
		//System.out.println("Trying to find Element By : " + by.toString());
	}

	public void afterFindBy(By by, WebElement element, WebDriver driver) 
	{
	//	log.writeToInfoLog(debugger,"Found Element By : " + by.toString());
		//System.out.println("Found Element By : " + by.toString());
	}

	/*
	 * non overridden methods of WebListener class
	 */
	public void beforeScript(String script, WebDriver driver) {
	}

	public void afterScript(String script, WebDriver driver) {
	}

	public void beforeAlertAccept(WebDriver driver) {
		// TODO Auto-generated method stub

	}

	public void afterAlertAccept(WebDriver driver) {
		// TODO Auto-generated method stub

	}

	public void afterAlertDismiss(WebDriver driver) {
		// TODO Auto-generated method stub

	}

	public void beforeAlertDismiss(WebDriver driver) {
		// TODO Auto-generated method stub

	}

	public void beforeNavigateRefresh(WebDriver driver) {
		// TODO Auto-generated method stub

	}

	public void afterNavigateRefresh(WebDriver driver) {
		// TODO Auto-generated method stub

	}

	public void beforeChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
		// TODO Auto-generated method stub

	}

	public void afterChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
		// TODO Auto-generated method stub

	}

	public <X> void afterGetScreenshotAs(OutputType<X> arg0, X arg1) {
		// TODO Auto-generated method stub
		
	}

	public void afterGetText(WebElement arg0, WebDriver arg1, String arg2) {
		// TODO Auto-generated method stub
		
	}

	public void afterSwitchToWindow(String arg0, WebDriver arg1) {
		// TODO Auto-generated method stub
		
	}

	public <X> void beforeGetScreenshotAs(OutputType<X> arg0) {
		// TODO Auto-generated method stub
		
	}

	public void beforeGetText(WebElement arg0, WebDriver arg1) {
		// TODO Auto-generated method stub
		
	}

	public void beforeSwitchToWindow(String arg0, WebDriver arg1) {
		// TODO Auto-generated method stub
		
	}

}