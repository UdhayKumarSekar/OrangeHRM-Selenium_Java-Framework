package com.orangehrm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.orangehrm.actiondriver.Actiondriver;
import com.orangehrm.base.BaseClass;

public class LoginPage {

	private Actiondriver actiondriver;
	
	
//	Define Locators Using By Class
	private By userName_field = By.name("username");
	
	private By password_field = By.cssSelector("input[type='password']");
	
	private By logIn_btn = By.xpath("//button[text() = ' Login ']");
	
	private By error_Msg = By.xpath("//p[text()='Invalid credentials']");
	

/*	commented this constructor and modified as below to follow Singleton pattern		
//	Constructor
//	constructor to initialize the Action driver Object by WebDriver Instance
	public LoginPage(WebDriver driver) {
		this.actiondriver = new Actiondriver(driver);
	}

*/	
	
//	constructor after singleton Design
	public LoginPage(WebDriver driver) {
		this.actiondriver = BaseClass.getActionDriver();
	}
	
//	method to perform login
	public void logIn(String UserName, String Password) {
		actiondriver.enterText(userName_field, UserName);
		actiondriver.enterText(password_field, Password);
		actiondriver.clickontheElement(logIn_btn);
		
	}
	
//	method to check if error message is displayed
	public boolean isErrorMessageDisplayed() {
		return actiondriver.IsDiplayed1(error_Msg);
	}
	
	
//	method to get the text from Error Message
	public String getErrorMessageText() {
		return actiondriver.getText(error_Msg);
	}
	
//	Verify if error is correct or not
	public void VerifyErrorMsg(String expected) {
		actiondriver.compareTexts(error_Msg, expected);
	}
}
