/**
 * 
 */
package com.bayzat.benefits.api.service;

import java.util.List;

import com.bayzat.benefits.api.model.BzbTCompany;

/**
 * Bayzat Benefits Restful API Service Interface for <tt>/companies</tt> Resource with CRUD operations.
 * 
 * @author Mohamed Yusuff
 */
public interface ICompanyService {

	/**
	 * Returns all Companies
	 * 
	 * @return a list of {@link BzbTCompany}
	 */
	public List<BzbTCompany> getAllCompanies();
	
	/**
	 * Returns requested Company
	 * 
	 * @param companyId refers to attribute {@code companyId}
	 * @return a {@link BzbTCompany} identified by its id
	 */
	public BzbTCompany getCompany(Long companyId);
	
	/**
	 * Adds a new Company
	 * 
	 * @param company refers to a new instance of {@link BzbTCompany}
	 * @return a newly added {@link BzbTCompany}
	 */
	public BzbTCompany addCompany(BzbTCompany company);
	
	/**
	 * Updates an existing Company
	 * 
	 * @param companyId refers to attribute {@code companyId}
	 * @param company refers to an edited instance of {@link BzbTCompany}
	 * @return an updated {@link BzbTCompany} identified by its id
	 */
	public BzbTCompany updateCompany(Long companyId, BzbTCompany company);
	
	/**
	 * Deletes an Existing Company
	 * 
	 * @param companyId refers to attribute {@code companyId}
	 */
	public void deleteCompany(Long companyId);
}
