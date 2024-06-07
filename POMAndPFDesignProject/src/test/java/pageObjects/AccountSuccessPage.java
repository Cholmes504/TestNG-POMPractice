package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AccountSuccessPage {

	WebDriver driver;
	
	public AccountSuccessPage(WebDriver driver) {
		this.driver = driver;
	}
	

	private WebElement accountSuccessHeading;
	
	public String retrieveSuccessPageHeading() {
		accountSuccessHeading = driver.findElement(By.xpath("//div[@id='content']/h1"));
		return accountSuccessHeading.getText();
	}


}
