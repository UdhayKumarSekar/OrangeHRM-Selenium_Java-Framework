package com.orangehrm.listeners;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.ITestAnnotation;

import com.orangehrm.base.BaseClass;
import com.orangehrm.utilities.ExtentManager;
import com.orangehrm.utilities.RetryAnalyzer;

public class ItestL implements ITestListener, IAnnotationTransformer {

	
// this method applies for annotation on class, constructor and method level
	@Override
	public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
		annotation.setRetryAnalyzer(RetryAnalyzer.class);
	}

	
//	Called when Test Starts
	@Override
	public void onTestStart(ITestResult result) {
//		storing the test name
		String testname = result.getMethod().getMethodName();

//		logged in Extent Report with corresponding test name
		ExtentManager.startTest(testname);
		ExtentManager.logStep("Test Started: " + testname);
	}

	
	@Override
	public void onTestSuccess(ITestResult result) {
//		Called when test is passed
//		storing the test name
		String testname = result.getMethod().getMethodName();
		
//	call the loggers Method based on the condition if it is not API then this method with driver was called
		if(!result.getTestClass().getName().toLowerCase().contains("api")) {
			ExtentManager.logStepWithScreenshot(BaseClass.getDriver(), " Test Passed SuccessFully!! ",
					"Test Ends:  " + testname + " - ✅ Test Passed !!");
//	this Condition added up for API		
		}
//	after checking the API then condition moved to get API method 		
		else {
			ExtentManager.logStepValidationforAPI("Test Ends:  " + testname + " - ✅ Test Passed !!");
		}
	}
	

	@Override
	public void onTestFailure(ITestResult result) {

//		Called when test is Failed
//		storing the test name
		String testname = result.getMethod().getMethodName();
		
		String failure_message = result.getThrowable().getMessage();

		ExtentManager.logStep(failure_message);
		
//		OnTestFailure Method for API
		if(!result.getTestClass().getName().toLowerCase().contains("api")) {
			ExtentManager.logFailure(BaseClass.getDriver(), " Test Failed !! ",
					"Test Ends:  " + testname + " - ❌ Test Failed !!");
		}
		else {
			ExtentManager.logFailureAPI("Test Ends:  " + testname + " - ❌ Test Failed !!");
		}
		
		

	}

	@Override
	public void onTestSkipped(ITestResult result) {
		
//		storing the test name in a variable
		String testName = result.getMethod().getMethodName();
		
		ExtentManager.logSkip("Test Skipped: "+testName);

	}
	

//	called when the test suite start execution
	@Override
	public void onStart(ITestContext context) {
//		Initialize the ExtentReport Manager
		ExtentManager.getReport();
	}

//	Called when test suite ends
	@Override
	public void onFinish(ITestContext context) {
//		this method will end the test
		ExtentManager.endTest();
	}

}
