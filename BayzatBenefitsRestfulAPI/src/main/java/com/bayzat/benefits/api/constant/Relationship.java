/**
 * 
 */
package com.bayzat.benefits.api.constant;

/**
 * Enum for Relationship Values
 * 
 * @author Mohamed Yusuff
 */
public enum Relationship {

	Child("Child"),
	Spouse("Spouse");
	
	private String value;

	
	/**
	 * Argument Constructor to assign values for ENUM
	 * 
	 * @param value
	 */
	private Relationship(String value) {
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
