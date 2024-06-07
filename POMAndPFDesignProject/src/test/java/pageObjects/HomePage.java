package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {

	WebDriver driver;
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
	}
	
	private WebElement myAccountDropMenu; 
	private WebElement loginOption;
	private WebElement registerOption;
	
	
	public void clickOnMyAccountDropMenu () {
		myAccountDropMenu = driver.findElement(By.xpath("//span[text()='My Account']"));
		myAccountDropMenu.click();
	}
	
	public WebDriver clickLoginOption() {
		loginOption = driver.findElement(By.linkText("Login"));
		loginOption.click();
		return driver;
	}

	public WebDriver clickRegisterOption() {
		registerOption = driver.findElement(By.linkText("Register"));
		registerOption.click();
		return driver;
	}

}



