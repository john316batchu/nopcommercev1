package com.nopcommerce.tests;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.*;

import com.nopcommerce.base.BaseClass;
import com.nopcommerce.pages.AddNewCustomerPage;
import com.nopcommerce.pages.LoginPage;
import com.nopcommerce.utility.Util;

public class AddNewCustomerTest extends BaseClass{
	LoginPage lp;
	AddNewCustomerPage ncp;
	

	AddNewCustomerTest(){
		super();
	}
	@BeforeMethod
	public void setUp() {
		initialise();
		lp=new LoginPage();
		logger.info("logging into the application");
		lp.login(prop.getProperty("username"), prop.getProperty("password"));
		ncp=new AddNewCustomerPage();
	}
	@Test
	public void newCustomerTest() throws IOException, InterruptedException {
		logger.info("clicking on CustomersMenu link");
		ncp.clickCustomersMenu();
		logger.info("Clicking on Customers link");
		ncp.clickCustomers();
		logger.info("Clicking on AddNew button");
		ncp.clickAddnew();
		logger.info("Adding new Customer Details");
		String mail=Util.RandomString()+"@gmail.com";
		ncp.addCustomerDetails(mail,"test" ,"venkat", "batchu","Male","TestComp","Guests", "Vendor 1");
		String text=driver.findElement(By.tagName("body")).getText();
		if(text.contains("The new customer has been added successfully.")) {
			Assert.assertTrue(true);
			logger.info("Add new Customer passed");
			logger.info("logging out from application");
			Thread.sleep(2000);
			driver.findElement(By.xpath("//a[normalize-space()='Logout']")).click();
		}else {
			Util.takeScreenshot(driver, "AddNewCustomerTest");
			logger.info("Add new Customer failed");
			Assert.assertTrue(false);
			
		}
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	

}
