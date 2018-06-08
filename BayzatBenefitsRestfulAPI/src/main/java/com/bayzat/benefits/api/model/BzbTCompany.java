package com.bayzat.benefits.api.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Mohamed Yusuff
 *
 */
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table (name="BZB_T_COMPANY")
public class BzbTCompany extends ResourceSupport implements Serializable {
	
	/**
	 * Auto Generated Serial Version UID
	 */
	private static final long serialVersionUID = 4660408765265956006L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="BZB_SQ_COMPANY_GEN")
	@SequenceGenerator(name="BZB_SQ_COMPANY_GEN", sequenceName="BZB_SQ_COMPANY_ID", initialValue=1001, allocationSize=1)
	@Column(name="COMPANY_ID", updatable=false, nullable=false)
	private Long companyId;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="REGISTRATION_NUMBER")
	private String registrationNumber;
	
	@Column(name="CONTACT_NUMBER")
	private String contactNumber;
	
	@Column(name="EMAIL")
	private String email;
	
	@Column(name="WEBSITE")
	private String website;
	
	@OneToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="ADDRESS_ID", foreignKey=@ForeignKey(name="BZB_FK_COMPANY_ADDRESS_ID"))
	private BzbTAddress address;
	
	@OneToMany(mappedBy="company", fetch=FetchType.LAZY)
	@JsonIgnore
	private List<BzbTEmployee> employees;

	
	/**
	 * No Argument Default Constructor
	 */
	public BzbTCompany() {
	}

	/**
	 * Argument Constructor to initialize Entity with Values
	 * 
	 * @param name
	 * @param registrationNumber
	 * @param contactNumber
	 * @param email
	 * @param website
	 * @param address
	 */
	public BzbTCompany(String name, String registrationNumber, String contactNumber, String email, String website,
			BzbTAddress address) {
		this.name = name;
		this.registrationNumber = registrationNumber;
		this.contactNumber = contactNumber;
		this.email = email;
		this.website = website;
		this.address = address;
	}
	
	/**
	 * @return the companyId
	 */
	public Long getCompanyId() {
		return companyId;
	}
	/**
	 * @param companyId the companyId to set
	 */
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the registrationNumber
	 */
	public String getRegistrationNumber() {
		return registrationNumber;
	}
	/**
	 * @param registrationNumber the registrationNumber to set
	 */
	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
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
	 * @return the website
	 */
	public String getWebsite() {
		return website;
	}
	/**
	 * @param website the website to set
	 */
	public void setWebsite(String website) {
		this.website = website;
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
	 * @return the employees
	 */
	public List<BzbTEmployee> getEmployees() {
		return employees;
	}
	/**
	 * @param employees the employees to set
	 */
	public void setEmployees(List<BzbTEmployee> employees) {
		this.employees = employees;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((contactNumber == null) ? 0 : contactNumber.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((registrationNumber == null) ? 0 : registrationNumber.hashCode());
		result = prime * result + ((website == null) ? 0 : website.hashCode());
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
		BzbTCompany other = (BzbTCompany) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (contactNumber == null) {
			if (other.contactNumber != null)
				return false;
		} else if (!contactNumber.equals(other.contactNumber))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (registrationNumber == null) {
			if (other.registrationNumber != null)
				return false;
		} else if (!registrationNumber.equals(other.registrationNumber))
			return false;
		if (website == null) {
			if (other.website != null)
				return false;
		} else if (!website.equals(other.website))
			return false;
		return true;
	}
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BzbTCompany [companyId=" + companyId + ", name=" + name + ", registrationNumber=" + registrationNumber
				+ ", contactNumber=" + contactNumber + ", email=" + email + ", website=" + website + ", address="
				+ address + /*", employees=" + employees + */"]";
	}
}