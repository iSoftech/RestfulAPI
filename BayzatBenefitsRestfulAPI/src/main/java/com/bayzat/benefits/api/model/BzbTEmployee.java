package com.bayzat.benefits.api.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.core.Relation;

import com.bayzat.benefits.api.constant.Gender;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Bayzat Benefits Table for Employee Entity Model
 * 
 * @author Mohamed Yusuff
 */
@Entity
@Relation(collectionRelation = "employees")
@Table (name="BZB_T_EMPLOYEE")
public class BzbTEmployee extends ResourceSupport implements Serializable {

	/**
	 * Auto Generated Serial Version UID
	 */
	private static final long serialVersionUID = 4726052352627225895L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="BZB_SQ_EMPLOYEE_GEN")
	@SequenceGenerator(name="BZB_SQ_EMPLOYEE_GEN", sequenceName="BZB_SQ_EMPLOYEE_ID", initialValue=1001, allocationSize=1)
	@Column(name="EMPLOYEE_ID", updatable=false, nullable=false)
	private Long employeeId;
	
	@Column(name="EMPLOYEE_CODE")
	private String employeeCode;
	
	@Column(name="FIRST_NAME")
	private String firstName;
	
	@Column(name="LAST_NAME")
	private String lastName;
	
	@Column(name="GENDER")
	@Enumerated(EnumType.STRING)
	private Gender gender;
	
	@Column(name="AGE")
	private Integer age;
	
	@Column(name="DATE_OF_BIRTH")
	@Temporal(TemporalType.DATE)
	private Date dateOfBirth;
	
	@Column(name="CONTACT_NUMBER")
	private String contactNumber;
	
	@Column(name="EMAIL")
	private String email;
	
	@Column(name="CURRENT_SALARY")
	private Double currentSalary;
	
	@Column(name="DATE_OF_JOINING")
	@Temporal(TemporalType.DATE)
	private Date dateOfJoining;
	
	@OneToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="ADDRESS_ID", foreignKey=@ForeignKey(name="BZB_FK_EMPLOYEE_ADDRESS_ID"))
	private BzbTAddress address;
	
	@JsonIgnore
	@ManyToOne(fetch=FetchType.EAGER)
	@OnDelete(action=OnDeleteAction.CASCADE)
	@JoinColumn(name="COMPANY_ID", nullable=false, foreignKey=@ForeignKey(name="BZB_FK_EMPLOYEE_COMPANY_ID"))
	private BzbTCompany company;
	
	@JsonIgnore
	@OneToMany(mappedBy="employee", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<BzbTDependant> dependants;

	
	/**
	 * No Argument Default Constructor
	 */
	public BzbTEmployee() {
	}

	/**
	 * Argument Constructor to initialize Entity with Values
	 * 
	 * @param employeeCode
	 * @param firstName
	 * @param lastName
	 * @param gender
	 * @param age
	 * @param dateOfBirth
	 * @param contactNumber
	 * @param email
	 * @param currentSalary
	 * @param dateOfJoining
	 * @param address
	 * @param company
	 */
	public BzbTEmployee(String employeeCode, String firstName, String lastName, String gender, Integer age,
			Date dateOfBirth, String contactNumber, String email, Double currentSalary, Date dateOfJoining,
			BzbTAddress address, BzbTCompany company) {
		this.employeeCode = employeeCode;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = Gender.valueOf(gender);
		this.age = age;
		this.dateOfBirth = dateOfBirth != null ? (Date) dateOfBirth.clone() : null;
		this.contactNumber = contactNumber;
		this.email = email;
		this.currentSalary = currentSalary;
		this.dateOfJoining = dateOfJoining != null ? (Date) dateOfJoining.clone() : null;
		this.address = address;
		this.company = company;
	}

	/**
	 * @return the employeeId
	 */
	public Long getEmployeeId() {
		return employeeId;
	}

	/**
	 * @param employeeId the employeeId to set
	 */
	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	/**
	 * @return the employeeCode
	 */
	public String getEmployeeCode() {
		return employeeCode;
	}

	/**
	 * @param employeeCode the employeeCode to set
	 */
	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
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
	public Gender getGender() {
		return gender;
	}

	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = Gender.valueOf(gender);
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
	 * @return the currentSalary
	 */
	public Double getCurrentSalary() {
		return currentSalary;
	}

	/**
	 * @param currentSalary the currentSalary to set
	 */
	public void setCurrentSalary(Double currentSalary) {
		this.currentSalary = currentSalary;
	}

	/**
	 * @return the dateOfJoining
	 */
	public Date getDateOfJoining() {
		return dateOfJoining != null ? (Date) dateOfJoining.clone() : null;
	}

	/**
	 * @param dateOfJoining the dateOfJoining to set
	 */
	public void setDateOfJoining(Date dateOfJoining) {
		this.dateOfJoining = dateOfJoining != null ? (Date) dateOfJoining.clone() : null;
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
	 * @return the company
	 */
	public BzbTCompany getCompany() {
		return company;
	}

	/**
	 * @param company the company to set
	 */
	public void setCompany(BzbTCompany company) {
		this.company = company;
	}

	/**
	 * @return the dependants
	 */
	public List<BzbTDependant> getDependants() {
		return dependants;
	}

	/**
	 * @param dependants the dependants to set
	 */
	public void setDependants(List<BzbTDependant> dependants) {
		this.dependants = dependants;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dependants == null) ? 0 : dependants.hashCode());
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((age == null) ? 0 : age.hashCode());
		result = prime * result + ((company == null) ? 0 : company.hashCode());
		result = prime * result + ((contactNumber == null) ? 0 : contactNumber.hashCode());
		result = prime * result + ((currentSalary == null) ? 0 : currentSalary.hashCode());
		result = prime * result + ((dateOfBirth == null) ? 0 : dateOfBirth.hashCode());
		result = prime * result + ((dateOfJoining == null) ? 0 : dateOfJoining.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((employeeCode == null) ? 0 : employeeCode.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((gender == null) ? 0 : gender.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
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
		BzbTEmployee other = (BzbTEmployee) obj;
		if (dependants == null) {
			if (other.dependants != null)
				return false;
		} else if (!dependants.equals(other.dependants))
			return false;
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
		if (company == null) {
			if (other.company != null)
				return false;
		} else if (!company.equals(other.company))
			return false;
		if (contactNumber == null) {
			if (other.contactNumber != null)
				return false;
		} else if (!contactNumber.equals(other.contactNumber))
			return false;
		if (currentSalary == null) {
			if (other.currentSalary != null)
				return false;
		} else if (!currentSalary.equals(other.currentSalary))
			return false;
		if (dateOfBirth == null) {
			if (other.dateOfBirth != null)
				return false;
		} else if (!dateOfBirth.equals(other.dateOfBirth))
			return false;
		if (dateOfJoining == null) {
			if (other.dateOfJoining != null)
				return false;
		} else if (!dateOfJoining.equals(other.dateOfJoining))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (employeeCode == null) {
			if (other.employeeCode != null)
				return false;
		} else if (!employeeCode.equals(other.employeeCode))
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
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BzbTEmployee [employeeId=" + employeeId + ", employeeCode=" + employeeCode + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", gender=" + gender + ", age=" + age + ", dateOfBirth=" + dateOfBirth
				+ ", contactNumber=" + contactNumber + ", email=" + email + ", currentSalary=" + currentSalary
				+ ", dateOfJoining=" + dateOfJoining + ", address=" + address + ", company=" + company + "]";
	}
}