package com.nopcommerce.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.nopcommerce.base.BaseClass;

public class LoginPage extends BaseClass {
	
//Defining pageFactory
	
	@FindBy(xpath="//input[@id='Email']")
	WebElement txtemail;
	
	@FindBy(xpath="//input[@id='Password']")
	WebElement txtpassword;
	
	@FindBy(xpath="//button[@type='submit']")
	WebElement btnlogin;
	
	//Initialising pagefactory with driver
	
	public LoginPage() {
		PageFactory.initElements(driver, this);
	}
	
	//Defining actions
	
	public void login(String un, String pwd) {
		txtemail.clear();
		txtemail.sendKeys(un);
		txtpassword.clear();
		txtpassword.sendKeys(pwd);
		btnlogin.click();
	}
	public String getTitle() {
		return driver.getTitle();
	}

}
