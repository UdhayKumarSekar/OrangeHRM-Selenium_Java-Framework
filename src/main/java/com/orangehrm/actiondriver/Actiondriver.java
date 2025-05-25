package com.orangehrm.actiondriver;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.orangehrm.base.BaseClass;
import com.orangehrm.utilities.ExtentManager;

public class Actiondriver {

	private WebDriver driver;
	private WebDriverWait wait;
	public static final Logger logger = BaseClass.logger;

//	constructor for the class
	public Actiondriver(WebDriver driver) {
		this.driver = driver;
		int explicit_time = Integer.parseInt(BaseClass.getProp().getProperty("Explicit_wait"));
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(explicit_time));
		logger.info("****** WebDriver Instance was Created ******");

//	Comment to know about the how many times the WebDriver instance created
		System.out.println("WebDriver Instance is Created...");
	}

//	Method to scroll the page to an element
	public void scrollToElement(By by) {
		try {

			applyBorder(by, "green");

			WebElement element = driver.findElement(by);
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].scrollIntoView(true); ", element);
		} catch (Exception e) {

			applyBorder(by, "red");

			System.out.println("Unable to Scroll to the element: " + e.getMessage());
			logger.info("****** Unable to Locate the Element ******");
		}

	}

//	Method to wait for page to load
	public void waitForPageToLoad(int timeOutInSecs) {
		try {
			wait.withTimeout(Duration.ofSeconds(timeOutInSecs));
			wait.until(WebDriver -> ((JavascriptExecutor) WebDriver).executeScript("return document.readyState")
					.equals("complete"));

			System.out.println("Page Loaded Successfully");
		} catch (Exception e) {
			System.out.println("page is not loaded with " + timeOutInSecs + " seconds. Exception: " + e.getMessage());
			logger.info("****** Page Is Not  Responding ******");

		}
	}

//	wait method for element to be click able
	private void waitForElementTobeClickable(By by) {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(by));
		} catch (Exception e) {

			System.out.println("Unable to Locate element also not clickable :  " + e.getMessage());
			logger.info("****** Unable to Click the Element ******");

		}
	}

//	wait method for element to be visible
	private void waitForElementTobeVisible(By by) {
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		} catch (Exception e) {
			System.out.println("Element is not visible:  " + e.getMessage());
			logger.info("****** the Element is not Visible ******");

		}
	}

//	method to click on the element
	public void clickontheElement(By by) {
//	retrieving the element description method
		String element_Description = getElementDescription(by);

		try {
			waitForElementTobeVisible(by);
			waitForElementTobeClickable(by);
			driver.findElement(by).click();

			applyBorder(by, "green");

			ExtentManager.logStep("Clicked on an element and the description of the element: " + element_Description);
			logger.info("Clicked on an element and the description of the element: " + element_Description);

		} catch (Exception e) {
			System.out.println("Unable to click on the element: " + e.getMessage());

			applyBorder(by, "red");

			ExtentManager.logFailure(BaseClass.getDriver(), "Unable to Click on the Element",
					element_Description + "Could not click on the element");
			logger.info("****** Unable to Click on the Element ******");
		}
	}

//	method to enter text into an input field-- Avoid code Duplication - fix the multiple call 
//	method for input field	
	public void enterText(By by, String value) {
		try {
			waitForElementTobeVisible(by);
//			driver.findElement(by).clear();
//			driver.findElement(by).sendKeys(value);
			WebElement Input_element = driver.findElement(by);
			Input_element.clear();
			Input_element.sendKeys(value);
			logger.info(
					"Entered the text on Input field & the element description: " + getElementDescription(by) + value);

			applyBorder(by, "green");

		} catch (Exception e) {
			System.out.println("Unable to Enter the text in input field: " + e.getMessage());

			applyBorder(by, "red");

			logger.info("****** Unable to Enter the text in Input field ******");

		}
	}

// 	modified code for the above method
//	1. this method will get the WebElement
	private WebElement getElement(By by) {
		waitForElementTobeVisible(by);
		return driver.findElement(by);

	}

//	we can use the WebElement here this will be the good practice
//	2. this is how we can locate, get and use the element
	public void Entervalue(By by, String text) {
		try {
			WebElement input_field = getElement(by);
			input_field.clear();
			input_field.sendKeys(text);

			applyBorder(by, "green");

		} catch (Exception e) {
			System.out.println("Unable to send the text into the input field: " + e.getMessage());

			applyBorder(by, "red");

			logger.info("****** Unable to send the text into the input field: " + e.getMessage() + " ******");
		}
	}

//	method to get text from an input field
	public String getText(By by) {
		try {
			waitForElementTobeVisible(by);

			applyBorder(by, "green");

			return driver.findElement(by).getText();
		} catch (Exception e) {
			System.out.println("Unable to get the Text: " + e.getMessage());

			applyBorder(by, "red");

			logger.info("****** Unable to get the Text: " + e.getMessage() + " ******");

			return " ";
		}
	}

//	Method for Compare Two Texts
	public boolean compareTexts(By by, String expected_text) {
		try {
			waitForElementTobeVisible(by);
			String actual_text = driver.findElement(by).getText();
			if (expected_text.equals(actual_text)) {

				applyBorder(by, "green");

				System.out.println("Texts are Equal/Matching: " + actual_text + " equals " + expected_text);

				ExtentManager.logStepWithScreenshot(BaseClass.getDriver(),
						"Test for Comparing text verified Successfully! : " + actual_text, " equals " + expected_text);
				logger.info("******** Texts are Equal/Matching: " + actual_text + " equals " + expected_text
						+ " *********");
				return true;

			} else {

				applyBorder(by, "red");

				System.out.println("Texts are Equal/Matching: " + actual_text + " Not equals " + expected_text);

				ExtentManager.logFailure(BaseClass.getDriver(),
						"Test for Comparing text verified Successfully! : " + actual_text,
						" is not equals " + expected_text);
				logger.info("******** Texts are Equal/Matching: " + actual_text + " Not equals " + expected_text
						+ " *******");

				return false;
			}

		} catch (Exception e) {

			applyBorder(by, "red");

			System.out.println("Unable to Compare Texts: " + e.getMessage());

			logger.info("Unable to Compare Texts: " + e.getMessage());
		}
		return false;
	}

//	Method to check if an Element is Displayed
//	Method --> 1
	public boolean IsDisplayed(By by) {
		try {
			waitForElementTobeVisible(by);
			boolean isDisplayed = driver.findElement(by).isDisplayed();

			if (isDisplayed) {

				applyBorder(by, "green");

				System.out.println("****** Element is Visible *******");
				ExtentManager.logStep("Element is Displayed : " + getElementDescription(by));
				logger.info("******* Element is Visible *******");
				return isDisplayed;
			} else {
				return isDisplayed;
			}
		} catch (Exception e) {

			applyBorder(by, "red");

			System.out.println("WebElement is Not Displayed: " + e.getMessage());

			ExtentManager.logFailure(BaseClass.getDriver(), "Element is Not Displayed: ",
					"Element is Not Displayed: " + getElementDescription(by));
			logger.info(" ******* WebElement is Not Displayed: " + e.getMessage() + " ******* ");

			return false;
		}
	}

//	Method to check if an Element is Displayed
//	Method --> 2 -- Simplified method and remove redundant condition
	public boolean IsDiplayed1(By by) {
		try {
			waitForElementTobeVisible(by);

			applyBorder(by, "green");

			ExtentManager.logStep("Element is Displayed");

			logger.info("Element is Displayed; " + getElementDescription(by));
			return driver.findElement(by).isDisplayed();

		} catch (Exception e) {
			System.out.println("Element is not Displayed: " + e.getMessage());

			applyBorder(by, "red");

			logger.info("******** Element is not Displayed: " + e.getMessage() + " ******** ");
			return false;
		}
	}

//	Method to get the Description of an element using locator
	public String getElementDescription(By locator) {

//		check for null driver/locator to avoid null pointer exception
		if (driver == null) {
			return "driver is null";
		}
		if (locator == null) {
			return "Locator is null";
		}

		try {
			// find the element using locator
			WebElement element = driver.findElement(locator);

			// Get Element Attributes and stored in variables
			String name = element.getDomAttribute("name");
			String id = element.getDomAttribute("id");
			String text = element.getText();
			String className = element.getDomAttribute("class");
			String placeHolder = element.getDomAttribute("placeholder");

			// Return the description based on element attributes
			if (isNotEmpty(name)) {
				return "Name Of the Element: " + name;
			} else if (isNotEmpty(id)) {
				return "id Of the Element: 	" + id;
			} else if (isNotEmpty(text)) {
				return "text Of the Element: 	" + truncate(text, 50);
			} else if (isNotEmpty(className)) {
				return "className Of the Element: 	" + className;
			} else if (isNotEmpty(placeHolder)) {
				return "placeHolder Of the Element: 	" + placeHolder;
			}
		} catch (Exception e) {
			logger.error("Unable to get the Element Description:  " + e.getMessage());
		}

		return "Unable to get Description of the Element";
	}

//	Utility method to check a String is not Null/empty
	private boolean isNotEmpty(String value) {
		return value != null && !value.isEmpty();
	}

//	Utility Method to truncate long String
	private String truncate(String value, int maxLength) {

		if (value == null || value.length() <= maxLength) {
			return value;
		}

		return value.substring(0, maxLength) + "...";
	}

//	utility method to Border for an element
	public void applyBorder(By by, String colour) {

		try {
			// Locate the Element
			WebElement element = driver.findElement(by);

			// making the border using js args
			String script = "arguments[0].style.border='3px solid " + colour + " ' ";

			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript(script, element);

			logger.info("Constructed a Border with colour " + colour + "to the particular Element "
					+ getElementDescription(by));

		} catch (Exception e) {

			logger.warn("Failed to construct the Border to an element: " + getElementDescription(by) + e.getMessage());
		}

	}

//	Methods for DropDown Elements using select tag
//	Method for get the element based on visible text
	public void selectByVisibleText(By by, String value) {
		try {
			WebElement element = driver.findElement(by);
			new Select(element).selectByVisibleText(value);
			applyBorder(by, "green");
			logger.info("Selected dropdown value based on Visible Text: " + value);

		} catch (Exception e) {
			applyBorder(by, "red");
			logger.error("Unable to get the dropdown element value " + value, e.getMessage());
		}
	}

//	Method based on select by value
	public void selectByvalue(By by, String value) {
		try {
			WebElement element = driver.findElement(by);
			new Select(element).selectByValue(value);
			applyBorder(by, "green");
			logger.info("Selected dropdown value based on a value: " + value);
		} catch (Exception e) {
			applyBorder(by, "red");
			logger.error("Unable to get the dropdown element value " + value, e.getMessage());
		}
	}

//	Method based select by Index
	public void selectByIndex(By by, int index_num) {
		try {
			WebElement element = driver.findElement(by);
			new Select(element).selectByIndex(index_num);
			applyBorder(by, "green");
			logger.info("Selected dropdown value based on Index: " + index_num);
		} catch (Exception e) {
			applyBorder(by, "red");
			logger.error("Unable to select the element based on Index: " + index_num + e);
		}

	}

//	Method to get all options from a dropdown
	public List<String> getDropdownOptions(By by) {

		List<String> element_list = new ArrayList<>();

		try {
			WebElement drop_element = driver.findElement(by);

			Select select = new Select(drop_element);

			for (WebElement elements : select.getOptions()) {
				element_list.add(elements.getText());
			}
			applyBorder(by, "green");
			logger.info("The List of Elements: " + getElementDescription(by));

		} catch (Exception e) {

			applyBorder(by, "red");
			logger.error("Unable to catch the elements: " + e.getMessage());
		}

		return element_list;
	}

//	JavaScript Utility Method
//	Method to click using JavaScript

	public void clickUsingJs(By by) {

		try {

			WebElement element = driver.findElement(by);
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
			applyBorder(by, "green");
			logger.info("Clicked element using Javascript: " + getElementDescription(by));

		} catch (Exception e) {

			applyBorder(by, "red");
			logger.error("Unable to click on the element using JavaScript", e.getMessage());
		}
	}

//	Method to  scroll till  bottom of the page
	public void scrollToBottom() {

		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight");
		logger.info("Scrolled till Bottom of the Page...");
	}

//	Method to highlight an element using  JavaScript
	public void highlightElementJS(By by) {

		try {
			WebElement element = driver.findElement(by);

			((JavascriptExecutor) driver).executeScript("arguments[0].style.border='3px solid black'", element);
			logger.info("Highlighted Element Using Javascript: " + getElementDescription(by));
		} catch (Exception e) {
			logger.error("Unable to Highlight an element using Javascript" + e);
		}
	}

//	Method to Switch between Browsers in same window
	public void switchToWindow(String windowTitle) {
		
		Set<String> windows = driver.getWindowHandles();
		
		try {
			for(String winds:windows) {
				driver.switchTo().window(winds);
				if (driver.getTitle().equals(windowTitle)) {
					logger.info("Switched to window: " + windowTitle);
					return;
				}
			}
			logger.warn("Window with Title : " + windowTitle + "Not Found..");
			
			
		} catch (Exception e) {
			logger.error("Unable To Switch between windows");
		}
	}
	
//	Method for switch to an iFrame
	public void switchToFrame(By by) {
		
		try {
			driver.switchTo().frame(driver.findElement(by));
			logger.info("Switched into the iframe:  " + getElementDescription(by));
		} catch (Exception e) {
			logger.error("Unable to switch to iframe" + e.getMessage());
		}
	}
	
//	Method to switch back to the default content
	public void switchToDefaultContent() {
		driver.switchTo().defaultContent();
		logger.info("Switched Back to the Default Content...");
	}
	
//	Method to accept an alert Pop-up
	public void acceptAlert() {
		try {
			driver.switchTo().alert().accept();
			logger.info("Alert accepted..");
		} catch (Exception e) {
			logger.error("No Alert Found To accept...", e.getMessage());
		}
	}
	
//	Method to dismiss an alert pop-up
	public void dismissAlert() {
		try {
			driver.switchTo().alert().dismiss();
			logger.info("Alert Dismissed...");
		} catch (Exception e) {
			logger.error("No alert Found to dismiss..", e.getMessage());
		}	
	}
	
//	Method to get alert text
	public String getAlertText() {
		
		try {
			return driver.switchTo().alert().getText();
		} catch (Exception e) {
			logger.error("NO alert Text Found..", e.getMessage());
			
			return " ";
		}
	}
	
//	Browser Actions
//	Method for Refresh Page
	public void refreshPage() {
		
		try {
			driver.navigate().refresh();
			ExtentManager.logStep("Page was Refreshed SuccessFully..");
			logger.info("Page refreshed Successfully...");
		} catch (Exception e) {
			ExtentManager.logFailure(BaseClass.getDriver(), "Unable to Refresh the page", "refresh cannot be done");
			logger.error("Unable to refresh Page: " + e.getMessage());
		}
	}
	
}
