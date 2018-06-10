/**
 * 
 */
package com.bayzat.benefits.api.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
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
			LOGGER.error("Error occurred when trying to fetch all Companies", exc);
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
			String operation = HttpMethod.GET.name();
			// Validates and Throw Exception for the Existing Resource - Company
			validateExistingResourceAndThrowException(companyId, operation);
			// Retrieves requested Company if present
			company = companyRepository.getOne(companyId);
		} catch (Exception exc) {
			LOGGER.error("Error occurred when trying to fetch Company with CompanyId - " + companyId, exc);
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
			String operation = HttpMethod.POST.name();
			// Validates and Throws Exception for the New Resource - Company
			validateNewResourceAndThrowException(company, operation);
			// Saves and Returns the newly added Company
			savedCompany = companyRepository.save(company);
		} catch (Exception exc) {
			LOGGER.error("Error occurred when trying to add a new Company", exc);
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
			String operation = HttpMethod.PUT.name();
			// Validates and Throw Exception for the Existing Resource - Company
			validateExistingResourceAndThrowException(companyId, operation);
			// Retrieves a Company by its CompanyId
			BzbTCompany companyEntity = companyRepository.getOne(companyId);
			// Sets the requested CompanyId
			company.setCompanyId(companyId);
			// Updates the AddressId If not present to avoid creating duplicate Address Entities
			if (null != company.getAddress() && null == company.getAddress().getAddressId()) {
				company.getAddress().setAddressId(companyEntity.getAddress().getAddressId());
			}
			// Saves and Returns the updated Company
			updatedCompany = companyRepository.save(company);
		} catch (Exception exc) {
			LOGGER.error("Error occurred when trying to update an existing Company with CompanyId - " + companyId, exc);
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
			String operation = HttpMethod.DELETE.name();
			// Validates and Throw Exception for the Existing Resource - Company
			validateExistingResourceAndThrowException(companyId, operation);
			// Deletes the requested Company by its id if present
			companyRepository.deleteById(companyId);
		} catch (Exception exc) {
			LOGGER.error("Error occurred when trying to delete an existing Company", exc);
			throw new BayzatTechnicalException(exc.getMessage());
		}
	}

	/**
	 * Validates and Throw Exception for the Existing Resource - Company
	 * 
	 * @param companyId refers to attribute {@code companyId}
	 * @param operation refers to attribute of {@link HttpMethod}
	 */
	private void validateExistingResourceAndThrowException(Long companyId, String operation) {
		// Finds a Company by its id to determine if it is present
		Optional<BzbTCompany> companyEntity = companyRepository.findById(companyId);
		// Throws Resource Not Exist Exception if not present
		if (!companyEntity.isPresent()) {
			throw new CompanyResourceNotExistException("The requested Company with Id - " + companyId
					+ " doesn't exist. The requested operation [" + operation + "] cannot be performed.");
		}
	}
	
	/**
	 * Validates and Throws Exception for the New Resource - Company
	 * 
	 * @param companyId refers to attribute {@code companyId}
	 * @param operation refers to attribute of {@link HttpMethod}
	 */
	private void validateNewResourceAndThrowException(BzbTCompany company, String operation) {
		// Finds a Company by Name and RegistrationNumber to determine if it is already exist
		Optional<BzbTCompany> companyEntity = companyRepository.findByName_RegistrationNumber(company.getName(),
				company.getRegistrationNumber());
		// Throws Resource Exist Exception if the Company already present with 
		// this Name and RegistrationNumber and the Address is same
		// Otherwise checks for Child Resource (Address) if it is already associated 
		// to different parent Resource (Company)
		if (companyEntity.isPresent()) {
			// Throws Resource Exist Exception if referring to same Resource details
			if (companyEntity.get().getAddress().equals(company.getAddress())) {
				throw new CompanyResourceExistException("A Company with the name - '" + company.getName()
						+ "' is already exist for the given address. "
						+ "The requested operation [" + operation + "] cannot be performed.");
			}
		} else {
			// Finds Company by Address.PostalCode to determine if an Address is already associated 
			// to different Company
			companyEntity = companyRepository.findByAddress_PostalCode(company.getAddress().getPostalCode());
			// Throws Resource Exist Exception if the Company already present with this Address
			if (companyEntity.isPresent()) {
				throw new CompanyResourceExistException(
						"The given address is already associated to another Company - '"
								+ companyEntity.get().getName()
								+ "'. The requested operation [" + operation + "] cannot be performed.");
			}
		}
	}
}