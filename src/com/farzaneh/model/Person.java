package com.farzaneh.model;

import java.io.Serializable;

public class Person implements Serializable {
	private Integer personId;
	private String firstName;
	private String lastName;
	private String emailAddress;
	private String principalName;
	private String personTypeCode;

	public Person() {
	}

	public Person(Integer personId, String firstName, String lastName, String emailAddress, String principalName,
			String personTypeCode) {
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((personId == null) ? 0 : personId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (personId == null) {
			if (other.personId != null)
				return false;
		} else if (!personId.equals(other.personId)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Person [personId=" + personId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", emailAddress=" + emailAddress + ", principalName=" + principalName + ", personTypeCode="
				+ personTypeCode + "]";
	}

}
