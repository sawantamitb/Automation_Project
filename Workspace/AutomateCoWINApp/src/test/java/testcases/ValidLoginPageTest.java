package testcases;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.xml.DOMConfigurator;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base.PropertyReading;
import base.TestBase;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Stories;
import io.qameta.allure.Story;
import pages.HomePage;
import pages.LoginPage;
import util.ReadWriteExcel;

import org.apache.log4j.Logger;
import static base.Constants.*;

public class ValidLoginPageTest extends TestBase
{
	LoginPage loginPage;
	HomePage homePage;
	// property reading config
	static PropertyReading objPropertyReading = new PropertyReading();
	//static File resourcesDirectory = new File(objPropertyReading.configPropertiesReading().getProperty("testsuiteexcel"));	
	public final static String testcasename = "ValidLoginPageTest";	
	public final static String testdatasheetname = "Self Registration";	
	static Logger log = Logger.getLogger("devpinoyLogger");	
	
	
	public ValidLoginPageTest()
	{
		super();
		PropertyConfigurator.configure("log4j.properties"); 
	}
	
	@BeforeClass
	public void setUpDBConnection() throws IOException
	{		
		ReadWriteExcel.setExcelFileSheet(testsuite, testcasename);	
		ReadWriteExcel.setRowNumber(ReadWriteExcel.getRowCellIndex(testsuite,testcasename,testcasenamecolumn));
		ReadWriteExcel.setColumnNumber(ReadWriteExcel.getColumnNameIndex(testsuite,passfailcolumn));
		if(!ReadWriteExcel.checkToRunUtility(casetorun, testcasename))
		{
			ReadWriteExcel.setCellData(testsuite,ReadWriteExcel.getRowNumber(), ReadWriteExcel.getColumnNumber(), "Skip");
			throw new SkipException("Test case skipped due to flag was not set properly for "+testcasename);
		}			
		
		ReadWriteExcel.setParameter1(ReadWriteExcel.getParameter1(testdatasheetname, testcasename));
		ReadWriteExcel.setParameter9(ReadWriteExcel.getParameter9(testdatasheetname, testcasename));
		ReadWriteExcel.setParameter10(ReadWriteExcel.getParameter10(testdatasheetname, testcasename));
	}
	
		
	
	  @BeforeMethod public void setUp() 
	  {
		  initialization();
		  log.info("This is Logger Info"); 
		  loginPage = new LoginPage(); 
	  }
	 
		
	@Test(description="Verifying login title")
	@Severity(SeverityLevel.MINOR)
	@Description("Test Case Description: Verifying login title")
	@Story("Story Name: Authentication Module")
	public void verifyTitle() throws InterruptedException
	{	
				
		try
		{
			log.debug("Infor ");
			Assert.assertEquals(loginPage.verifyTitle(), ReadWriteExcel.data1);
		}
		catch (Exception e) 
		{
			log.debug("Exception Error: "+ e);
		}
	}
	
	
	  @Test(priority=2, description="Verifying login and logout")	  
	  @Severity(SeverityLevel.BLOCKER)	  
	  @Description("Test Case Description: Verifying login and logout functionality")	  
	  @Story("Story Name: Authentication Module") 
	  public void loginTest() throws InterruptedException 
	  { 
		 
		  homePage = loginPage.login(ReadWriteExcel.data9,ReadWriteExcel.data10); 
		  homePage.clickOnLogout(); 
	  }
	 
		
		
	@AfterMethod
	public void tearDown()
	{
		getDriver().quit();		
	}

}