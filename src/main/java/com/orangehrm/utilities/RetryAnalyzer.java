package com.orangehrm.utilities;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {

	private int retryCount = 0; // Counting the Number of Retries
	private static final int maxRetryCount = 2; // The limit of Retry, max number

	/*
	 * This method helps to Retest the failed test cases for maximum limit of 2 if
	 * the test was passed at first execution no need of this class implementation
	 */
	@Override
	public boolean retry(ITestResult result) {

		if (retryCount < maxRetryCount) {
			retryCount++;

			return true; // Making to Retry
		}

		return false; // Stop the Retry when it is reached the maximum try
	}
}
