package testPackage;

import java.time.Duration;
import java.util.Date;
import java.util.Properties;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import pageObjects.AccountSuccessPage;
import pageObjects.HomePage;
import pageObjects.RegisterPage;

public class Register {
	
	WebDriver driver = null;//makes driver local
	Properties properties = null;//makes property file local
	HomePage homePage;
	RegisterPage registerPage;
	
	
	@BeforeMethod
	public void setup() throws FileNotFoundException, IOException {
		
		properties = new Properties();
		properties.load(new FileInputStream(new File("src/test/java/properties/ProjectData.properties")));
		
		
		
		String browserName = properties.getProperty("browser");

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
		driver.get(properties.getProperty("url"));
		
		HomePage homePage = new HomePage(driver);
		
		homePage.clickOnMyAccountDropMenu();
		driver = homePage.clickRegisterOption();
		
	}
	
	@AfterMethod
	public void tearDown() {
		if(driver!=null) {
			driver.quit();
		}
	}
	@Test
	public void registerWithAllFields() {
	
		RegisterPage registerPage = new RegisterPage(driver);
		
		registerPage.enterFirstName(properties.getProperty("firstname"));
		registerPage.enterLastName(properties.getProperty("lastname"));
		registerPage.enterEmail(generateNewEmail());
		registerPage.enterPhoneNumber(properties.getProperty("phonenumber"));
		registerPage.enterPassword(properties.getProperty("validpassword"));
		registerPage.confirmPassword(properties.getProperty("validpassword"));
		registerPage.yesSubscribe();
		registerPage.agreeToTerms();
		driver = registerPage.clickSubmit();
		
		AccountSuccessPage accountSuccess = new AccountSuccessPage(driver);
		
		String expectedPageHeading = "Your Account Has Been Created!";
		String actualPageHeading = accountSuccess.retrieveSuccessPageHeading();
		
		Assert.assertEquals(actualPageHeading, expectedPageHeading);
	}

	public String generateNewEmail() {
		
		Date date = new Date();
		return "christian" + date.toString().replace(" ", "_").replace(":", "_")+"@gmail.com";
	}
}
