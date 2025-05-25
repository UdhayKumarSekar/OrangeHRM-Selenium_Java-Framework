package com.OrangeHRM.test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.orangehrm.base.BaseClass;
import com.orangehrm.pages.HomePage;
import com.orangehrm.pages.LoginPage;
import com.orangehrm.utilities.Dataprovider;
import com.orangehrm.utilities.ExtentManager;

public class LoginPageTest extends BaseClass {

	private LoginPage login_page;
	private HomePage home_page;

	@BeforeMethod
	public void setupPages() {
		login_page = new LoginPage(getDriver());
		home_page = new HomePage(getDriver());
	}

	@Test(dataProvider = "ValidLoginData", dataProviderClass = Dataprovider.class)
	public void verifyValidLogInTest(String username, String password) {

		ExtentManager.startTest("Test: Verify LogIn with Valid Credentials"); // --> Implemented in ITestListener Class

		System.out.println("\n" + "Test: Verify LogIn with Valid Credentials" + Thread.currentThread().getId());

		ExtentManager.logStep("Navigating to Login Page and Providing valid User Data");
		login_page.logIn(username, password);

		ExtentManager.logStep("Verifying the Admin Tab is Visible or NOt");
		Assert.assertTrue(home_page.IsAdminTabVisible(), "Admin tab Should be visible after Successfull login");
		System.out.println("\n" + "Successfully!! Logged in with Valid Credentials");
		ExtentManager.logStep("validated Successfully !!..");

		home_page.logOut();
		ExtentManager.logStep("Logged Out Successfully !!..");
		System.out.println("Logged Out from application");
		staticwait(2);

	}

	@Test(dataProvider = "InValidLoginData", dataProviderClass = Dataprovider.class)
	public void InvalidLoginTest(String username, String password) {

		ExtentManager.startTest("Test: Verify LogIn with InValid Credentials"); // --> Implemented in ITestListener
																				// Class
		System.out.println("\n" + "Test: Verify LogIn with Invalid Credentials");
		ExtentManager.logStep("Navigating to Login Page and Providing valid User Data");
		login_page.logIn(username, password);
		staticwait(3);

		Assert.assertTrue(login_page.isErrorMessageDisplayed(), "Invalid credentials");
		ExtentManager.logStep("validated Successfully !!..");
		ExtentManager.logStep("Logged Out Successfully !!..");
	}
}
