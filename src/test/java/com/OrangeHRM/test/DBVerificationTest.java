package com.OrangeHRM.test;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.orangehrm.base.BaseClass;
import com.orangehrm.pages.HomePage;
import com.orangehrm.pages.LoginPage;
import com.orangehrm.utilities.DBConnection;
import com.orangehrm.utilities.Dataprovider;
import com.orangehrm.utilities.ExtentManager;

public class DBVerificationTest extends BaseClass{

	private HomePage hp;
	private LoginPage lp;
	
	
	@BeforeMethod
	public void  setupPages() {
		
//	LoginPage and HomePage Objects 
		hp = new HomePage(getDriver());
		lp = new LoginPage(getDriver());
		
	}
	
	
	@Test(dataProvider = "emp_verification", dataProviderClass = Dataprovider.class)
	public void verifyEmployeeNameFromDB(String emp_ID, String emp_Name) {
		
		SoftAssert softassert = new SoftAssert();
	/*
		SoftAssert softassert = new SoftAssert();
		Instead of creating Object hard coded like this created  object using thread local and 
		created getter method for the object
	*/
//		calling login method
		ExtentManager.logStep("LogIn as admin to Application with  valid Credentials");
		lp.logIn(prop.getProperty("username"), prop.getProperty("password"));
		
//		calling PIM method
		ExtentManager.logStep("Clicked On PIM Tab");
		hp.clickOnPIM();
		
//		Calling Employee Search Tab method
		ExtentManager.logStep("Search For Employee with Name");
		hp.employeeSearch(emp_Name);
		
//		Fetching Employee name with ID from DB
		ExtentManager.logStep("Employee Name with ID");
		String emp_id = emp_ID;
		
//		Storing in collection
		Map<String, String> emp_details = DBConnection.getEmployeeDetails(emp_id);
		
		String emp_FirstName = emp_details.get("firstName");
		String emp_MiddleName = emp_details.get("middleName");
		String emp_LastName = emp_details.get("lastName");
		
//		remove leading and trailing whitespace from a string using trim()
		String emp_first_MiddleName = (emp_FirstName +" "+ emp_MiddleName).trim();
		
//		Validation of employee names
		ExtentManager.logStep("Verify and Validate the Employee first and Middle Name");
		softassert.assertTrue(hp.verifyFirstandMiddleName(emp_first_MiddleName));
		
		
//		Compare and verify the names
		ExtentManager.logStep("Compare and verify the Employee First and Last Name");
		softassert.assertTrue(hp.verifyemployeeLastName(emp_LastName));
		
		ExtentManager.logStep("DB Verification Successful");
		
		softassert.assertAll();
		
	}
	
}
