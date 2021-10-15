package com.nopcommerce.tests;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.nopcommerce.base.BaseClass;
import com.nopcommerce.pages.LoginPage;
import com.nopcommerce.utility.Excel_Utility;
import com.nopcommerce.utility.Util;

public class LoginTests extends BaseClass {
	
	LoginPage lp;
	String filepath=System.getProperty("user.dir")+"/src/test/java/com/nopcommerce/data/admin-nopcommerce.xlsx";
	String sheet="Sheet1";
	LoginTests(){
		super();
	}
	@BeforeMethod
	public void setUp() {
		initialise();
		lp=new LoginPage();
	}
	@Test
	public void loginTest() throws IOException {
		logger.info("Logging into the application");
		lp.login(prop.getProperty("username"), prop.getProperty("password"));
		logger.info("validating pagetitle after login");
		String title=driver.getTitle();
		if(title.equals("Dashboard / nopCommerce administration")) {
			logger.info("Page Title matches");
			logger.info("Login Test passed");
			driver.findElement(By.xpath("//a[normalize-space()='Logout']")).click();
			Assert.assertTrue(true);
		}else
		{	logger.info("Title does not match");
			logger.info("Taking screenshot");
			Util.takeScreenshot(driver, "loginTest");
			logger.info("login Test failed");
			Assert.assertTrue(false);
		}
	}
	
	@Test(dataProvider="getData")
	public void loginTest_DataDriven(String un,String pwd) throws IOException {
		logger.info("Logging into the application");
		lp.login(un, pwd);
		logger.info("validating pagetitle after login");
		String title=driver.getTitle();
		if(title.equals("Dashboard / nopCommerce administration")) {
			logger.info("Page Title matches");
			logger.info("Login Test passed");
			driver.findElement(By.xpath("//a[normalize-space()='Logout']")).click();
			Assert.assertTrue(true);
		}else
		{	logger.info("Title does not match");
			logger.info("Taking screenshot");
			Util.takeScreenshot(driver, "loginTest_DataDriven");
			logger.info("login Test failed");
			Assert.assertTrue(false);
		}
	}
	@DataProvider
	public String[][] getData() throws IOException {
		int rownum=Excel_Utility.getRowCount(filepath, sheet);
		int colnum=Excel_Utility.getCellCount(filepath, sheet, 1);
		String data[][]=new String[rownum][colnum];
		for(int i=1;i<=rownum;i++) {
			for(int j=0;j<colnum;j++) {
				data[i-1][j]=Excel_Utility.getCellData(filepath, sheet, i, j);
			}
			}
		return data;
	}
	@Test
	public void titleTest() {
		logger.info("Validating Page Title");
		String title=lp.getTitle();
		Assert.assertEquals(title, "Your store. Login");
		logger.info("Page title matches");
	}
	
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}

}
