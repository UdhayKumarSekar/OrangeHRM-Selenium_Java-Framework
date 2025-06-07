package com.OrangeHRM.test;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.orangehrm.base.BaseClass;
import com.orangehrm.pages.HomePage;
import com.orangehrm.pages.LoginPage;
import com.orangehrm.pages.MyInfo;
import com.orangehrm.utilities.Dataprovider;
import com.orangehrm.utilities.ExtentManager;

public class MyInfoTest extends BaseClass{

	private LoginPage loginPage;
	private HomePage homePage;
	private MyInfo myInfo;

	@BeforeMethod
	public void setup() {
		loginPage = new LoginPage(getDriver());
		homePage = new HomePage(getDriver());
		myInfo = new MyInfo(getDriver());
	}

	@AfterMethod
	public void tearDown() {
		homePage.logOut();
		getDriver().quit();
	}

	@Test(dataProvider = "ValidData", dataProviderClass = Dataprovider.class)
	public void verifyMyInfoInDashBoard(String username, String password) {

		ExtentManager.startTest("Test: Verify the MyInfo Tab in Dashboard");
		System.out.println("Test: Verify the MyInfo Tab in Dashboard - Thread: " + Thread.currentThread().getId());
		logStep("Navigating to Login Page with Valid User Data");

		loginPage.logIn(username, password);
		logStep("Successfully Logged In and Landed on Home Page");

		logStep("Verify whether The MyInfo Tab is Visible and Clickable");
		Assert.assertTrue(myInfo.isMyInfoVisible(), "MyInfo tab should be visible");
		myInfo.clickOnMyInfo();
/*
		logStep("Validating Employee Image...");
		Assert.assertTrue(myInfo.isEmpImageVisible(), "Employee image should be visible");

		logStep("Validating Employee Personal Data...");
		Assert.assertTrue(myInfo.isEmpPersonalData(), "Employee personal details should be visible");

		logStep("Validating Emergency Contact Details...");
		Assert.assertTrue(myInfo.isEmpEmergencyContactDetails(), "Employee emergency contact details should be visible");

		logStep("Validating Assigned Dependents...");
		Assert.assertTrue(myInfo.isEmpDependents(), "Employee assigned dependents should be visible");

		logStep("Validating Immigration Details...");
		Assert.assertTrue(myInfo.isEmpImmigration(), "Employee immigration details should be visible");

		logStep("Validating Job Information...");
		Assert.assertTrue(myInfo.isEmpJobInfo(), "Employee job information should be visible");

		logStep("Validating Salary Details...");
		Assert.assertTrue(myInfo.isEmpSalaryVisible(), "Employee salary details should be visible");

		logStep("Validating Assigned Supervisors...");
		Assert.assertTrue(myInfo.isEmpSupervisorsVisible(), "Supervisor assigned to employee should be visible");

		logStep("Validating Employee Qualifications...");
		Assert.assertTrue(myInfo.isEmpQualificationVisible(), "Employee qualifications should be visible");

		logStep("Validating Employee Membership Details...");
		Assert.assertTrue(myInfo.isEmpMembershipVisible(), "Employee membership details should be visible");
*/	
	}

	private void logStep(String message) {
		logger.info(message);
		ExtentManager.logStep(message);
	}
}
