package com.orangehrm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.orangehrm.actiondriver.Actiondriver;
import com.orangehrm.base.BaseClass;

public class MyInfo {

//	Actiondriver Object
	private Actiondriver actiondriver;

//	Admin Tab elements

	private By my_Info = By.xpath("//span[normalize-space()='My Info']");
	private By emp_name = By.xpath("//div[@class='orangehrm-edit-employee-name']");
	private By emp_image = By.xpath("//img[@class='employee-image']");

	private By emp_personal_Details = By.xpath("//h6[normalize-space()='Personal Details']");
	private By emp_personal_tag = By.xpath("//a[normalize-space()='Personal Details']");

	private By emp_Contact_tag = By.xpath("//a[normalize-space()='Contact Details']");
	private By emp_contact_Details = By.xpath("//h6[normalize-space()='Contact Details']");

	private By emp_Emergency_Contacts = By.xpath(
			"//body/div[@id='app']/div[@class='oxd-layout orangehrm-upgrade-layout']/div[@class='oxd-layout-container']/div[@class='oxd-layout-context']/div[@class='orangehrm-background-container']/div[@class='orangehrm-card-container']/div[@class='orangehrm-edit-employee']/div[@class='orangehrm-edit-employee-content']/div[1]");
	private By emp_Emergency_tag = By.xpath("//a[normalize-space()='Emergency Contacts']");

	private By emp_Dependents = By.xpath("//a[normalize-space()='Dependents']");
	private By emp_Assigned_dependents = By.xpath(
			"//body/div[@id='app']/div[@class='oxd-layout orangehrm-upgrade-layout']/div[@class='oxd-layout-container']/div[@class='oxd-layout-context']/div[@class='orangehrm-background-container']/div[@class='orangehrm-card-container']/div[@class='orangehrm-edit-employee']/div[@class='orangehrm-edit-employee-content']/div[1]");

	private By emp_Immigration = By.xpath("//a[normalize-space()='Immigration']");
	private By emp_Immigration_Associes = By.xpath(
			"//body/div[@id='app']/div[@class='oxd-layout orangehrm-upgrade-layout']/div[@class='oxd-layout-container']/div[@class='oxd-layout-context']/div[@class='orangehrm-background-container']/div[@class='orangehrm-card-container']/div[@class='orangehrm-edit-employee']/div[@class='orangehrm-edit-employee-content']/div[1]");

	private By emp_Job = By.xpath("//a[normalize-space()='Job']");
	private By emp_job_Info = By.xpath("//h6[normalize-space()='Job Details']");

	private By emp_Salary_details = By.xpath("//a[normalize-space()='Salary']");
	private By emp_Assigned_Salary_Components = By.xpath("//h6[normalize-space()='Assigned Salary Components']");

	private By emp_ReportTo_details = By.xpath("//a[normalize-space()='Report-to']");
	private By emp_Assigned_Supervisor_Details = By
			.xpath("//div[@class='orangehrm-horizontal-padding orangehrm-top-padding']");

	private By emp_qualifications = By.xpath("//a[normalize-space()='Qualifications']");
	private By emp_Qualify_Info = By.xpath("//h6[normalize-space()='Qualifications']");

	private By emp_Membership = By.xpath("//a[normalize-space()='Memberships']");
	private By emp_Assigned_Membership = By.xpath(
			"//body/div[@id='app']/div[@class='oxd-layout orangehrm-upgrade-layout']/div[@class='oxd-layout-container']/div[@class='oxd-layout-context']/div[@class='orangehrm-background-container']/div[@class='orangehrm-card-container']/div[@class='orangehrm-edit-employee']/div[@class='orangehrm-edit-employee-content']/div[1]");

//	constructor 
	public MyInfo(WebDriver driver) {
		this.actiondriver = BaseClass.getActionDriver();
	}

	public void clickOnMyInfo() {
		actiondriver.clickontheElement(my_Info);
	}

//	Method for Employee MyInfo is Visible
	public boolean isMyInfoVisible() {
		return actiondriver.IsDiplayed1(my_Info);
	}

//	Method for Employee Name is Visible
	public boolean IsEmpNameVisible() {
		actiondriver.clickontheElement(emp_name);
		return actiondriver.IsDisplayed(emp_name);
	}

//	Method for Employee Image is Visible
	public boolean IsEmpImageVisible() {
		actiondriver.clickontheElement(emp_image);
		return actiondriver.IsDisplayed(emp_image);
	}

//	Method for Employee Personal Data is Visible
	public boolean IsEmpPersonalData() {
		actiondriver.clickontheElement(emp_personal_tag);
		return actiondriver.IsDiplayed1(emp_personal_Details);
	}

//	Method for Employee Contact Details are Visible
	public boolean IsEmpContactDetails() {
		actiondriver.clickontheElement(emp_Contact_tag);
		return actiondriver.IsDiplayed1(emp_contact_Details);
	}

//	Method for Employee Emergency Contact Details are Visible
	public boolean IsEmpEmergencyContactDetails() {
		actiondriver.clickontheElement(emp_Emergency_tag);
		return actiondriver.IsDiplayed1(emp_Emergency_Contacts);
	}

//	Method for Employee dependent visible
	public boolean IsEmpDependents() {
		actiondriver.clickontheElement(emp_Dependents);
		return actiondriver.IsDiplayed1(emp_Assigned_dependents);
	}

//	Method for Employee Dependents
	public boolean IsEmpImmigration() {
		actiondriver.clickontheElement(emp_Immigration);
		return actiondriver.IsDiplayed1(emp_Immigration_Associes);
	}

//	Method for Employee Job Info
	public boolean IsEmpJobInfo() {
		actiondriver.clickontheElement(emp_Job);
		return actiondriver.IsDiplayed1(emp_job_Info);
	}

//	Method for Employee Salary
	public boolean IsEmpSalaryVisible() {
		actiondriver.clickontheElement(emp_Salary_details);
		return actiondriver.IsDiplayed1(emp_Assigned_Salary_Components);

	}

//	Method for Employee Reporting Supervisor
	public boolean IsEmpSupervisorsVisible() {
		actiondriver.clickontheElement(emp_ReportTo_details);
		return actiondriver.IsDiplayed1(emp_Assigned_Supervisor_Details);

	}

//	Method for employee Qualifications
	public boolean IsEmpQualificationVisible() {
		actiondriver.clickontheElement(emp_qualifications);
		return actiondriver.IsDiplayed1(emp_Qualify_Info);
	}

//	Method for Employee Membership
	public boolean IsEmpMembershipVisible() {
		actiondriver.clickontheElement(emp_Membership);
		return actiondriver.IsDiplayed1(emp_Assigned_Membership);

	}
}