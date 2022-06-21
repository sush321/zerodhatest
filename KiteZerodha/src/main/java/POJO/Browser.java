package POJO;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Browser {
	
	public static WebDriver openBrowser()  {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\91877\\OneDrive\\Documents\\Software\\Driver Update\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();//upcasting Chrome driver class to WebDriver
		driver.get("https://kite.zerodha.com/");//method of WebDriver interface
		driver.manage().window().maximize();
	//	driver.manage().timeouts().implicitlyWait(10000,TimeUnit.MILLISECONDS);//universal wait applicable for every instance of WebDriver
		return driver;
	}

}
