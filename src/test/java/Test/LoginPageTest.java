package Test;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import POJO.Browser;
import POM.ZerodhaLoginPage;
import Utility.Parametrization;
import Utility.Report;


@Listeners(TestListeners.class)

public class LoginPageTest extends BaseTest {
	ExtentReports reports;
	ExtentTest test;
	
	@BeforeTest
	public void extentReports() {
		reports = Report.createReport();
	}
	
	@BeforeMethod
	public void browser() {
		 driver =Browser.openBrowser();
	}
	
	@Test
	public void loginWithValidCredentialsTest() throws EncryptedDocumentException, IOException {
		test =reports.createTest("loginWithValidCredentialsTest");
		ZerodhaLoginPage zerodhaLoginPage = new ZerodhaLoginPage(driver);
		String userName =Parametrization.getData(0, 1);
		String password = Parametrization.getData(1, 1);
		zerodhaLoginPage.enterUserID(userName);
		zerodhaLoginPage.enterPassword(password);
		zerodhaLoginPage.clickOnLogin();
	}
	
	@Test
	public void forgotPasswordLinkTest() {
		test =reports.createTest("forgotPasswordLinkTest");
		ZerodhaLoginPage zerodhaLoginPage = new ZerodhaLoginPage(driver);
		zerodhaLoginPage.clickOnForgotPassword();
	}
	
	@Test (dependsOnMethods = {"clickOnLoginWithoutDataTest"})
	public void signupLinkTest() {
		test =reports.createTest("signupLinkTest");
		ZerodhaLoginPage zerodhaLoginPage = new ZerodhaLoginPage(driver);
		zerodhaLoginPage.clickOnSignUp();
	}
	
	@Test
	public void clickOnLoginWithoutDataTest() throws EncryptedDocumentException, IOException {
		test =reports.createTest("clickOnLoginWithoutDataTest");
		ZerodhaLoginPage zerodhaLoginPage = new ZerodhaLoginPage(driver);
		zerodhaLoginPage.clickOnLogin();
		String text =zerodhaLoginPage.getErrorText();
		
		String expectedText = "User ID should be minimum 6";
		
	//Assert.assertEquals(text,expectedText);//Hard Assert
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(text, expectedText);
		
		String userName =Parametrization.getData(0, 1);
		String password = Parametrization.getData(1, 1);
		zerodhaLoginPage.enterUserID(userName);
		zerodhaLoginPage.enterPassword(password);
		zerodhaLoginPage.clickOnLogin();
		
		softAssert.assertAll();
		
		
	}
	
	@AfterMethod
	public void closeBrowser(ITestResult result) {

		if(result.getStatus() == ITestResult.FAILURE) {
			test.log(Status.FAIL, result.getName());
		} else if(result.getStatus() == ITestResult.SUCCESS) {
			test.log(Status.PASS, result.getName());
		}
		else {
			test.log(Status.SKIP,result.getName());
		}
	}
	
	@AfterTest
	public void flushResult() {
		reports.flush();
	}
	
}
