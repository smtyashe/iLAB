package com.iLABCareers.utilities;

import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebElement;

import com.gargoylesoftware.htmlunit.html.applets.AppletClassLoader;
import com.iLABCareers.database.entities.ApplicantEntity;
import com.iLABCareers.pageObjectModel.ApplicantPage;

public class ApplicantUtil extends BaseUtil {

	private static Pattern regexPattern;
	private static Matcher regMatcher;

	public static void navigateToCareers() {

		controller.getElement(ApplicantPage.clickCareers).click();
	}

	public static void clickSouthAfricaLink() {

		controller.getElement(ApplicantPage.clickSouthAfrica).click();
	}

	public void clickFirtsAvailableJobHyperLink(String href, int position) {

		@SuppressWarnings("unchecked")
		List<WebElement> jobLinks = (List<WebElement>) controller.getElement(ApplicantPage.firstAvailableJob);
		Iterator<WebElement> i = jobLinks.iterator();

		int j = 0;
		while (i.hasNext()) {
			WebElement anchor = i.next();
			if (anchor.getAttribute("href").contains(href)) {
				j++;
			}

			if (anchor.getAttribute("href").contains(href) && j == position) {
				anchor.click();
				break;
			}
		}
	}

	public static void clickApplyOnline() {

		controller.getElement(ApplicantPage.clickApplyOnline).click();
	}

	public static void populateApplicantInformation(ApplicantEntity applicantEntity) {
		
		
		 String number = applicantEntity.getPhoneNumber();
		 String email = applicantEntity.getEmailAddress();
		controller.getElement(ApplicantPage.enterApplicantName).sendKeys(applicantEntity.getFullName());
		controller.getElement(ApplicantPage.emailAddress).sendKeys(validateEmailAddress(email));
		controller.getElement(ApplicantPage.phoneNumber).sendKeys(generatePhoneNumber(number));
		controller.getElement(ApplicantPage.whyWorkAtILab).sendKeys(applicantEntity.getWhyWorkAtILAB());
	}

	public static String generatePhoneNumber(String number) {

		String randomNumbers = RandomStringUtils.randomNumeric(7);
		return number + "079" + randomNumbers;
	}

	public static java.lang.String validateEmailAddress(String emailAddress) {

		regexPattern = Pattern.compile("^[(a-zA-Z-0-9-\\_\\+\\.)]+@[(a-z-A-z)]+\\.[(a-zA-z)]{2,3}$");
		regMatcher = regexPattern.matcher(emailAddress);
		if (regMatcher.matches()) {

			return "Valid Email Address";

		} else {

			return "Invalid Email Address";
		}
	}

	public static String submitApplication() {

		controller.getElement(ApplicantPage.clickSubmitApplication).click();

		return controller.getElement(ApplicantPage.errorValidationText).getText();
	}
}
