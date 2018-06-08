/**
 * 
 */
package com.bayzat.benefits.api.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bayzat.benefits.api.exception.BayzatTechnicalException;
import com.bayzat.benefits.api.exception.CompanyResourceExistException;
import com.bayzat.benefits.api.exception.CompanyResourceNotExistException;
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
			LOGGER.error("Error occurred when trying to fetch all companies", exc);
			throw new BayzatTechnicalException(exc.getMessage());
		}
		return companies;
	}

	@Override
	public BzbTCompany getCompany(Long id) {
		BzbTCompany company = null;
		try {
			Optional<BzbTCompany> companyOptional = companyRepository.findById(id);
			if (!companyOptional.isPresent()) {
				throw new CompanyResourceNotExistException("The requested Company with Id: " + id
						+ " doesn't exist. The requested operation [GET] cannot be performed.");
			}
			company = companyOptional.get();
		} catch (Exception exc) {
			LOGGER.error("Error occurred when trying to fetch company with Id: " + id, exc);
			throw new BayzatTechnicalException(exc.getMessage());
		}
		return company;
	}

	@Override
	public BzbTCompany addCompany(BzbTCompany company) {
		BzbTCompany savedCompany = null;
		try {
			Optional<BzbTCompany> companyOptional = companyRepository.findByName_RegistrationNumber(company.getName(),
					company.getRegistrationNumber());
			if (companyOptional.isPresent()) {
				if (companyOptional.get().getAddress().equals(company.getAddress())) {
					throw new CompanyResourceExistException("A Company with the name - '" + company.getName()
							+ "' is already exist for the given address. "
							+ "The requested operation [POST] cannot be performed.");
				}
			} else {
				companyOptional = companyRepository.findByAddress_PostalCode(
						company.getAddress().getPostalCode());
				if (companyOptional.isPresent()) {
					throw new CompanyResourceExistException(
							"The given address is already associated to another Company - '"
									+ companyOptional.get().getName()
									+ "'. The requested operation [POST] cannot be performed.");
				}
			}
			savedCompany = companyRepository.save(company);
		} catch (Exception exc) {
			LOGGER.error("Error occurred when trying to add a new company", exc);
			throw new BayzatTechnicalException(exc.getMessage());
		}
		return savedCompany;
	}

	@Override
	public BzbTCompany updateCompany(Long id, BzbTCompany company) {
		BzbTCompany updatedCompany = null;
		try {
			Optional<BzbTCompany> companyOptional = companyRepository.findById(id);
			if (!companyOptional.isPresent()) {
				throw new CompanyResourceNotExistException("The requested Company with Id: " + id
						+ " doesn't exist. The requested operation [PUT] cannot be performed.");
			}
			company.setCompanyId(id);
			updatedCompany = companyRepository.save(company);
		} catch (Exception exc) {
			LOGGER.error("Error occurred when trying to update an existing company with Id: " + id, exc);
			throw new BayzatTechnicalException(exc.getMessage());
		}
		return updatedCompany;
	}

	@Override
	public void deleteCompany(Long id) {
		try {
			Optional<BzbTCompany> companyOptional = companyRepository.findById(id);
			if (!companyOptional.isPresent()) {
				throw new CompanyResourceNotExistException("The requested Company with Id: " + id
						+ " doesn't exist. The requested operation [DELETE] cannot be performed.");
			}
			companyRepository.deleteById(id);
		} catch (Exception exc) {
			LOGGER.error("Error occurred when trying to delete an existing company", exc);
			throw new BayzatTechnicalException(exc.getMessage());
		}
	}
}