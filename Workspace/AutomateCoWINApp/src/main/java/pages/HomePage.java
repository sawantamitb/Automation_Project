package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import base.TestBase;
import static base.Constants.*;


public class HomePage extends TestBase 
{

	By logout = By.xpath("//li[text()=' Logout ']");
	
	// Initializing the Page Objects:
	public HomePage() 
	{
		
	}
	
	public void clickOnLogout() throws InterruptedException
	{
		getDriver().findElement(logout).click();
		Thread.sleep(THREAD_WAIT_TIME_OUT);
	
	}
	
}