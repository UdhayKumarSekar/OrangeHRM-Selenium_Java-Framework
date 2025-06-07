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

public class MyInfoDashBoardTest extends BaseClass {

	private LoginPage lp;
	private HomePage hp;
	private MyInfo My_Info;

	@BeforeMethod
	public void setup() {

		lp = new LoginPage(getDriver());
		hp = new HomePage(getDriver());
		My_Info = new MyInfo(getDriver());

	}

	@Test(dataProvider = "ValidData", dataProviderClass = Dataprovider.class)
	public void verifyMyInfoInDashBoard(String username, String password) {

		ExtentManager.startTest("Test: Verify the MyInfo Tab in DashBoard");
		System.out.println("Test: Verify the MyInfo Tab in DashBoard" + Thread.currentThread().getId());
		ExtentManager.logStep("Navigating to Login Page with Valid User Data");

		lp.logIn(username, password);

		logger.info("Successfully Logged In and Landed on Home Page...");
		System.out.println("Successfully Logged In and Landed On Home Page");
		ExtentManager.logStep("Verify whether The MyInfo Tab is Visible and also clickable in the Dashboard");
		logger.info("Verify whether The MyInfo Tab is Visible and also clickable in the Dashboard");
		
		Assert.assertTrue(My_Info.isMyInfoVisible(), "MyInfo tab should be Visible");
		My_Info.clickOnMyInfo();
		ExtentManager.logStep("Validation Successfully !!...");
		logger.info("Validation Successfully !!...");

		ExtentManager.logStep("Employee Image Validation...");
		logger.info("Employee Image Validation...");
		Assert.assertTrue(My_Info.IsEmpImageVisible(), "Employee Image should be visible !!!...");
		ExtentManager.logStep("Employee Image Validation Successfully !!!..");
		logger.info("Employee Image Validation Successfully !!!..");
		
		ExtentManager.logStep("Employee Personal Data Validation..");
		logger.info("Employee Personal Data validation...");
		Assert.assertTrue(My_Info.IsEmpPersonalData(), "Employee Personal Details are visible..!!!..");
		ExtentManager.logStep("Employee Personal Data Validated Successfully.. !!");

		ExtentManager.logStep("Employee Emergency Contact Details are Visible...");
		logger.info("Employee Emergency Contact Details are Visible..");
		Assert.assertTrue(My_Info.IsEmpEmergencyContactDetails(),
				"Employee Emergency contact Details should be Visible..!!");
		ExtentManager.logStep("Employee Emergency Contact Details are Visible Successfully..!!");

		ExtentManager.logStep("Employee Assigned Dependent validation");
		logger.info("Employee Assigned Dependent Validation");
		Assert.assertTrue(My_Info.IsEmpDependents(), "Employee Assigned Dependent should be visible");
		ExtentManager.logStep("Employee Dependent Validation Successfully..!!");

		ExtentManager.logStep("Employee Immigration Details are Visible..");
		logger.info("Employee Immigration Details validation ");
		Assert.assertTrue(My_Info.IsEmpImmigration(), "Employee Immigration Details should be visible");
		ExtentManager.logStep("Employee Immigration Details are Validated Successfully..!!");

		ExtentManager.logStep("Employee Job Information Validation");
		logger.info("Validating the Employee My Job Information");
		Assert.assertTrue(My_Info.IsEmpJobInfo(), "Employee Job Information shoulb be Visible..!!");
		ExtentManager.logStep("Employee Job Information validation Successfully..!!");

		ExtentManager.logStep("Employee Salary Details validation");
		logger.info("Validating the Employee Salary Details..");
		Assert.assertTrue(My_Info.IsEmpSalaryVisible(), "Employee Salary Details are must be visible..");
		ExtentManager.logStep("Employee Salary Details are Validated Successfully...!!");

		ExtentManager.logStep("Employee Assigned Supervisor Validation");
		logger.info("Validating the Supervisor Assigned for an Employee..");
		Assert.assertTrue(My_Info.IsEmpSupervisorsVisible(), "The supervisor for an Employee should be visible..!");
		ExtentManager.logStep("The Reporting Supervisor for an Employee details are validated Successfully..!!");

		ExtentManager.logStep("Verify Employee Qualifications are Visible..");
		logger.info("Validating the Employee Qualifications are Visible or Not..!!");
		Assert.assertTrue(My_Info.IsEmpQualificationVisible(), "Employee Qualification Details should be visible..");
		ExtentManager.logStep("Employee Qualifications are validated Successfully..!!");

		ExtentManager.logStep("Verify the Employee Membership are visible or Not..!!");
		logger.info("Validating the Employee Membership are visible");
		Assert.assertTrue(My_Info.IsEmpMembershipVisible(), "Employee Membership should be visible..!!");
		ExtentManager.logStep("Employee Membership Details are validated Succesfully..!!");
		
	}
	
	@AfterMethod
	public void tearDown() {
		getDriver().quit();
	}

}
