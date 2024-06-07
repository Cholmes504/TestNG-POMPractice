package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MyAccountPage {

	WebDriver driver;
	
	public MyAccountPage(WebDriver driver) {
		this.driver =  driver;
	}
	
	private WebElement editYourAccountInformation;
	
	public boolean loggedInStatus() {
		WebElement editYourAccountInformation = driver.findElement(By.linkText("Edit your account information"));
		return editYourAccountInformation.isDisplayed();
	}
	
}
