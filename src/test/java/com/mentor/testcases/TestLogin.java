package com.mentor.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.By;

import com.mentor.base.TestBase;

public class TestLogin extends TestBase{
	
	@Test
	public void testLogin() throws InterruptedException {
		
		System.out.println("Hello TestNG");
		driver.findElement(By.cssSelector(or.getProperty("manager"))).click();
		Thread.sleep(3000);
		Assert.assertTrue(isElementPresent(By.cssSelector(or.getProperty("addCustomer"))), "Login failed as no such element present in the context::: ");
		log.debug("Login successfull...");
	}

}
