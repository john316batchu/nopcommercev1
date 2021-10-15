package com.nopcommerce.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BaseClass {

	public Properties prop;
	public static WebDriver driver;
	public FileInputStream fis;
	public Logger logger;

	public BaseClass() {
		 logger=Logger.getLogger("NopCommerceV1");
		 PropertyConfigurator.configure("log4j.properties");
		try {
			fis = new FileInputStream(
					System.getProperty("user.dir") + "//src//test//java//com//nopcommerce//config//config.properties");
			prop = new Properties();
			prop.load(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		public void initialise() {
			String browser=prop.getProperty("browser");
			if(browser.equals("chrome")) {
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"//drivers/chromedriver.exe");
				logger.info("Launching Chrome Browser");
				driver=new ChromeDriver();
			}else if(browser.equals("firefox")) {
				System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"//drivers/geckodriver.exe");
				logger.info("Launching firefox browser");
				driver=new FirefoxDriver();
			}else if(browser.equals("ie")) {
				logger.info("Launching IE browser");
				System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+"//drivers/IEDriverServer.exe");
				driver=new FirefoxDriver();
			}
			driver.manage().window().maximize();
			driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			logger.info("Opening the URL ");
			driver.get(prop.getProperty("url"));
			
		}

	

}
