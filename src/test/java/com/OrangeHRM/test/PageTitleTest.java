package com.OrangeHRM.test;

import org.testng.annotations.Test;

import com.orangehrm.base.BaseClass;
import com.orangehrm.utilities.ExtentManager;

public class PageTitleTest extends BaseClass {

	@Test
	public void titleTest() {
		
		ExtentManager.startTest("Test: Verify the Page Title");	//--> Implemented in ITestListener Class
		logger.info("***** Test: Verify the Page Title *****");
//		String act_title = driver.getTitle();				// Before ThreadLocal
		
//		New Changes After ThreadLocal
		
		String act_title = getDriver().getTitle();
		ExtentManager.logStep("Catched The Title: "+ act_title);
		
		assert act_title.equals("OrangeHRM") : "Test Failed - Titles are Not Matching";
		ExtentManager.logStep("Validation of the Page Title Successfully Done..!!");
		
		System.out.println("Title Of the Page: "+act_title);
		System.out.println("Test Passed - Title is Matching");
		ExtentManager.logStep("Title is Matching !!!..");

	}

}
