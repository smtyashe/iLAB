package com.iLABCareers.tests;


import static org.testng.Assert.assertEquals;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.iLABCareers.database.entities.ApplicantEntity;
import com.iLABCareers.database.utilities.retrieval.DataRetrieval;
import com.iLABCareers.utilities.ApplicantUtil;
import com.iLABCareers.utilities.BaseMethods;

public class submitJobApplicationTest extends BaseMethods {

	@BeforeMethod
	public void initialiseTests() {
		initialise();
	}
	
	@Test
	public void submitJobApplicationOnFirstClick() {
		
		ApplicantEntity applicantEntity = (ApplicantEntity) DataRetrieval.getInstance()
				.getPositiveData(new ApplicantEntity());
		currentTestData.add(applicantEntity);
		
		ApplicantUtil.navigateToCareers();
		ApplicantUtil.clickSouthAfricaLink();
		ApplicantUtil.clickApplyOnline();
		ApplicantUtil.populateApplicantInformation(applicantEntity);
		
		String uploadError = ApplicantUtil.submitApplication();
		
		assertEquals("You need to upload at least one file." , uploadError);
	}

	
	@AfterMethod
	public void exitBrowser() {
		{
			driver.quit();
			driver = null;
		}
	}
}

