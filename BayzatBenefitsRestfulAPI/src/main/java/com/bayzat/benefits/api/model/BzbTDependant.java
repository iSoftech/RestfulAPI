package com.bayzat.benefits.api.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 * @author Mohamed Yusuff
 *
 */
@Entity
@Table (name="BZB_T_DEPENDANT")
public class BzbTDependant implements Serializable {

	/**
	 * Auto Generated Serial Version UID
	 */
	private static final long serialVersionUID = -3038472736903820231L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="BZB_SQ_DEPENDANT_GEN")
	@SequenceGenerator(name="BZB_SQ_DEPENDANT_GEN", sequenceName="BZB_SQ_DEPENDANT_ID", initialValue=1001, allocationSize=1)
	@Column(name="DEPENDANT_ID", updatable=false, nullable=false)
	private Long dependantId;
	
	@Column(name="FIRST_NAME")
	private String firstName;
	
	@Column(name="LAST_NAME")
	private String lastName;
	
	@Column(name="GENDER")
	private String gender;
	
	@Column(name="AGE")
	private Integer age;
	
	@Column(name="DATE_OF_BIRTH")
	@Temporal(TemporalType.DATE)
	private Date dateOfBirth;
	
	@Column(name="CONTACT_NUMBER")
	private String contactNumber;
	
	@Column(name="EMAIL")
	private String email;
	
	@Column(name="RELATIONSHIP")
	private String relationship;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="ADDRESS_ID", foreignKey=@ForeignKey(name="BZB_FK_DEPENDANT_ADDRESS_ID"))
	private BzbTAddress address;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@OnDelete(action=OnDeleteAction.CASCADE)
	@JoinColumn(name="EMPLOYEE_ID", nullable=false, foreignKey=@ForeignKey(name="BZB_FK_DEPENDANT_EMPLOYEE_ID"))
	private BzbTEmployee employee;


	/**
	 * No Argument Default Constructor
	 */
	public BzbTDependant() {
	}

	/**
	 * Argument Constructor to initialize Entity with Values
	 * 
	 * @param firstName
	 * @param lastName
	 * @param gender
	 * @param age
	 * @param dateOfBirth
	 * @param contactNumber
	 * @param email
	 * @param relationship
	 * @param address
	 * @param employee
	 */
	public BzbTDependant(String firstName, String lastName, String gender, Integer age, Date dateOfBirth,
			String contactNumber, String email, String relationship, BzbTAddress address, BzbTEmployee employee) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.age = age;
		this.dateOfBirth = dateOfBirth;
		this.contactNumber = contactNumber;
		this.email = email;
		this.relationship = relationship;
		this.address = address;
		this.employee = employee;
	}

	/**
	 * @return the dependantId
	 */
	public Long getDependantId() {
		return dependantId;
	}

	/**
	 * @param dependantId the dependantId to set
	 */
	public void setDependantId(Long dependantId) {
		this.dependantId = dependantId;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * @return the age
	 */
	public Integer getAge() {
		return age;
	}

	/**
	 * @param age the age to set
	 */
	public void setAge(Integer age) {
		this.age = age;
	}

	/**
	 * @return the dateOfBirth
	 */
	public Date getDateOfBirth() {
		return dateOfBirth != null ? (Date) dateOfBirth.clone() : null;
	}

	/**
	 * @param dateOfBirth the dateOfBirth to set
	 */
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth != null ? (Date) dateOfBirth.clone() : null;
	}

	/**
	 * @return the contactNumber
	 */
	public String getContactNumber() {
		return contactNumber;
	}

	/**
	 * @param contactNumber the contactNumber to set
	 */
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the relationship
	 */
	public String getRelationship() {
		return relationship;
	}

	/**
	 * @param relationship the relationship to set
	 */
	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}

	/**
	 * @return the address
	 */
	public BzbTAddress getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(BzbTAddress address) {
		this.address = address;
	}

	/**
	 * @return the employee
	 */
	public BzbTEmployee getEmployee() {
		return employee;
	}

	/**
	 * @param employee the employee to set
	 */
	public void setEmployee(BzbTEmployee employee) {
		this.employee = employee;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((age == null) ? 0 : age.hashCode());
		result = prime * result + ((contactNumber == null) ? 0 : contactNumber.hashCode());
		result = prime * result + ((dateOfBirth == null) ? 0 : dateOfBirth.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((employee == null) ? 0 : employee.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((gender == null) ? 0 : gender.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((relationship == null) ? 0 : relationship.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BzbTDependant other = (BzbTDependant) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (age == null) {
			if (other.age != null)
				return false;
		} else if (!age.equals(other.age))
			return false;
		if (contactNumber == null) {
			if (other.contactNumber != null)
				return false;
		} else if (!contactNumber.equals(other.contactNumber))
			return false;
		if (dateOfBirth == null) {
			if (other.dateOfBirth != null)
				return false;
		} else if (!dateOfBirth.equals(other.dateOfBirth))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (employee == null) {
			if (other.employee != null)
				return false;
		} else if (!employee.equals(other.employee))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (gender == null) {
			if (other.gender != null)
				return false;
		} else if (!gender.equals(other.gender))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (relationship == null) {
			if (other.relationship != null)
				return false;
		} else if (!relationship.equals(other.relationship))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BzbTDependant [dependantId=" + dependantId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", gender=" + gender + ", age=" + age + ", dateOfBirth=" + dateOfBirth + ", contactNumber="
				+ contactNumber + ", email=" + email + ", relationship=" + relationship + ", address=" + address
				+ ", employee=" + employee + "]";
	}
}