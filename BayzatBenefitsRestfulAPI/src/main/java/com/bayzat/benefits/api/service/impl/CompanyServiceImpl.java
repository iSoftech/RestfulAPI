/**
 * 
 */
package com.bayzat.benefits.api.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bayzat.benefits.api.model.BzbTCompany;
import com.bayzat.benefits.api.repo.ICompanyRepository;
import com.bayzat.benefits.api.service.ICompanyService;

/**
 * Bayzat Benefits Restful API Service for <tt>/companies</tt> Resource with CRUD operations.
 * 
 * @author Mohamed Yusuff
 */
@Service
public class CompanyServiceImpl implements ICompanyService {

	// LOGGER to log the Error in case of Exceptions
	private static final Logger LOGGER = LoggerFactory.getLogger(CompanyServiceImpl.class);
	
	@Autowired
	private ICompanyRepository companyRepository;
	
	@Override
	public List<BzbTCompany> getAllCompanies() {
		List<BzbTCompany> companies = null;
		try {
			companies = companyRepository.findAll();
		} catch (Exception exc) {
			LOGGER.error("Error occurred when trying to fetch all companies.", exc);
		}
		return companies;
	}

	@Override
	public BzbTCompany getCompany(Long id) {
		BzbTCompany company = null;
		try {
			company = companyRepository.getOne(id);
		} catch (Exception exc) {
			LOGGER.error("Error occurred when trying to fetch company with Id: " + id + ".", exc);
		}
		return company;
	}

	@Override
	public void addCompany(BzbTCompany company) {
		try {
			companyRepository.save(company);
		} catch (Exception exc) {
			LOGGER.error("Error occurred when trying to add a new company.", exc);
		}
	}

	@Override
	public void updateCompany(Long id, BzbTCompany company) {
		try {
			company.setCompanyId(id);
			companyRepository.save(company);
		} catch (Exception exc) {
			LOGGER.error("Error occurred when trying to update an existing company with Id: " + id + ".", exc);
		}
	}

	@Override
	public void deleteCompany(Long id) {
		try {
			companyRepository.deleteById(id);
		} catch (Exception exc) {
			LOGGER.error("Error occurred when trying to delete an existing company.", exc);
		}
	}
}