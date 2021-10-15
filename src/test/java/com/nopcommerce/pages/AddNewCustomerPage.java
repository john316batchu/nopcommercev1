package com.nopcommerce.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.nopcommerce.base.BaseClass;

public class AddNewCustomerPage extends BaseClass {
	
	
	//Defining pageObjects
	@FindBy(xpath="//a[@href='#']//p[contains(text(),'Customers')]")
	WebElement lnkcustomersmenu;
	
	@FindBy(xpath="//a[@href='/Admin/Customer/List']//p[contains(text(),'Customers')]")
	WebElement lnkcustomers;
	
	@FindBy(xpath="//a[normalize-space()='Add new']")
	WebElement btnaddnew;
	
	@FindBy(xpath="//input[@id='Email']")
	WebElement txtemail;
	
	@FindBy(xpath="//input[@id='Password']")
	WebElement txtpassword;
	
	@FindBy(xpath="//input[@id='FirstName']")
	WebElement txtfirstname;
	
	@FindBy(xpath="//input[@id='LastName']")
	WebElement txtlastname;
	
	@FindBy(xpath="//input[@id='Gender_Male']")
	WebElement rdmale;
	
	@FindBy(xpath="//input[@id='Gender_Female']")
	WebElement rdfemale;
	
	@FindBy(xpath="//input[@id='Company']")
	WebElement txtcompanyname;
	
	@FindBy(xpath="//div[@class='input-group-append input-group-required']//div[@role='listbox']")
	WebElement listbox;
	
	@FindBy(xpath="//li[normalize-space()='Administrators']")
	WebElement administrators;
	
	@FindBy(xpath="//li[normalize-space()='Forum Moderators']")
	WebElement forummoderators;
	
	@FindBy(xpath="//li[normalize-space()='Guests']")
	WebElement guests;
	
	@FindBy(xpath="//li[normalize-space()='Registered']")
	WebElement registered;
	
	@FindBy(xpath="//li[contains(text(),'Vendors')]")
	WebElement vendors;
	
	@FindBy(xpath="//select[@name='VendorId']")
	WebElement drpvendor;
	
	@FindBy(xpath="//button[@name='save']")
	WebElement btnsave;
	
	public AddNewCustomerPage() {
		PageFactory.initElements(driver, this);
	}
	
	//actions
	
	public void clickCustomersMenu() {
		lnkcustomersmenu.click();
	}
	public void clickCustomers() {
		lnkcustomers.click();
	}
	public void clickAddnew() {
		btnaddnew.click();
	}
	public String getTitle() {
		return driver.getTitle();
	}
	
	public void addCustomerDetails(String email, String pwd,String fname,String lname, String gender,
			String comp,String role,String vendor) {
		txtemail.sendKeys(email);
		txtpassword.sendKeys(pwd);
		txtfirstname.sendKeys(fname);
		txtlastname.sendKeys(lname);
		if(gender.equals("male")) {
			rdmale.click();
		}else if(gender.equals("female")) {
			rdfemale.click();
		}
		txtcompanyname.sendKeys(comp);
		driver.findElement(By.xpath("//span[@title='delete']")).click();
		selectRole(role);
		selectVendor(vendor);
		btnsave.click();
	}
	public void selectVendor(String vendor) {
		Select s=new Select(drpvendor);
		s.selectByVisibleText(vendor);
	}
	public void selectRole(String role) {
		listbox.click();		
		switch(role)
		{
		case "Administrators":
			administrators.click();
			break;
		case "Forum Moderators":
			forummoderators.click();
			break;
		case "Guests":
			guests.click();
			break;
		case "Registered":
			registered.click();
			break;
		case "Vendors":
			vendors.click();
			break;
		}
		
	}
	
}
