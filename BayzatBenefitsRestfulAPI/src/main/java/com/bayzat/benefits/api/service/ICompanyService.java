/**
 * 
 */
package com.bayzat.benefits.api.service;

import java.util.List;

import com.bayzat.benefits.api.model.BzbTCompany;

/**
 * @author Mohamed Yusuff
 *
 */
public interface ICompanyService {

	public List<BzbTCompany> getAllCompanies();
	
	public BzbTCompany getCompany(Long id);
	
	public BzbTCompany addCompany(BzbTCompany company);
	
	public BzbTCompany updateCompany(Long id, BzbTCompany company);
	
	public void deleteCompany(Long id);
}
