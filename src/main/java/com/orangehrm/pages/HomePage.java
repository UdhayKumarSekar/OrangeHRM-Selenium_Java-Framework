package com.orangehrm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.orangehrm.actiondriver.Actiondriver;
import com.orangehrm.base.BaseClass;

public class HomePage {

//	creating object for action driver class for reuse
	private Actiondriver actiondriver;

//	locating the elements

	private By Admin_tab = By.xpath("//span[text()='Admin']");
	private By userID_btn = By.className("oxd-userdropdown-name");
	private By logout_btn = By.xpath("//a[text()='Logout']");
	private By OrangeHRM_logo = By.xpath("//div[@class='oxd-brand-banner']//img");

	private By Dashboard_ele = By.xpath("//h6[normalize-space()='Dashboard']");
	private By searchtab_ele = By.xpath("//div[@class='oxd-main-menu-search']");
	private By PIM_ele = By.xpath("//span[normalize-space()='PIM']");
	private By Leave_ele = By.xpath("//span[normalize-space()='Leave']");

//	Inside PIM For employee list
	private By employee_search = By
			.xpath("//label[text()='Employee Name']/parent::div/following-sibling::div/div/div/input");
	private By search_btn = By.xpath("//button[@type='submit']");
	private By emp_First_Middle_name = By.xpath("//div[@class = 'oxd-table-card']/div/div[3]");
	private By emp_lastname = By.xpath("//div[@class='oxd-table-card']/div/div[4]");

	/*
	 * commented this constructor and modified as below to follow Singleton pattern
	 *
	 * // constructor to initialize the Action driver Object by WebDriver Instance
	 * public HomePage(WebDriver driver) { this.actiondriver = new
	 * Actiondriver(driver); }
	 */

//	constructor after singleton Design
	public HomePage(WebDriver driver) {
		this.actiondriver = BaseClass.getActionDriver();
	}

//	Method to verify if Admin tab is visible
	public boolean IsAdminTabVisible() {
		return actiondriver.IsDiplayed1(Admin_tab);
	}

//	method to verify the logo 
	public boolean VerifyOrangeHRMLogo() {
		return actiondriver.IsDiplayed1(OrangeHRM_logo);
	}

//	method to verify dashboard is visible
	public boolean isDashboardVisible() {
		return actiondriver.IsDiplayed1(Dashboard_ele);
	}

//	method to check search tab element
	public boolean isSearchbarVisible() {
		return actiondriver.IsDiplayed1(searchtab_ele);
	}

//	method to check PIM element
	public boolean isPIMelementVisible() {
		return actiondriver.IsDiplayed1(PIM_ele);
	}

//	method to verify the Leave element
	public boolean isLeaveElementVisible() {
		return actiondriver.IsDisplayed(Leave_ele);
	}

//	Method to navigate PIM
	public void clickOnPIM() {
		actiondriver.clickontheElement(PIM_ele);
	}

//	method to  Employee Search
	public void employeeSearch(String value) {
		actiondriver.enterText(employee_search, value);
		actiondriver.clickontheElement(search_btn);
		actiondriver.scrollToElement(emp_First_Middle_name);
	}

//	Verify  Employee First and Middle Name
	public boolean verifyFirstandMiddleName(String act_emp_firstMiddlename_from_DB) {
		return actiondriver.compareTexts(emp_First_Middle_name, act_emp_firstMiddlename_from_DB);
	}

//	verify Emp last Name
	public boolean verifyemployeeLastName(String act_Emp_LastName_fromDB) {
		return actiondriver.compareTexts(emp_lastname, act_Emp_LastName_fromDB);
	}

//	method to perform logout operation
	public void logOut() {
		actiondriver.clickontheElement(userID_btn);
		actiondriver.clickontheElement(logout_btn);
	}
}
