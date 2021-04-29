package com.anchalapp;

import static common.Constants.THREAD_WAIT_TIME_OUT;
import static common.Constants.THREAD_WAIT_TIME_OUT_30000;
import static common.Constants.THREAD_WAIT_TIME_OUT_5000;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.os.WindowsUtils;
import org.openqa.selenium.support.ui.WebDriverWait;

import common.DriverConfig;
import common.PropertyReading;
import logger.MyLogger;

public class AnchalAppFunctions 
{
	static WebDriver driver;
	static WebDriverWait wait;
	static DriverConfig objDriverConfig;
	static AnchalAppFunctions objAnchalAppFunctions;
	static RoleVariations objRoleVariations;
	
	public AnchalAppFunctions(WebDriver driver)
	{
		this.driver = driver;		
	}
	public  WebDriver getDriver() 
	{
		return driver;
    }
	
	static PropertyReading objPropertyReading = new PropertyReading();
	static String validateurl= objPropertyReading.configPropertiesReading().getProperty("validateurl");
	
	//log file
	static String logfilepath= objPropertyReading.configPropertiesReading().getProperty("logfilepath");
	static String logfilename= objPropertyReading.configPropertiesReading().getProperty("logfilename");
	static MyLogger log = new MyLogger();
	static Logger debugger = log.configureLogger(logfilepath, logfilename);
	
	public static void setup(String browsername, String baseurl) throws InterruptedException  
	{
		
			objDriverConfig = new DriverConfig(driver);
			objDriverConfig.browserSelection(browsername);
			log.writeToInfoLog(debugger, browsername+" is selected for automation.");
		   	driver=objDriverConfig.getDriver();
		   	objAnchalAppFunctions = new AnchalAppFunctions(driver);				
			driver.get(baseurl);
			log.writeToInfoLog(debugger, "URL of application: "+baseurl);
			Thread.sleep(THREAD_WAIT_TIME_OUT);				
	}
	
	public static void authentication(String login, String password, String validateurl) throws InterruptedException  
	{
			driver.findElement(By.xpath(objPropertyReading.objectRepositoryPropertiesReading().getProperty("inputemail"))).sendKeys(login);
			log.writeToInfoLog(debugger, "Username: "+login);
			driver.findElement(By.xpath(objPropertyReading.objectRepositoryPropertiesReading().getProperty("inputpassword"))).sendKeys(password);
			log.writeToInfoLog(debugger, "Password: "+password);
			driver.findElement(By.xpath(objPropertyReading.objectRepositoryPropertiesReading().getProperty("btnlogin"))).click();
			//Thread.sleep(THREAD_WAIT_TIME_OUT_5000);
			
			driver=objAnchalAppFunctions.getDriver();
			objDriverConfig = new DriverConfig(driver);
			objDriverConfig.waitTillPresenceOfElementLocated(driver, wait, 60, By.xpath(objPropertyReading.objectRepositoryPropertiesReading().getProperty("labelhome")));
			driver=objDriverConfig.getDriver();
			objAnchalAppFunctions = new AnchalAppFunctions(driver);	
			
			String actualurl = driver.getCurrentUrl();
			Assert.assertEquals("There is an issue with actual url with post authentication ",validateurl,actualurl);
					
	}
	
	public static void navigate_to_add_role() throws InterruptedException  
	{
		
			driver.findElement(By.xpath("//button[@class='mat-icon-button']")).click();
			Thread.sleep(THREAD_WAIT_TIME_OUT);
			driver.findElement(By.xpath("//div[@class='mat-drawer-inner-container']//mat-nav-list[@role='navigation']/div[2]/a")).click();
			driver.findElement(By.xpath("//div[@class='ng-star-inserted']/a[3]")).click();
			Thread.sleep(THREAD_WAIT_TIME_OUT);				
	}
	
	public static void add_roles(String username, String mobilenumber, String roles) throws InterruptedException  
	{			
				driver.findElement(By.xpath(objPropertyReading.objectRepositoryPropertiesReading().getProperty("inputusername"))).sendKeys(username);
				log.writeToInfoLog(debugger, mobilenumber+" : Username '"+username+"' is entered successfully");						
			
				driver.findElement(By.xpath(objPropertyReading.objectRepositoryPropertiesReading().getProperty("inputmobilenumber"))).sendKeys(mobilenumber);
				log.writeToInfoLog(debugger, mobilenumber+" : Mobile Number '"+mobilenumber+"' is entered successfully");					
			
				driver.findElement(By.xpath(objPropertyReading.objectRepositoryPropertiesReading().getProperty("selectroles"))).sendKeys(roles);
				log.writeToInfoLog(debugger, mobilenumber+" : Role of user '"+roles+"' is selected successfully");
				Thread.sleep(THREAD_WAIT_TIME_OUT);			
	}
		
	public static boolean selectDHForFirstTime(String DH, String mobilenumber) throws InterruptedException  
	{		
			driver.findElement(By.xpath(objPropertyReading.objectRepositoryPropertiesReading().getProperty("inputdh"))).click();
			Thread.sleep(THREAD_WAIT_TIME_OUT_30000);
			log.writeToDebugLog(debugger, "First time it is taking time to load the DH ..."+DH);
			driver.findElement(By.xpath(objPropertyReading.objectRepositoryPropertiesReading().getProperty("inputdh"))).click();
			//mat-option/span[contains(text(),'"+" "+DH+" "+")']
			
			Boolean dhboolean = driver.findElements(By.xpath(objPropertyReading.objectRepositoryPropertiesReading().getProperty("selectdh1")+" "+DH.trim()+" "+objPropertyReading.objectRepositoryPropertiesReading().getProperty("selectdh2"))).size()>0;
			if(dhboolean)
			{
				driver.findElement(By.xpath(objPropertyReading.objectRepositoryPropertiesReading().getProperty("selectdh1")+" "+DH.trim()+" "+objPropertyReading.objectRepositoryPropertiesReading().getProperty("selectdh2"))).click();
				log.writeToInfoLog(debugger, mobilenumber+" : DH '"+DH+"' is selected successfully");
				Thread.sleep(THREAD_WAIT_TIME_OUT);	
				return true;
			}
			else
			{
				return false;
			}
				
	}
	
	public static boolean selectDH(String DH, String mobilenumber) throws InterruptedException  
	{
			driver.findElement(By.xpath(objPropertyReading.objectRepositoryPropertiesReading().getProperty("inputdh"))).click();
			Thread.sleep(THREAD_WAIT_TIME_OUT);
			Boolean dhboolean = driver.findElements(By.xpath(objPropertyReading.objectRepositoryPropertiesReading().getProperty("selectdh1")+" "+DH.trim()+" "+objPropertyReading.objectRepositoryPropertiesReading().getProperty("selectdh2"))).size()>0;
			if(dhboolean)
			{
				driver.findElement(By.xpath(objPropertyReading.objectRepositoryPropertiesReading().getProperty("selectdh1")+" "+DH.trim()+" "+objPropertyReading.objectRepositoryPropertiesReading().getProperty("selectdh2"))).click();
				log.writeToInfoLog(debugger, mobilenumber+" : DH '"+DH+"' is selected successfully");
				Thread.sleep(THREAD_WAIT_TIME_OUT);	
				return true;
			}
			else
			{
				return false;
			}
				
	}
	
	public static boolean selectCHC(String CHC, String mobilenumber) throws InterruptedException
	{
			driver.findElement(By.xpath(objPropertyReading.objectRepositoryPropertiesReading().getProperty("inputchc"))).click();
			Thread.sleep(THREAD_WAIT_TIME_OUT);
			Boolean chcboolean = driver.findElements(By.xpath(objPropertyReading.objectRepositoryPropertiesReading().getProperty("selectchc1")+" "+CHC.trim()+" "+objPropertyReading.objectRepositoryPropertiesReading().getProperty("selectchc2"))).size()>0;
			if(chcboolean)
			{
				driver.findElement(By.xpath(objPropertyReading.objectRepositoryPropertiesReading().getProperty("selectchc1")+" "+CHC.trim()+" "+objPropertyReading.objectRepositoryPropertiesReading().getProperty("selectchc2"))).click();
				log.writeToInfoLog(debugger, mobilenumber+" : CHC '"+CHC+"' is selected successfully");
				Thread.sleep(THREAD_WAIT_TIME_OUT);
				return true;
			}
			else
			{
				return false;
			}	
	}
	
	public static boolean selectPHC(String PHC, String mobilenumber) throws InterruptedException
	{
		
			driver.findElement(By.xpath(objPropertyReading.objectRepositoryPropertiesReading().getProperty("inputphc"))).click();
			Thread.sleep(THREAD_WAIT_TIME_OUT);
			Boolean phcboolean = driver.findElements(By.xpath(objPropertyReading.objectRepositoryPropertiesReading().getProperty("selectphc1")+" "+PHC.trim()+" "+objPropertyReading.objectRepositoryPropertiesReading().getProperty("selectphc2"))).size()>0;
			if(phcboolean)
			{
				driver.findElement(By.xpath(objPropertyReading.objectRepositoryPropertiesReading().getProperty("selectphc1")+" "+PHC.trim()+" "+objPropertyReading.objectRepositoryPropertiesReading().getProperty("selectphc2"))).click();
				log.writeToInfoLog(debugger, mobilenumber+" :PHC '"+PHC+"' is selected successfully");
				Thread.sleep(THREAD_WAIT_TIME_OUT);
				return true;
			}
			else
			{
				return false;
			}	
	}
	
	public static boolean selectSC(String SC, String mobilenumber) throws InterruptedException
	{
			driver.findElement(By.xpath(objPropertyReading.objectRepositoryPropertiesReading().getProperty("inputsc"))).click();
			Thread.sleep(THREAD_WAIT_TIME_OUT);
			Boolean scboolean = driver.findElements(By.xpath(objPropertyReading.objectRepositoryPropertiesReading().getProperty("selectsc1")+" "+SC.trim()+" "+objPropertyReading.objectRepositoryPropertiesReading().getProperty("selectsc2"))).size()>0;
			if(scboolean)
			{
				driver.findElement(By.xpath(objPropertyReading.objectRepositoryPropertiesReading().getProperty("selectsc1")+" "+SC.trim()+" "+objPropertyReading.objectRepositoryPropertiesReading().getProperty("selectsc2"))).click();
				log.writeToInfoLog(debugger, mobilenumber+" : SC '"+SC+"' is selected successfully");
				Thread.sleep(THREAD_WAIT_TIME_OUT);
				return true;
			}
			else
			{
				return false;
			}		
	}
	
	public static boolean selectBlock(String block, String mobilenumber) throws InterruptedException
	{		
			driver.findElement(By.xpath(objPropertyReading.objectRepositoryPropertiesReading().getProperty("inputblock"))).click();
			Thread.sleep(THREAD_WAIT_TIME_OUT);
			Boolean blockboolean = driver.findElements(By.xpath(objPropertyReading.objectRepositoryPropertiesReading().getProperty("selectblock1")+" "+block.trim()+" "+objPropertyReading.objectRepositoryPropertiesReading().getProperty("selectblock2"))).size()>0;
			if(blockboolean)
			{
				driver.findElement(By.xpath(objPropertyReading.objectRepositoryPropertiesReading().getProperty("selectblock1")+" "+block.trim()+" "+objPropertyReading.objectRepositoryPropertiesReading().getProperty("selectblock2"))).click();
				log.writeToInfoLog(debugger, mobilenumber+" : Block '"+block+"' is selected successfully");
				Thread.sleep(THREAD_WAIT_TIME_OUT);
				return true;
			}
			else
			{
				return false;
			}	
	}
	
	public static boolean selectVillage(String village, String mobilenumber) throws InterruptedException
	{
			driver.findElement(By.xpath(objPropertyReading.objectRepositoryPropertiesReading().getProperty("inputvillage"))).click();
			Thread.sleep(THREAD_WAIT_TIME_OUT);
			Boolean villageboolean = driver.findElements(By.xpath(objPropertyReading.objectRepositoryPropertiesReading().getProperty("selectvillage1")+" "+village.trim()+" "+objPropertyReading.objectRepositoryPropertiesReading().getProperty("selectvillage2"))).size()>0;
			if(villageboolean)
			{
				driver.findElement(By.xpath(objPropertyReading.objectRepositoryPropertiesReading().getProperty("selectvillage1")+" "+village.trim()+" "+objPropertyReading.objectRepositoryPropertiesReading().getProperty("selectvillage2"))).click();
				log.writeToInfoLog(debugger, mobilenumber+" : Village '"+village+"' is selected successfully");
				Thread.sleep(THREAD_WAIT_TIME_OUT);	
				return true;
			}
			else
			{
				return false;
			}		
	}		
	
	
	public static void save() throws InterruptedException
	{
		
		driver.findElement(By.xpath(objPropertyReading.objectRepositoryPropertiesReading().getProperty("btnsave"))).click();
		Thread.sleep(THREAD_WAIT_TIME_OUT);	
		//log.writeToInfoLog(debugger, "The data has been saved successfully");		
		
	}
	
	public static void navigateToURL(String navigateurl) throws InterruptedException
	{		
			driver.navigate().to(navigateurl);
			Thread.sleep(THREAD_WAIT_TIME_OUT);	
	}		
	
	public static boolean selectFacilityDH(String DH) throws InterruptedException
	{
			driver.findElement(By.xpath(objPropertyReading.objectRepositoryPropertiesReading().getProperty("facilityinputdh"))).click();
			Thread.sleep(THREAD_WAIT_TIME_OUT);	
			Boolean dhboolean = driver.findElements(By.xpath(objPropertyReading.objectRepositoryPropertiesReading().getProperty("facilityselectdh1")+DH.trim()+objPropertyReading.objectRepositoryPropertiesReading().getProperty("facilityselectdh2"))).size()>0;
			if(dhboolean)
			{
				driver.findElement(By.xpath(objPropertyReading.objectRepositoryPropertiesReading().getProperty("facilityselectdh1")+DH.trim()+objPropertyReading.objectRepositoryPropertiesReading().getProperty("facilityselectdh2"))).click();
				Thread.sleep(THREAD_WAIT_TIME_OUT);	
				return true;
			}
			else
			{
				return false;
			}			
	}	
	
	public static boolean selectFacilityCHC(String CHC) throws InterruptedException
	{
			driver.findElement(By.xpath(objPropertyReading.objectRepositoryPropertiesReading().getProperty("facilityinputchc"))).click();
			Thread.sleep(THREAD_WAIT_TIME_OUT);	
			Boolean chcboolean = driver.findElements(By.xpath(objPropertyReading.objectRepositoryPropertiesReading().getProperty("facilityselectchc1")+CHC.trim()+objPropertyReading.objectRepositoryPropertiesReading().getProperty("facilityselectchc2"))).size()>0;
			if(chcboolean)
			{
				driver.findElement(By.xpath(objPropertyReading.objectRepositoryPropertiesReading().getProperty("facilityselectchc1")+CHC.trim()+objPropertyReading.objectRepositoryPropertiesReading().getProperty("facilityselectchc2"))).click();
				Thread.sleep(THREAD_WAIT_TIME_OUT);	
				return true;
			}
			else
			{
				return false;
			}	
	}	
	
	public static boolean selectFacilityPHC(String PHC) throws InterruptedException
	{
			driver.findElement(By.xpath(objPropertyReading.objectRepositoryPropertiesReading().getProperty("facilityinputphc"))).click();
			Thread.sleep(THREAD_WAIT_TIME_OUT);	
			Boolean phcboolean = driver.findElements(By.xpath(objPropertyReading.objectRepositoryPropertiesReading().getProperty("facilityselectphc1")+PHC.trim()+objPropertyReading.objectRepositoryPropertiesReading().getProperty("facilityselectphc2"))).size()>0;
			if(phcboolean)
			{
				driver.findElement(By.xpath(objPropertyReading.objectRepositoryPropertiesReading().getProperty("facilityselectphc1")+PHC.trim()+objPropertyReading.objectRepositoryPropertiesReading().getProperty("facilityselectphc2"))).click();
				Thread.sleep(THREAD_WAIT_TIME_OUT);	
				return true;
			}
			else
			{
				return false;
			}
	}	
	
	public static boolean selectFacilitySC(String SC) throws InterruptedException
	{
			driver.findElement(By.xpath(objPropertyReading.objectRepositoryPropertiesReading().getProperty("facilityinputsc"))).click();
			Thread.sleep(THREAD_WAIT_TIME_OUT);	
			Boolean scboolean = driver.findElements(By.xpath(objPropertyReading.objectRepositoryPropertiesReading().getProperty("facilityselectsc1")+SC.trim()+objPropertyReading.objectRepositoryPropertiesReading().getProperty("facilityselectsc2"))).size()>0;
			if(scboolean)
			{
				driver.findElement(By.xpath(objPropertyReading.objectRepositoryPropertiesReading().getProperty("facilityselectsc1")+SC.trim()+objPropertyReading.objectRepositoryPropertiesReading().getProperty("facilityselectsc2"))).click();
				Thread.sleep(THREAD_WAIT_TIME_OUT);	
				return true;
			}
			else
			{
				return false;
			}		
	}	
	
	public static boolean setFacilityName(String name) throws InterruptedException
	{
		if(name.isEmpty())
		{
			return false;
		}
		else
		{
			driver.findElement(By.xpath(objPropertyReading.objectRepositoryPropertiesReading().getProperty("facilityinputusername"))).sendKeys(name);
			Thread.sleep(THREAD_WAIT_TIME_OUT);	
			return true;			
		}
	
	}
	
	public static boolean selectFacilityLevel(String level) throws InterruptedException
	{
		driver.findElement(By.xpath(objPropertyReading.objectRepositoryPropertiesReading().getProperty("facilityinputlevel"))).click();
		Thread.sleep(THREAD_WAIT_TIME_OUT);	
		Boolean stateboolean = driver.findElements(By.xpath(objPropertyReading.objectRepositoryPropertiesReading().getProperty("facilityselectlevel1")+level.trim()+objPropertyReading.objectRepositoryPropertiesReading().getProperty("facilityselectlevel2"))).size()>0;
		if(stateboolean)
		{
			driver.findElement(By.xpath(objPropertyReading.objectRepositoryPropertiesReading().getProperty("facilityselectlevel1")+level.trim()+objPropertyReading.objectRepositoryPropertiesReading().getProperty("facilityselectlevel2"))).click();
			Thread.sleep(THREAD_WAIT_TIME_OUT);	
			return true;
		}
		else
		{
			return false;
		}			
	}
	
	public static boolean setFacilityStreet(String street) throws InterruptedException
	{
		if(street.isEmpty())
		{
			return false;
		}
		else
		{
			driver.findElement(By.xpath(objPropertyReading.objectRepositoryPropertiesReading().getProperty("facilityinputstreet"))).sendKeys(street);
			Thread.sleep(THREAD_WAIT_TIME_OUT);	
			return true;			
		}		
	}
	
	public static boolean selectFacilityState(String state) throws InterruptedException
	{
		if(state.isEmpty())
		{
			return true;			
		}
		else
		{
			driver.findElement(By.xpath(objPropertyReading.objectRepositoryPropertiesReading().getProperty("facilityinputstate"))).click();
			Thread.sleep(THREAD_WAIT_TIME_OUT);	
			Boolean stateboolean = driver.findElements(By.xpath(objPropertyReading.objectRepositoryPropertiesReading().getProperty("facilityselectstate1")+state.trim()+objPropertyReading.objectRepositoryPropertiesReading().getProperty("facilityselectstate2"))).size()>0;
			if(stateboolean)
			{
				driver.findElement(By.xpath(objPropertyReading.objectRepositoryPropertiesReading().getProperty("facilityselectstate1")+state.trim()+objPropertyReading.objectRepositoryPropertiesReading().getProperty("facilityselectstate2"))).click();
				Thread.sleep(THREAD_WAIT_TIME_OUT);	
				return true;
			}
			else
			{
				return false;
			}	
		}		
	}
	
	public static boolean selectFacilityDistrict(String district) throws InterruptedException
	{
		if(district.isEmpty())
		{
			return true;			
		}
		else
		{
			driver.findElement(By.xpath(objPropertyReading.objectRepositoryPropertiesReading().getProperty("facilityinputdistrict"))).click();
			Thread.sleep(THREAD_WAIT_TIME_OUT);	
			Boolean districtboolean = driver.findElements(By.xpath(objPropertyReading.objectRepositoryPropertiesReading().getProperty("facilityselectdistrict1")+district.trim()+objPropertyReading.objectRepositoryPropertiesReading().getProperty("facilityselectdistrict2"))).size()>0;
			if(districtboolean)
			{
				driver.findElement(By.xpath(objPropertyReading.objectRepositoryPropertiesReading().getProperty("facilityselectdistrict1")+district.trim()+objPropertyReading.objectRepositoryPropertiesReading().getProperty("facilityselectdistrict2"))).click();
				Thread.sleep(THREAD_WAIT_TIME_OUT);	
				return true;
			}
			else
			{
				return false;
			}		
		}				
	}
	
	public static boolean selectFacilityBlock(String block) throws InterruptedException
	{
		if(block.isEmpty())
		{
			return true;			
		}
		else
		{
			driver.findElement(By.xpath(objPropertyReading.objectRepositoryPropertiesReading().getProperty("facilityinputblock"))).click();
			Thread.sleep(THREAD_WAIT_TIME_OUT);	
			Boolean blockboolean = driver.findElements(By.xpath(objPropertyReading.objectRepositoryPropertiesReading().getProperty("facilityselectblock1")+block.trim()+objPropertyReading.objectRepositoryPropertiesReading().getProperty("facilityselectblock2"))).size()>0;
			if(blockboolean)
			{
				driver.findElement(By.xpath(objPropertyReading.objectRepositoryPropertiesReading().getProperty("facilityselectblock1")+block.trim()+objPropertyReading.objectRepositoryPropertiesReading().getProperty("facilityselectblock2"))).click();
				Thread.sleep(THREAD_WAIT_TIME_OUT);	
				return true;
			}
			else
			{
				return false;
			}		
		}
			
	}
	
	public static boolean selectFacilityVillage(String village) throws InterruptedException
	{
		if(village.isEmpty())
		{
			return true;
		}
		else
		{
			driver.findElement(By.xpath(objPropertyReading.objectRepositoryPropertiesReading().getProperty("facilityinputvillage"))).click();
			Thread.sleep(THREAD_WAIT_TIME_OUT);	
			Boolean villageboolean = driver.findElements(By.xpath(objPropertyReading.objectRepositoryPropertiesReading().getProperty("facilityselectvillage1")+village.trim()+objPropertyReading.objectRepositoryPropertiesReading().getProperty("facilityselectvillage2"))).size()>0;
			if(villageboolean)
			{
				driver.findElement(By.xpath(objPropertyReading.objectRepositoryPropertiesReading().getProperty("facilityselectvillage1")+village.trim()+objPropertyReading.objectRepositoryPropertiesReading().getProperty("facilityselectvillage2"))).click();
				Thread.sleep(THREAD_WAIT_TIME_OUT);	
				return true;
			}
			else
			{
				return false;
			}		
		}
	
	}
	
	public static boolean setFacilityPinCode(String pincode) throws InterruptedException
	{
		if(pincode.isEmpty())
		{
			return false;
		}
		else
		{
			driver.findElement(By.xpath(objPropertyReading.objectRepositoryPropertiesReading().getProperty("facilityinputpincode"))).sendKeys(pincode);
			Thread.sleep(THREAD_WAIT_TIME_OUT);	
			return true;			
		}		
	}
	
	public static boolean setFacilityLatitude(String latitude) throws InterruptedException
	{
		if(objDriverConfig.betweenIntRange(latitude, -90, 90))
		{
			driver.findElement(By.xpath(objPropertyReading.objectRepositoryPropertiesReading().getProperty("facilityinputlatitude"))).sendKeys(latitude);
			Thread.sleep(THREAD_WAIT_TIME_OUT);	
			return true;
		}
		else
		{
			return false;
		}				
	}
	
	public static boolean setFacilityLongitude(String longitude) throws InterruptedException
	{
		if(objDriverConfig.betweenIntRange(longitude, -180, 180))
		{
			driver.findElement(By.xpath(objPropertyReading.objectRepositoryPropertiesReading().getProperty("facilityinputlongitude"))).sendKeys(longitude);
			Thread.sleep(THREAD_WAIT_TIME_OUT);			
			return true;
		}
		else
		{
			return false;	
		}			
	}
	
	public static void clickAdd() throws InterruptedException
	{
		driver.findElement(By.xpath(objPropertyReading.objectRepositoryPropertiesReading().getProperty("faciltybtnadd"))).click();
		Thread.sleep(THREAD_WAIT_TIME_OUT);	
	}
	
	public static void killprocess()
	{
		driver=objAnchalAppFunctions.getDriver();
		objDriverConfig = new DriverConfig(driver);
		DriverConfig.killprocess(driver);
		/*
		 * driver.quit(); WindowsUtils.killByName("IEDriverServer.exe");
		 * WindowsUtils.killByName("iexplore.exe");
		 * WindowsUtils.killByName("chromedriver.exe");
		 * WindowsUtils.killByName("geckodriver.exe"); log.writeToDebugLog(debugger,
		 * "Killing the all process");
		 */
	}
	
	public static void refreshPage()
	{
		driver.navigate().refresh();
		log.writeToDebugLog(debugger, "Reloading the page for new set of record");
	}	
}
