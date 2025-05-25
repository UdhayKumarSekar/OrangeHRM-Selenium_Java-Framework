package com.OrangeHRM.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.orangehrm.utilities.APIAutomationUtility;
import com.orangehrm.utilities.ExtentManager;
import com.orangehrm.utilities.RetryAnalyzer;

import io.restassured.response.Response;

public class APIDemoTest {
	
	@Test(retryAnalyzer = RetryAnalyzer.class )
	public void verifyGetUserAPI() {
	
//	The validation done using  HARD ASSERT
		
//	1. Define end Point
		String endPoint = "https://jsonplaceholder.typicode.com/users/1";
		ExtentManager.logStep("API EndPoint: " + endPoint);
		
//	2. GET Request
		ExtentManager.logStep("GET Request");
		Response response = APIAutomationUtility.sendGetRequest(endPoint);
		
//	3. Status codes Validation
		ExtentManager.logStep("API Response Code Validation");
		boolean  IsValidStatusCode = APIAutomationUtility.validateStatusCode(response, 200);
		
		Assert.assertTrue(IsValidStatusCode, "Status Code Not Matching:  No Expected Response");
		
		if(IsValidStatusCode) {
			ExtentManager.logStepValidationforAPI("Validation SuccessFull:  Response Code is Matching ");
		}
		else {
			ExtentManager.logFailureAPI("Validation Failed : Response Code is not Matching");
		}
	
//	4. User name Validation
		ExtentManager.logStep(" UserName validation for an user");

		String userName = APIAutomationUtility.getJsonValue(response, "username");
		boolean isUserNameValid = "Bret".equals(userName);
		
		Assert.assertTrue(isUserNameValid, "User Name is Not Matching");
		
		if(isUserNameValid) {
			ExtentManager.logStepValidationforAPI("Validation SuccessFull: Username matching");
		}
		else {
			ExtentManager.logFailureAPI("Validation Failed: Username Not Matching");
		}
		
//	5. user email Validation
			ExtentManager.logStep("email validation for a user");

			String user_email = APIAutomationUtility.getJsonValue(response, "email");
			boolean IsUser_emailValid = "Sincere@april.biz".equals(user_email);
			
			Assert.assertTrue(IsUser_emailValid, "User email is Not Matching");
			
			if(IsUser_emailValid) {
				ExtentManager.logStepValidationforAPI("Validation SuccessFull: email matching");
			}
			else {
				ExtentManager.logFailureAPI("Validation Failed: email Not Matching");
			}
			
	}
	
}
