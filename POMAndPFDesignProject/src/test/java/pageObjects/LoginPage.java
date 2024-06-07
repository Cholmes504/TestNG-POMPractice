package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {

	WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}
	
	private WebElement emailField; 
	private WebElement passwordField;
	private WebElement loginButton;
	private WebElement warningMessage;
	
	public void enterLoginEmailAddress(String emailText) {
		emailField = driver.findElement(By.id("input-email"));
		emailField.sendKeys(emailText);
	}

	public void enterLoginPassword (String passwordText) {
		passwordField = driver.findElement(By.id("input-password"));
		passwordField.sendKeys(passwordText);
	}
	
	public WebDriver clickOnLoginButton() {
		loginButton = driver.findElement(By.xpath("//input[@value='Login']"));
		loginButton.click();
		return driver;
	}

	public String warningMessageDisplayed() {
		warningMessage = driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']"));	
		return warningMessage.getText();
	}
}

