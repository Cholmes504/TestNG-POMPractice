package testPackage;

import java.time.Duration;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;
import java.lang.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;

public class Login {

	WebDriver driver = null;
	Properties prop = null;
	HomePage homePage;
	LoginPage loginPage;

	@BeforeMethod
	public void setup() throws FileNotFoundException, IOException {

		prop = new Properties();
		prop.load(new FileInputStream(new File("src/test/java/properties/ProjectData.properties")));

		String browserName = prop.getProperty("browser");

		if (browserName.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		} else if (browserName.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		} else if (browserName.equalsIgnoreCase("safari")) {
			driver = new SafariDriver();
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get(prop.getProperty("url"));

		homePage = new HomePage(driver);
		homePage.clickOnMyAccountDropMenu();
		driver = homePage.clickLoginOption();

	}

	@AfterMethod
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}

	@Test (priority = 1)
	public void loginWithValidCredentials() {

		loginPage = new LoginPage(driver);
		loginPage.enterLoginEmailAddress(prop.getProperty("validemail"));
		loginPage.enterLoginPassword(prop.getProperty("validpassword"));
		driver = loginPage.clickOnLoginButton();
		MyAccountPage myAccountPage = new MyAccountPage(driver);

		Assert.assertTrue(myAccountPage.loggedInStatus());

	}

	@Test (priority = 2)
	public void loginWithInvalidCredentials() {
		
		loginPage = new LoginPage(driver);
		loginPage.enterLoginEmailAddress(prop.getProperty("invalidemail"));
		loginPage.enterLoginPassword(prop.getProperty("invalidpassword"));
		driver = loginPage.clickOnLoginButton();
		
		String expectedWarning = "Warning: No match for E-Mail Address and/or Password.";
		String actualWarning = loginPage.warningMessageDisplayed();
		Assert.assertEquals(actualWarning, expectedWarning);
	}
	
}
