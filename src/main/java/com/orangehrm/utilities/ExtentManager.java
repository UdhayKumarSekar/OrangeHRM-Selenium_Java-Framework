package com.orangehrm.utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {

	private static ExtentReports extent;
	private static ThreadLocal<ExtentTest> Test = new ThreadLocal<>();
	private static Map<Long, WebDriver> drivermap = new HashMap<>();

//	Initialize the Extent Report
	public synchronized static ExtentReports getReport() {

		if (extent == null) {
			String report_path = System.getProperty("user.dir") + "/src/test/resources/ExtentReport/ExtentReport.html";

			ExtentSparkReporter Spark = new ExtentSparkReporter(report_path);
			Spark.config().setReportName("OrangeHRM Test Automation Report");
			Spark.config().setDocumentTitle("OrangeHRM Application Test Report");
			Spark.config().setTheme(Theme.DARK);

			extent = new ExtentReports();
			extent.attachReporter(Spark);

//			Adding System information
			extent.setSystemInfo("Operating System", System.getProperty("os.name"));
			extent.setSystemInfo("Java Version", System.getProperty("java.version"));
			extent.setSystemInfo("User Name", System.getProperty("user.name"));

		}

		return extent;
	}

//	Start the Test
	public static ExtentTest startTest(String testName) {
		ExtentTest extentTest = getReport().createTest(testName);
		Test.set(extentTest);

		return extentTest;
	}

//	End a Test
	public static void endTest() {
		getReport().flush();
	}

//	get Current Thread's Test 
	public static ExtentTest getTestThread() {
		return Test.get();
	}

//	Method to get the Name of the Current Test
	public static String getTestName() {
		ExtentTest currentTest = getTestThread();

		if (currentTest != null) {
			return currentTest.getModel().getName();
		} else {
			return "Currently There is No Test Active for this Thread";
		}
	}

//	Log a Step
	public static void logStep(String logMessage) {
		getTestThread().info(logMessage);
	}

//	Log a Step Validation for API 
	public static void logStepValidationforAPI(String logMessage) {
		getTestThread().pass(logMessage);

	}
	
//	This separate method  for log Failure
	public static void logFailureAPI(String logMessage) {
		String colour_message = String.format("<span style = 'color:red;'>%s</span>", logMessage);

		getTestThread().fail(colour_message);

	}


//	Log a Step Validation with ScreenShot
	public static void logStepWithScreenshot(WebDriver driver, String logMessage, String screenShotMessage) {

		getTestThread().pass(logMessage);
		// screenshot Method
		attachScreenshot(driver, screenShotMessage);

	}

//	log a Failure
	public static void logFailure(WebDriver driver, String logMessage, String screenShotMessage) {
		String colour_message = String.format("<span style = 'color:red;'>%s</span>", logMessage);

		getTestThread().fail(colour_message);
		// ScreenShot Method
		attachScreenshot(driver, screenShotMessage);

	}



//	log a Skip
	public static void logSkip(String logMessage) {
		String colour_message = String.format("<span style = 'color:orange;'>%s</span>", logMessage);
		getTestThread().skip(colour_message);
	}

//	Take ScreenShot with date and time
	public static String takeScreenShot(WebDriver driver, String screenshot_fileName) {
		TakesScreenshot tss = (TakesScreenshot) driver;

//	catching the screenshot as File
		File src = tss.getScreenshotAs(OutputType.FILE);

//	time stamp for the file
		String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());

//	creating location path to save the screenshot
		String dest_path = System.getProperty("user.dir") + "/src/test/resources/screenshots" + screenshot_fileName
				+ "_" + timestamp + ".png";

//		storing the screenshot inside a directory
		File final_path = new File(dest_path);
		try {
//			copy the file into destination
			FileUtils.copyFile(src, final_path);
		} catch (IOException e) {

			e.printStackTrace();
		}
//	convert screenshot to Base64 for embedding the Report
		String base64Format = convertToBase64(src);

		return base64Format;

	}

//	convert screenshot to Base64 format
	public static String convertToBase64(File screenShotFile) {
		String base64Format = "";

//	Read the file content into a byte array
		byte[] fileContent;
		try {
			fileContent = FileUtils.readFileToByteArray(screenShotFile);
			base64Format = Base64.getEncoder().encodeToString(fileContent);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

//	convert the byte array to Base64 String
		return base64Format;
	}

//	Attaching the screenshot file to the report using Base64
	public static void attachScreenshot(WebDriver driver, String message) {

		try {
			String screenShotBase64 = takeScreenShot(driver, getTestName());

			getTestThread().info(message, com.aventstack.extentreports.MediaEntityBuilder
					.createScreenCaptureFromBase64String(screenShotBase64).build());
		} catch (Exception e) {

			getTestThread().fail("Failed to attach the ScreenShot: " + message);
			e.printStackTrace();
		}

	}

//	Register WebDriver for Current Thread
	public static void registerDriver(WebDriver driver) {
		drivermap.put(Thread.currentThread().getId(), driver);
	}
}
