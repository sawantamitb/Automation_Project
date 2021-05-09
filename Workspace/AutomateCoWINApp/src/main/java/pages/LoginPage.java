package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import base.TestBase;
import io.qameta.allure.Step;

import static base.Constants.*;
public class LoginPage extends TestBase
{
	
	//Page Factory - OR:
	By mobileinput = By.xpath("//*[@id='mat-input-0']");
	By otpInput = By.id("mat-input-1");
	By submitButton = By.xpath("//ion-button[@type='button']");
	
	//Initializing the Page Objects:
	public LoginPage()
	{
		
	}
	
	@Step("Verify the page title")
	public String verifyTitle() throws InterruptedException
	{
		return getDriver().getTitle();
	}
	
	@Step("Login with mobile number : {0} and OTP : {1} and click")
	public HomePage login(String un, String pwd) throws InterruptedException
	{
		getDriver().findElement(mobileinput).sendKeys(un);
		 Thread.sleep(THREAD_WAIT_TIME_OUT);
		 getDriver().findElement(submitButton).click();
		 Thread.sleep(THREAD_WAIT_TIME_OUT);
		 
		 getDriver().findElement(otpInput).sendKeys(pwd);
		 
		 getDriver().findElement(submitButton).click();
		 Thread.sleep(THREAD_WAIT_TIME_OUT);
		 
//		 JavascriptExecutor js = (JavascriptExecutor)driver;
//		 js.executeScript("arguments[0].click();", submitButton);
//		 
		 return new HomePage();
	}	
}