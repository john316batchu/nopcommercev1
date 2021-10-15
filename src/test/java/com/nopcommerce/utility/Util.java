package com.nopcommerce.utility;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.nopcommerce.base.BaseClass;

public class Util extends BaseClass 
{
	
	public static void takeScreenshot(WebDriver driver,String testname) throws IOException {
		TakesScreenshot ts=(TakesScreenshot)driver;
		File source=ts.getScreenshotAs(OutputType.FILE);
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());//time stamp
		String name=testname+timeStamp;
		File target=new File(System.getProperty("user.dir")+"/screenshots/"+name+".png");
		FileUtils.copyFile(source, target);
		System.out.println("captured screenshot ");
		}
	public static String RandomString() {
		String string=RandomStringUtils.randomAlphabetic(5);
		return string;
	}

}
