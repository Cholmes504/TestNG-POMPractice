package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RegisterPage {

	WebDriver driver;
	
	public RegisterPage(WebDriver driver) {
		this.driver = driver;
	}
	
	private WebElement firstNameBox;
	private WebElement lastNameBox;
	private WebElement emailBox;
	private WebElement telephoneBox;
	private WebElement passwordBox;
	private WebElement passwordConfirmBox;
	private WebElement subscribeYesButton;
	private WebElement termsBox;
	private WebElement submitButton;
	
	
	public void enterFirstName(String firstNameText) {
		firstNameBox = driver.findElement(By.id("input-firstname"));
		firstNameBox.sendKeys(firstNameText);
	}
	
	public void enterLastName(String lastNameText) {
		lastNameBox = driver.findElement(By.id("input-lastname"));
		lastNameBox.sendKeys(lastNameText);
	}

	public void enterEmail(String emailText) {
		emailBox = driver.findElement(By.id("input-email"));
		emailBox.sendKeys(emailText);
	}
	
	public void enterPhoneNumber(String phoneNumberText) {
		telephoneBox = driver.findElement(By.id("input-telephone"));
		telephoneBox.sendKeys(phoneNumberText);
	}

	public void enterPassword(String passwordText) {
		passwordBox = driver.findElement(By.id("input-password"));
		passwordBox.sendKeys(passwordText);
	}
	
	public void confirmPassword(String passwordText) {
		passwordConfirmBox = driver.findElement(By.id("input-confirm"));
		passwordConfirmBox.sendKeys(passwordText);
	}

	public void yesSubscribe() {
		subscribeYesButton = driver.findElement(By.xpath("//input[@name='newsletter'][@value='1']"));
		subscribeYesButton.click();
	}
	
	public void agreeToTerms() {
		termsBox = driver.findElement(By.xpath("//input[@name='agree'][@value='1']"));
		termsBox.click();
	}
	
	public  WebDriver clickSubmit() {
		submitButton = driver.findElement(By.xpath("//input[@type='submit']"));
		submitButton.click();
		return driver;
	}
}
