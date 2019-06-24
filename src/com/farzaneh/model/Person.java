package com.farzaneh.model;

public class Person {
	private Integer personId;
	private String firstName;
	private String lastName;
	private String emailAddress;
	private String principalName;
	private String personTypeCode;

	public Person() {
		super();
	}

	public Person(Integer personId, String firstName, String lastName, String emailAddress, String principalName,
			String personTypeCode) {
		super();
		this.personId = personId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAddress = emailAddress;
		this.principalName = principalName;
		this.personTypeCode = personTypeCode;
	}

	public Integer getPersonId() {
		return personId;
	}

	public void setPersonId(Integer personId) {
		this.personId = personId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getPrincipalName() {
		return principalName;
	}

	public void setPrincipalName(String principalName) {
		this.principalName = principalName;
	}

	public String getPersonTypeCode() {
		return personTypeCode;
	}

	public void setPersonTypeCode(String personTypeCode) {
		this.personTypeCode = personTypeCode;
	}

}
