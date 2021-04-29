package com.anchalapp.testcases;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.ScriptTimeoutException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import com.anchalapp.AnchalAppFunctions;
import com.anchalapp.MetaDataHeader;
import com.anchalapp.RoleVariations;

import common.DriverConfig;
import common.PropertyReading;
import common.ReadWriteExcel;
import logger.MyLogger;

import java.io.IOException;

	public class TestAutomateRoleForm 
	{				
		static WebDriver driver;
		static DriverConfig objDriverConfig;
		static AnchalAppFunctions objAnchalAppFunctions;
		static RoleVariations objRoleVariations = new RoleVariations() ;
		static ReadWriteExcel r1 = new ReadWriteExcel(DriverConfig.resourcesDirectory());		
		static MetaDataHeader metadata = new MetaDataHeader();
		static PropertyReading objConfigPropertyReading = new PropertyReading();
		
		static String driverpath= objConfigPropertyReading.configPropertiesReading().getProperty("driverpath");
		static String baseurl= objConfigPropertyReading.configPropertiesReading().getProperty("baseurl");
		static String browsername= objConfigPropertyReading.configPropertiesReading().getProperty("browsername");
		static String validateurl= objConfigPropertyReading.configPropertiesReading().getProperty("validateurl");
		static String login= objConfigPropertyReading.configPropertiesReading().getProperty("login");
		static String password= objConfigPropertyReading.configPropertiesReading().getProperty("password");
		
		//log file
		static String logfilepath= objConfigPropertyReading.configPropertiesReading().getProperty("logfilepath");
		static String logfilename= objConfigPropertyReading.configPropertiesReading().getProperty("logfilename");
		static MyLogger log = new MyLogger();
		static Logger debugger = log.configureLogger(logfilepath, logfilename);
		
		// sheet name
		static String exceldatasheet= objConfigPropertyReading.configPropertiesReading().getProperty("exceldatasheet");
		
		// types of role
		
		static String rolethmo= objConfigPropertyReading.configPropertiesReading().getProperty("thmo");
		static String rolephcmo= objConfigPropertyReading.configPropertiesReading().getProperty("phcmo");
		static String roleanm= objConfigPropertyReading.configPropertiesReading().getProperty("anm");
		static String roleasha= objConfigPropertyReading.configPropertiesReading().getProperty("asha");
	
		@Before
		public void setup()
		{
			try
			{
				AnchalAppFunctions.setup(browsername, baseurl); 
				log.writeToInfoLog(debugger, browsername+" has been opened and successfully launching url");
			}
			catch(Exception e)
			{
				log.writeToFatalLog(debugger, "Exception during setup the environment", e);			
			}				
		}
				
		@Test
		public void testAnchalRoleAutomate() throws InterruptedException
		{	
			try
			{
				AnchalAppFunctions.authentication(login, password, validateurl);
				log.writeToInfoLog(debugger, "Authetication has been done succeessfully using provided credential.");
				if(exceldatasheet.equalsIgnoreCase(rolethmo))
				{
					objRoleVariations.executeTHMO(exceldatasheet);
				}
				else if(exceldatasheet.equalsIgnoreCase(rolephcmo))
				{
					objRoleVariations.executePHCMO(exceldatasheet);
				}
				else if(exceldatasheet.equalsIgnoreCase(roleanm))
				{
					objRoleVariations.executeANM(exceldatasheet);
				}
				else if(exceldatasheet.equalsIgnoreCase(roleasha))
				{
					objRoleVariations.executeASHA(exceldatasheet);					
				}
			}
			catch(AssertionError e)
			{
				log.writeToErrorLog(debugger, "Assertion error while verifying the expected result with actual result", e);				
			}
			catch(NotFoundException e)
			{
				log.writeToErrorLog(debugger, "Element is not found exception", e);				
			}
			catch(ScriptTimeoutException e)
			{
				log.writeToErrorLog(debugger, "Selenium script set timeout exception", e);				
			}
			catch(TimeoutException e)
			{
				log.writeToErrorLog(debugger, "Selenium wait timeout exception", e);				
			}
			catch(InvalidElementStateException e)
			{
				log.writeToErrorLog(debugger, "Invalid element state exception", e);				
			}
			catch(Exception e)
			{
				log.writeToFatalLog(debugger, "Exception during testing scenario", e);			
			}		
			
		}	
		
		@After
		public void testKillProcess() throws IOException
		{
			try
			{
				AnchalAppFunctions.killprocess();
				log.writeToDebugLog(debugger, "Closing the browser");
				if(r1.ipstr!=null)
					r1.ipstr.close();
				if(r1.opstr!=null)
					r1.opstr.close();	
				log.writeToDebugLog(debugger, "Closing the excel file object connection if opened");
			
			}
			catch(NoSuchSessionException e)
			{
				log.writeToErrorLog(debugger, "No such session available to quit", e);
			}
			catch(Exception e)
			{
				log.writeToFatalLog(debugger, "Exception during testing scenario", e);				
			}		
		}
				
	}
