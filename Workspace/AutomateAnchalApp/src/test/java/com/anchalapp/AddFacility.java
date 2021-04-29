package com.anchalapp;

import static common.Constants.THREAD_WAIT_TIME_OUT;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class AddFacility 
{

	static WebDriver driver;
	static String baseUrl = "https://anchalapp.com/login";
	
	public static void setup(String baseUrl)
	{
		System.setProperty("webdriver.chrome.driver","driverexe\\chromedriver.exe");
		driver = new ChromeDriver();	
		driver.get(baseUrl);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	public static void authentication(String username, String password) throws InterruptedException
	{
		driver.findElement(By.xpath("//div[@class='mat-form-field-infix']/input[@name='email']")).sendKeys(username);
		driver.findElement(By.xpath("//div[@class='mat-form-field-infix']/input[@name='password']")).sendKeys(password);
		driver.findElement(By.xpath("//span[@class='right-float']/button/span[text()='Log In']")).click();
		Thread.sleep(3000);
	}
	
	public static void navigateToURL(String navigateurl) throws InterruptedException
	{
			driver.navigate().to(navigateurl);
			Thread.sleep(THREAD_WAIT_TIME_OUT);	
	}	
	
	public static void selectFacilityDH(String DH) throws InterruptedException
	{
			driver.findElement(By.xpath("//mat-select[@name='dh']")).click();
			//mat-option/span[contains(text(),'dh pune')]
			Thread.sleep(THREAD_WAIT_TIME_OUT);	
			driver.findElement(By.xpath("//mat-option/span[contains(text(),'"+DH.trim()+"')]")).click();
			Thread.sleep(THREAD_WAIT_TIME_OUT);	
	}	
	
	public static void selectFacilityCHC(String CHC) throws InterruptedException
	{
			driver.findElement(By.xpath("//mat-select[@name='chc']")).click();
			//mat-option/span[contains(text(),'dh pune')]
			Thread.sleep(THREAD_WAIT_TIME_OUT);	
			driver.findElement(By.xpath("//mat-option/span[contains(text(),'"+CHC.trim()+"')]")).click();
			Thread.sleep(THREAD_WAIT_TIME_OUT);	
	}	
	
	public static void selectFacilityPHC(String PHC) throws InterruptedException
	{
			driver.findElement(By.xpath("//mat-select[@name='phc']")).click();
			//mat-option/span[contains(text(),'dh pune')]
			Thread.sleep(THREAD_WAIT_TIME_OUT);	
			driver.findElement(By.xpath("//mat-option/span[contains(text(),'"+PHC.trim()+"')]")).click();
			Thread.sleep(THREAD_WAIT_TIME_OUT);	
	}	
	
	public static void selectFacilitySC(String SC) throws InterruptedException
	{
			driver.findElement(By.xpath("//mat-select[@name='sc']")).click();
			//mat-option/span[contains(text(),'dh pune')]
			Thread.sleep(THREAD_WAIT_TIME_OUT);	
			driver.findElement(By.xpath("//mat-option/span[contains(text(),'"+SC.trim()+"')]")).click();
			Thread.sleep(THREAD_WAIT_TIME_OUT);	
	}	
	
	public static void setFacilityName(String Name) throws InterruptedException
	{
		driver.findElement(By.xpath("//*[@name='name']")).sendKeys(Name);
		//mat-option/span[contains(text(),'dh pune')]
		Thread.sleep(THREAD_WAIT_TIME_OUT);	
	}
	
	public static void selectFacilityLevel(String level) throws InterruptedException
	{
		driver.findElement(By.xpath("//*[@name='level']")).click();	
		Thread.sleep(THREAD_WAIT_TIME_OUT);	
		driver.findElement(By.xpath("//mat-option/span[contains(text(),'"+level.trim()+"')]")).click();
		Thread.sleep(THREAD_WAIT_TIME_OUT);	
	}
	
	public static void setFacilityStreet(String street) throws InterruptedException
	{
		driver.findElement(By.xpath("//*[@name='street']")).sendKeys(street);
		Thread.sleep(THREAD_WAIT_TIME_OUT);	
	}
	
	public static void selectFacilityState(String state) throws InterruptedException
	{
		driver.findElement(By.xpath("//*[@name='state']")).click();
		Thread.sleep(THREAD_WAIT_TIME_OUT);	
		driver.findElement(By.xpath("//mat-option/span[contains(text(),'"+state.trim()+"')]")).click();
		Thread.sleep(THREAD_WAIT_TIME_OUT);	
	}
	
	public static void selectFacilityDistrict(String district) throws InterruptedException
	{
		driver.findElement(By.xpath("//*[@name='district']")).click();
		Thread.sleep(THREAD_WAIT_TIME_OUT);	
		driver.findElement(By.xpath("//mat-option/span[contains(text(),'"+district.trim()+"')]")).click();
		Thread.sleep(THREAD_WAIT_TIME_OUT);	
	}
	
	public static void selectFacilityBlock(String block) throws InterruptedException
	{
		driver.findElement(By.xpath("//*[@name='block']")).click();
		Thread.sleep(THREAD_WAIT_TIME_OUT);	
		driver.findElement(By.xpath("//mat-option/span[contains(text(),'"+block.trim()+"')]")).click();
		Thread.sleep(THREAD_WAIT_TIME_OUT);			
	}
	
	public static void selectFacilityVillage(String village) throws InterruptedException
	{
		driver.findElement(By.xpath("//*[@name='village']")).click();
		Thread.sleep(THREAD_WAIT_TIME_OUT);	
		driver.findElement(By.xpath("//mat-option/span[contains(text(),'"+village.trim()+"')]")).click();
		Thread.sleep(THREAD_WAIT_TIME_OUT);	
	}
	
	public static void setFacilityPinCode(String pincode) throws InterruptedException
	{
		driver.findElement(By.xpath("//*[@name='pin']")).sendKeys(pincode);
		Thread.sleep(THREAD_WAIT_TIME_OUT);			
	}
	
	public static void setFacilityLatitude(String latitude) throws InterruptedException
	{
		driver.findElement(By.xpath("//*[@name='latitude']")).sendKeys(latitude);
		Thread.sleep(THREAD_WAIT_TIME_OUT);			
	}
	
	public static void setFacilityLongitude(String longitude) throws InterruptedException
	{
		driver.findElement(By.xpath("//*[@name='longitude']")).sendKeys(longitude);
		Thread.sleep(THREAD_WAIT_TIME_OUT);
	}
	
	public static void addDH(String DHName, String Name, String level, String street, String state, String district, String block, String village, String pincode, String latitude, String longitude ) throws InterruptedException
	{
				selectFacilityDH(DHName);
				setFacilityName(Name);
				selectFacilityLevel(level);
				setFacilityStreet(street);
				selectFacilityState(state);
				selectFacilityDistrict(district);
				selectFacilityBlock(block);
				selectFacilityVillage(village);
				setFacilityPinCode(pincode);
				setFacilityLatitude(latitude);
				setFacilityLongitude(longitude);		
	}
	
	/*
	
	
	public static void addCHC(String DHName,String CHCName, String Name, String level, String street, String state, String district, String block, String village, String pincode, String latitude, String longitude ) throws InterruptedException
	{
				selectFacilityDH(DHName);
				selectFacilityCHC(CHCName);
				setName(Name);
				selectLevel(level);
				setStreet(street);
				selectState(state);
				selectDistrict(district);
				selectBlock(block);
				selectVillage(village);
				setPinCode(pincode);
				setLatitude(latitude);
				setLongitude(longitude);		
	}
	
	public static void addPHC(String DHName,String CHCName,String PHCName, String Name, String level, String street, String state, String district, String block, String village, String pincode, String latitude, String longitude ) throws InterruptedException
	{
				selectFacilityDH(DHName);
				selectFacilityCHC(CHCName);
				selectPHC(PHCName);
				setName(Name);
				selectLevel(level);
				setStreet(street);
				selectState(state);
				selectDistrict(district);
				selectBlock(block);
				selectVillage(village);
				setPinCode(pincode);
				setLatitude(latitude);
				setLongitude(longitude);		
	}
	
	public static void addSC(String DHName,String CHCName,String PHCName,String SCName, String Name, String level, String street, String state, String district, String block, String village, String pincode, String latitude, String longitude ) throws InterruptedException
	{
				selectFacilityDH(DHName);
				selectFacilityCHC(CHCName);
				selectPHC(PHCName);
				selectSC(SCName);
				setName(Name);
				selectLevel(level);
				setStreet(street);
				selectState(state);
				selectDistrict(district);
				selectBlock(block);
				selectVillage(village);
				setPinCode(pincode);
				setLatitude(latitude);
				setLongitude(longitude);		
	}
	
	*/
	
	public static void clickAdd() throws InterruptedException
	{
		driver.findElement(By.xpath("//button/span[text()='Add']")).click();
		Thread.sleep(THREAD_WAIT_TIME_OUT);	
		driver.quit();
	}
	
	public static void main(String a[]) throws InterruptedException
	{
		setup(baseUrl);
		authentication("9871471717","Anchal@333");
		navigateToURL("https://anchalapp.com/admin/facilities");
		//selectDH("Add DH / SDH"); //dh pune //Add DH / SDH	
		//addDH("Add DH / SDH","Test1", "Level 1", "Test 123", "maharashtra", "pune", "ambegaon", "Sultanpur", "40012", "50", "100");
		
		//addCHC("dh pune","Add CHC","Test1", "Level 1", "Test 123", "maharashtra", "pune", "ambegaon", "Sultanpur", "40012", "50", "100");
		//addPHC("dh pune","chc khed","Add PHC","Test1", "Level 1", "Test 123", "maharashtra", "pune", "ambegaon", "Sultanpur", "40012", "50", "100");
		//addSC("dh pune","chc khed","Pait","Add SC","Test1", "Level 1", "Test 123", "maharashtra", "pune", "ambegaon", "Sultanpur", "40012", "50", "100");
		clickAdd();		
	}	
}
