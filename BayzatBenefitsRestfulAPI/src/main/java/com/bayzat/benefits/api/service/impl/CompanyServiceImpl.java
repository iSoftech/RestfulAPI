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
 * Bayzat Benefits Restful API Service Implementation for <tt>/companies</tt> Resource with CRUD operations.
 * 
 * @author Mohamed Yusuff
 */
@Service
public class CompanyServiceImpl implements ICompanyService {

	// To Log the Error Messages in case of Exceptions
	private static final Logger LOGGER = LoggerFactory.getLogger(CompanyServiceImpl.class);

	@Autowired
	private ICompanyRepository companyRepository;

	/**
	 * Returns all Companies
	 * 
	 * @return a list of {@link BzbTCompany}
	 */
	@Override
	public List<BzbTCompany> getAllCompanies() {
		List<BzbTCompany> companies = null;
		try {
			// Finds all Companies Entities
			companies = companyRepository.findAll();
		} catch (Exception exc) {
			LOGGER.error("Error occurred when trying to fetch all companies", exc);
			throw new BayzatTechnicalException(exc.getMessage());
		}
		return companies;
	}

	/**
	 * Returns requested Company
	 * 
	 * @param companyId refers to attribute {@code companyId}
	 * @return a {@link BzbTCompany} identified by its id
	 */
	@Override
	public BzbTCompany getCompany(Long companyId) {
		BzbTCompany company = null;
		try {
			// Finds a Company by its id to determine if it is present
			Optional<BzbTCompany> companyOpt = companyRepository.findById(companyId);
			// Throws Resource Not Exist Exception if not present
			if (!companyOpt.isPresent()) {
				throw new CompanyResourceNotExistException("The requested Company with Id: " + companyId
						+ " doesn't exist. The requested operation [GET] cannot be performed.");
			}
			// Retrieves requested Company if present
			company = companyOpt.get();
		} catch (Exception exc) {
			LOGGER.error("Error occurred when trying to fetch company with Id: " + companyId, exc);
			throw new BayzatTechnicalException(exc.getMessage());
		}
		return company;
	}

	/**
	 * Adds a new Company
	 * 
	 * @param company refers to a new instance of {@link BzbTCompany}
	 * @return a newly added {@link BzbTCompany}
	 */
	@Override
	public BzbTCompany addCompany(BzbTCompany company) {
		BzbTCompany savedCompany = null;
		try {
			// Finds company by Name and RegistrationNumber to determine if it is already exist
			Optional<BzbTCompany> companyOpt = companyRepository.findByName_RegistrationNumber(company.getName(),
					company.getRegistrationNumber());
			// Throws Resource Exist Exception if present a Company already present with 
			// this name and registration number and the Address is same
			// Otherwise checks for Child Resource (Address) if it is already associated 
			// to different parent Resource (Company)
			if (companyOpt.isPresent()) {
				// Throws Resource Exist Exception if referring to same Resource details
				if (companyOpt.get().getAddress().equals(company.getAddress())) {
					throw new CompanyResourceExistException("A Company with the name - '" + company.getName()
							+ "' is already exist for the given address. "
							+ "The requested operation [POST] cannot be performed.");
				}
			} else {
				// Finds Company by Address.PostalCode to determine if it is already associated 
				// to different Company
				companyOpt = companyRepository.findByAddress_PostalCode(
						company.getAddress().getPostalCode());
				// Throws Resource Exist Exception if a Company already present with this address
				if (companyOpt.isPresent()) {
					throw new CompanyResourceExistException(
							"The given address is already associated to another Company - '"
									+ companyOpt.get().getName()
									+ "'. The requested operation [POST] cannot be performed.");
				}
			}
			// Saves and Returns the newly added Company
			savedCompany = companyRepository.save(company);
		} catch (Exception exc) {
			LOGGER.error("Error occurred when trying to add a new company", exc);
			throw new BayzatTechnicalException(exc.getMessage());
		}
		return savedCompany;
	}

	/**
	 * Updates an existing Company
	 * 
	 * @param companyId refers to attribute {@code companyId}
	 * @param company refers to an edited instance of {@link BzbTCompany}
	 * @return an updated {@link BzbTCompany} identified by its id
	 */
	@Override
	public BzbTCompany updateCompany(Long companyId, BzbTCompany company) {
		BzbTCompany updatedCompany = null;
		try {
			// Finds a Company by its id to determine if it is present
			Optional<BzbTCompany> companyOpt = companyRepository.findById(companyId);
			// Throws Resource Not Exist Exception if not present
			if (!companyOpt.isPresent()) {
				throw new CompanyResourceNotExistException("The requested Company with Id: " + companyId
						+ " doesn't exist. The requested operation [PUT] cannot be performed.");
			}
			// Sets the requested CompanyId
			company.setCompanyId(companyId);
			// Saves and Returns the updated Company
			updatedCompany = companyRepository.save(company);
		} catch (Exception exc) {
			LOGGER.error("Error occurred when trying to update an existing company with Id: " + companyId, exc);
			throw new BayzatTechnicalException(exc.getMessage());
		}
		return updatedCompany;
	}

	/**
	 * Deletes an Existing Company
	 * 
	 * @param companyId refers to attribute {@code companyId}
	 */
	@Override
	public void deleteCompany(Long companyId) {
		try {
			// Finds a Company by its id to determine if it is present
			Optional<BzbTCompany> companyOpt = companyRepository.findById(companyId);
			// Throws Resource Not Exist Exception if not present
			if (!companyOpt.isPresent()) {
				throw new CompanyResourceNotExistException("The requested Company with Id: " + companyId
						+ " doesn't exist. The requested operation [DELETE] cannot be performed.");
			}
			// Deletes the requested Company by its id if present
			companyRepository.deleteById(companyId);
		} catch (Exception exc) {
			LOGGER.error("Error occurred when trying to delete an existing company", exc);
			throw new BayzatTechnicalException(exc.getMessage());
		}
	}
}