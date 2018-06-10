/**
 * 
 */
package com.bayzat.benefits.api.constant;

/**
 * Enum for Gender Values
 * 
 * @author Mohamed Yusuff
 */
public enum Gender {

	Female("Female"),
	Male("Male");
	
	private String value;

	/**
	 * Argument Constructor to assign values for ENUM
	 * 
	 * @param value
	 */
	private Gender(String value) {
		this.value = value;
	}

	/**
	 * @param value
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * @return a string value
	 */
	public String getValue() {
		return value;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		return value;
	}
}