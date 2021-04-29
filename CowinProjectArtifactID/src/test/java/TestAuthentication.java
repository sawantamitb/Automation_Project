import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.JavascriptExecutor;


public class TestAuthentication 
{
	static WebDriver driver;
	static String baseUrl = "https://selfregistration.sit.co-vin.in/";
	static Boolean register=false;
	
	public static WebElement expand_shadow_element(WebElement element)
    {
        WebElement shadow_root = (WebElement)((JavascriptExecutor)driver).executeScript("return arguments[0].shadowRoot", element);
        return shadow_root;
    }

	
	public static void schedule() throws InterruptedException
	{
		driver.findElement(By.xpath("//ion-button[contains(.,'Schedule Now')]")).click();
		Thread.sleep(3000);			
	}
			
	public static void searchcenterlist()
	{
		String text2 = driver.findElement(By.xpath("//h3[contains(.,' Book Appointment for Vaccination')]")).getText();
		driver.findElement(By.xpath("//div/div/input[@appinputchar='pincode']")).sendKeys("403001");
		driver.findElement(By.xpath("//ion-button[contains(.,'Search')]")).click();				
	}
		
	public static void schedule_session()
	{
		int rows = driver.findElements(By.xpath("//div/mat-selection-list/div")).size();
		int col = 7;
		
		for(int i=1; i<=rows+1;i++)
		{
			if(driver.findElement(By.xpath("//mat-selection-list/div["+i+"]/mat-list-option//h5")).getText().equalsIgnoreCase("Mark Hospital Panaji"))
			{
				int rowindex = i;
				try
				{
					driver.findElement(By.xpath("//div["+rowindex+"]/mat-list-option/div/div[2]/ion-row/ion-col[2]/ul/li[1]/div/a")).click();
					break;
				}
				catch(Exception e)
				{
					System.out.println("There is some error in appointment link. Please check session for today."+e);
				}					
			}
			else
			{
				System.out.println("There is no center with the name");
			}
		}
	}
		
	public static void reschedule_session() throws InterruptedException
	{
		//reschedule
		driver.findElement(By.xpath("//a[contains(text(),'Reschedule')]")).click();
		Thread.sleep(3000);
		searchcenterlist();
		int rows = driver.findElements(By.xpath("//div/mat-selection-list/div")).size();
		int col = 7;
		
		for(int i=1; i<=rows+1;i++)
		{
			if(driver.findElement(By.xpath("//mat-selection-list/div["+i+"]/mat-list-option//h5")).getText().equalsIgnoreCase("Mark Hospital Panaji"))
			{
				int rowindex = i;
				try
				{
					driver.findElement(By.xpath("//div["+rowindex+"]/mat-list-option/div/div[2]/ion-row/ion-col[2]/ul/li[2]/div/a")).click();
					break;
				}
				catch(Exception e)
				{
					System.out.println("There is some error in appointment link. Please check session for today."+e);
				}					
			}
			else
			{
				System.out.println("There is no center with the name");
			}
		}
		appointmentconfirmation();
	}
	
	
	public static void appointmentconfirmation() throws InterruptedException
	{
		driver.findElement(By.xpath("//h3[contains(.,' Appointment Confirmation')]")).getText().equalsIgnoreCase("Appointment Confirmation");
		
		driver.findElement(By.xpath("//ion-col[2]/div/ion-button[1]")).click();
		driver.findElement(By.xpath("//ion-button[@type='submit']")).click();
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("//ion-row[2]/ion-col/p[1]")).getText().equalsIgnoreCase("Your vaccination appointment is confirmed.");
		
		// download pdf
		//driver.findElement(By.xpath("//div/span/span")).click();
		
		driver.findElement(By.xpath("(//ion-button[@type='submit'])[2]")).click();
		Thread.sleep(3000);
		
		//h3[contains(.,' Appointment Successful')]
	}
	
	
	public static void main(String a[]) throws InterruptedException
	{
		System.setProperty("webdriver.chrome.driver","driverexe\\chromedriver.exe");
		driver = new ChromeDriver();	
		driver.get(baseUrl);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.findElement(By.xpath("//*[@id='mat-input-0']")).sendKeys("9090000023");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//ion-button[@type='button']")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("mat-input-1")).sendKeys("100000");
		driver.findElement(By.xpath("//ion-button[@type='button']")).click();		
		Thread.sleep(3000);
		
		if(driver.findElements(By.xpath("//h3[contains(text(),'Register for Vaccination')]")).size()>0==true)
		{
	
			driver.findElement(By.xpath("//h3[contains(text(),'Register for Vaccination')]")).getText().equalsIgnoreCase("Register for Vaccination");
			
			driver.findElement(By.xpath("//*[@id='mat-select-0']")).click();
			Thread.sleep(3000); 
			driver.findElement(By.xpath("//*[@id='mat-select-0-panel']//mat-option[@id='mat-option-3']")).click();
			Thread.sleep(3000);
			 
			driver.findElement(By.xpath("//mat-form-field//input[@appinputchar='passportnumber']")).sendKeys("N1234567");
			//Thread.sleep(3000);
			
			driver.findElement(By.xpath("//mat-form-field//input[@appinputchar='username']")).sendKeys("Test Passport Male");
			//Thread.sleep(3000);
			
			driver.findElement(By.xpath("//span[contains(.,'Male')]")).click();
			//Thread.sleep(3000);
			
			driver.findElement(By.xpath("//mat-form-field//input[@formcontrolname='birth_year']")).sendKeys("1976");
			//Thread.sleep(3000);
			
			driver.findElement(By.xpath("//*[@id='main-content']/app-registration-form//ion-button")).click();
			Thread.sleep(3000);
			
			
			
			/*
			 * driver.findElement(By.xpath(
			 * "//*[@id='main-content']/app-registration-form//ion-button")).click();
			 * WebElement root1 = driver.findElement(By.className("md error hydrated"));
			 * WebElement shadow_root1 = expand_shadow_element(root1); String text =
			 * shadow_root1.getText(); System.out.println(text);
			 * 
			 * //
			 * equalsIgnoreCase("There are one or more error. Please fill all the fields with valid information"
			 * ); Thread.sleep(3000);
			 */
		    
		    driver.findElement(By.xpath("//ul[@class='navigation']/li[text()=' Logout ']")).click();
			Thread.sleep(3000);
			driver.quit();
		}
		else 
		{
			//if(driver.findElements(By.xpath("//h3[contains(text(),'Account Details')]")).size()>0==true)
			driver.findElement(By.xpath("//h3[contains(text(),'Account Details')]")).getText().equalsIgnoreCase("Account Details");
			//Thread.sleep(3000);
			
			driver.findElement((By.xpath("//a[contains(text(),'Schedule')]"))).click();
			String text1 = driver.findElement(By.xpath("//div/h5")).getText();
			
			
			
			schedule();
			searchcenterlist();
			schedule_session();
			appointmentconfirmation();
			reschedule_session();
			
			//cancel apointment
			driver.findElement(By.xpath("//ion-icon[@name='close-outline']")).click();
			driver.findElement(By.xpath("//button[contains(.,'Yes')]")).click();
			Thread.sleep(3000);
			
			//delete apointment
			driver.findElement((By.xpath("//ion-icon[@name='trash-outline']"))).click();
			driver.findElement(By.xpath("//div[@aria-labelledby='swal2-title']/div[3]//button[contains(text(),'Yes')]")).click(); 
			Thread.sleep(3000);
			
			driver.findElement(By.xpath("//ul[@class='navigation']/li[text()=' Logout ']")).click();
			Thread.sleep(3000);
			driver.quit();
		}		
	}

}
