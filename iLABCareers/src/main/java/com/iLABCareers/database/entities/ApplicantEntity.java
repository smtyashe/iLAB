package com.iLABCareers.database.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Table;

@Entity
@DynamicUpdate
@Table(name = "applicant")
public class ApplicantEntity extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String fullName, emailAddress, phoneNumber, whyWorkAtILAB, testType;

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getWhyWorkAtILAB() {
		return whyWorkAtILAB;
	}

	public void setWhyWorkAtILAB(String whyWorkAtILAB) {
		this.whyWorkAtILAB = whyWorkAtILAB;
	}

	public String getTestType() {
		return testType;
	}

	public void setTestType(String testType) {
		this.testType = testType;
	}

}
