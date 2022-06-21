package Test;

import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import POJO.Browser;
import POM.ZerodhaLoginPage;
import POM.ZerodhaPinPage;
import Utility.Parametrization;

@Listeners(TestListeners.class)
public class PinPageTest {
	WebDriver driver;
	
  @BeforeMethod
  public void launchBrowser() {
	  driver =Browser.openBrowser();
  }
  
  @Test
  public void loginToZerodhaWithPinTest() throws EncryptedDocumentException, IOException, InterruptedException {
	  ZerodhaLoginPage zerodhaLoginPage = new ZerodhaLoginPage(driver);
	  String userName =Parametrization.getData(0, 1);
	  String password = Parametrization.getData(1, 1);
	  zerodhaLoginPage.enterUserID(userName);
	  zerodhaLoginPage.enterPassword(password);
	  zerodhaLoginPage.clickOnLogin();
	  ZerodhaPinPage zerodhaPinPage = new ZerodhaPinPage(driver);
	  String pin = Parametrization.getData(2, 1);
	  System.out.println(pin);
	  zerodhaPinPage.enterPin(pin,driver);
	  zerodhaPinPage.clickOnContinue();
	  String homepage = driver.getTitle();
	  String requiredTitle ="Dashboard / Kite";
	  Assert.assertEquals(homepage,requiredTitle);
	  
		
		
	  
  }
}
