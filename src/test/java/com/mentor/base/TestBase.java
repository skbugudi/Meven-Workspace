package com.mentor.base;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;


public class TestBase {

	/*
	 * Initialize following
	 * 
	 * WebDriver Properties Logs Extent Reports DB Excel Mail
	 */

	public static WebDriver driver;
	public static Properties config = new Properties();
	public static Properties or = new Properties();
	public static FileInputStream fis;
	public static Logger log = Logger.getLogger("devpinoylogger");

	@BeforeSuite
	public void setUp() throws InterruptedException {
		if (driver == null) {
			try {

				fis = new FileInputStream(
						System.getProperty("user.dir") + "/src/test/resources/properties/Config.properties");

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				config.load(fis);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				fis = new FileInputStream(
						System.getProperty("user.dir") + "/src/test/resources/properties/OR.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				or.load(fis);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			if (config.getProperty("Browser").equals("chrome")) {

				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir") + "/src/test/resources/executables/chromedriver");

				driver = new ChromeDriver();
				log.debug("Chrome Launched...");

			} else if (config.getProperty("Browser").equals("Firefox")) {
				System.setProperty("webdriver.gecko.driver",
						System.getProperty("user.dir") + "/src/test/resources/executables/geckodriver");
				driver = new FirefoxDriver();

			} else if (config.getProperty("Browser").equals("iexplorer")) {
				System.setProperty("webdriver.ie.driver",
						System.getProperty("user.dir") + "/src/test/resources/executables/IEDriverServer.exe");
				driver = new InternetExplorerDriver();
			}

			driver.get(config.getProperty("testurl"));
			log.debug("Navigated to the base URL...");
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicit.wait")),
					TimeUnit.SECONDS);
		}

	}

	@AfterSuite
	public void tearDown() {
		if (driver != null) {
			driver.quit();
			log.debug("Driver quitting...");
		}

	}
	
	
	/* Function to verify whether given element is present or not */
	
	public boolean isElementPresent(By by) {
		
		try {
		driver.findElement(by);
		return true;
		}
		
		catch(NoSuchElementException e) {
//			e.printStackTrace();
			return false;
		}
	}
	
	

}
