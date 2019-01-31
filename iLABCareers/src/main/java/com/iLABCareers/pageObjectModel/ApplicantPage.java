package com.iLABCareers.pageObjectModel;

import org.openqa.selenium.By;

public class ApplicantPage {
	
	
	public static By clickCareers = By.linkText("CAREERS");
	
	public static By clickSouthAfrica = By.linkText("South Africa");
	
	public static By firstAvailableJob = By.tagName("a");
	
	public static By clickApplyOnline  = By.linkText("Apply Online");
	
	public static By enterApplicantName  = By.id("applicant_name");
	
	public static By emailAddress  = By.id("email");
	
	public static By phoneNumber  = By.id("phone");
	
	public static By whyWorkAtILab  = By.id("message");
	
	public static By clickSubmitApplication  = By.id("wpjb_submit");
	
	
	public static By errorValidationText  = By.className("wpjb-errors");
}
