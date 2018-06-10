/**
 * 
 */
package com.bayzat.benefits.api.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.bayzat.benefits.api.exception.BayzatTechnicalException;
import com.bayzat.benefits.api.exception.CompanyResourceNotExistException;
import com.bayzat.benefits.api.exception.DependantResourceExistException;
import com.bayzat.benefits.api.exception.DependantResourceNotExistException;
import com.bayzat.benefits.api.exception.EmployeeResourceNotExistException;
import com.bayzat.benefits.api.model.BzbTDependant;
import com.bayzat.benefits.api.model.BzbTEmployee;
import com.bayzat.benefits.api.repo.ICompanyRepository;
import com.bayzat.benefits.api.repo.IDependantRepository;
import com.bayzat.benefits.api.repo.IEmployeeRepository;
import com.bayzat.benefits.api.service.IDependantService;

/**
 * Bayzat Benefits Restful API Service Implementation for <tt>/companies/{companyId}/employees/{employeeId}/dependants</tt>
 * Resource with CRUD operations.
 * 
 * @author Mohamed Yusuff
 */
@Service
public class DependantServiceImpl implements IDependantService {

	// To Log the Error Messages in case of Exceptions
	private static final Logger LOGGER = LoggerFactory.getLogger(DependantServiceImpl.class);

	@Autowired
	private ICompanyRepository companyRepository;
	
	@Autowired
	private IEmployeeRepository employeeRepository;
	
	@Autowired
	private IDependantRepository dependantRepository;
	
	/**
	 * Returns all Dependants by its Employee
	 * 
	 * @param companyId refers to attribute {@code employee.company.companyId} 
	 * @param employeeId refers to attribute {@code employee.employeeId}
	 * @return a list of {@link BzbTDependant}
	 */
	public List<BzbTDependant> getAllDependants(Long companyId, Long employeeId) {
		List<BzbTDependant> dependants = null;
		try {
			String operation = HttpMethod.GET.name();
			// Validates and Throws Exception for availability of Company and Employee
			validateExistingResourceAndThrowException(companyId, employeeId, null, operation);
			// Finds all Employees Entities by its Company
			dependants = dependantRepository.findAllByEmployeeId(employeeId);
		} catch (Exception exc) {
			LOGGER.error("Error occurred when trying to fetch all Dependants for the Employee with EmployeeId - "
					+ employeeId + " and CompanyId - " + companyId, exc);
			HttpStatus httpStatus = AnnotationUtils.findAnnotation(exc.getClass(), ResponseStatus.class).code();
			throw new BayzatTechnicalException(exc.getMessage(), httpStatus);
		}
		return dependants;
	}
	
	/**
	 * Returns requested Dependant by its Employee
	 * 
	 * @param companyId refers to attribute {@code employee.company.companyId} 
	 * @param employeeId refers to attribute {@code employee.employeeId}
	 * @param dependantId refers to attribute {@code dependantId}
	 * @return a {@link BzbTDependant} identified by its dependantId
	 */
	public BzbTDependant getDependant(Long companyId, Long employeeId, Long dependantId) {
		BzbTDependant dependant = null;
		try {
			String operation = HttpMethod.GET.name();
			// Validates and Throws Exception for availability of Company, Employee and Dependant
			validateExistingResourceAndThrowException(companyId, employeeId, dependantId, operation);
			// Retrieves requested Dependant if present
			dependant = dependantRepository.getOne(dependantId);
		} catch (Exception exc) {
			LOGGER.error("Error occurred when trying to fetch a dependant with dependantId - " + dependantId
						+ " and EmployeeId - " + employeeId + " for the company with CompanyId - " + companyId, exc);
			HttpStatus httpStatus = AnnotationUtils.findAnnotation(exc.getClass(), ResponseStatus.class).code();
			throw new BayzatTechnicalException(exc.getMessage(), httpStatus);
		}
		return dependant;
	}
	
	/**
	 * Adds a new Dependant by its Employee
	 * 
	 * @param companyId refers to attribute {@code employee.company.companyId} 
	 * @param employeeId refers to attribute {@code employee.employeeId}
	 * @param dependant refers to a new instance of {@link BzbTDependant}
	 * @return a newly added {@link BzbTDependant}
	 */
	public BzbTDependant addDependant(Long companyId, Long employeeId, BzbTDependant dependant) {
		BzbTDependant savedDependant = null;
		try {
			String operation = HttpMethod.POST.name();
			// Validates and Throws Exception for availability of Company and Employee
			validateExistingResourceAndThrowException(companyId, employeeId, null, operation);
			// Validates and Throws Exception for New Resource - Dependant
			validateNewResourceAndThrowException(companyId, employeeId, dependant, operation);
			// Sets the requested Employee
			dependant.setEmployee(employeeRepository.getOne(employeeId));
			// Saves and Returns the newly added Dependant for the Employee
			savedDependant = dependantRepository.save(dependant);
		} catch (Exception exc) {
			LOGGER.error("Error occurred when trying to add a new Dependant for the Employee with EmployeeId - " + employeeId
					+ " and Company with CompanyId - " + companyId, exc);
			HttpStatus httpStatus = AnnotationUtils.findAnnotation(exc.getClass(), ResponseStatus.class).code();
			throw new BayzatTechnicalException(exc.getMessage(), httpStatus);
		}
		return savedDependant;
	}

	/**
	 * Updates an existing Dependant by its Employee
	 * 
	 * @param companyId refers to attribute {@code employee.company.companyId} 
	 * @param employeeId refers to attribute {@code employee.employeeId}
	 * @param dependantId refers to attribute {@code dependantId}
	 * @param dependant refers to an edited instance of {@link BzbTDependant}
	 * @return an updated {@link BzbTDependant} identified by its dependantId
	 */
	public BzbTDependant updateDependant(Long companyId, Long employeeId, Long dependantId, BzbTDependant dependant) {
		BzbTDependant updatedDependant = null;
		try {
			String operation = HttpMethod.PUT.name();
			// Validates and Throws Exception for availability of Company, Employee and Dependant
			validateExistingResourceAndThrowException(companyId, employeeId, dependantId, operation);
			// Retrieves a Dependant by its DependantId
			BzbTDependant dependantEntity = dependantRepository.getOne(dependantId);
			// Sets the requested Employee
			dependant.setEmployee(employeeRepository.getOne(employeeId));
			// Sets the request EmployeeId
			dependant.setDependantId(dependantId);
			// Updates the AddressId If not present to avoid creating duplicate Address Entities
			if (null != dependant.getAddress() && null == dependant.getAddress().getAddressId()) {
				dependant.getAddress().setAddressId(dependantEntity.getAddress().getAddressId());
			}
			// Saves and Returns the updated Dependant for the Employee
			updatedDependant = dependantRepository.save(dependant);
		} catch (Exception exc) {
			LOGGER.error("Error occurred when trying to update an existing Dependant with DependantId - " + dependantId
					+ " and Employee with EmployeeId - " + employeeId + " for the Company with CompanyId - "
					+ companyId, exc);
			HttpStatus httpStatus = AnnotationUtils.findAnnotation(exc.getClass(), ResponseStatus.class).code();
			throw new BayzatTechnicalException(exc.getMessage(), httpStatus);
		}
		return updatedDependant;
	}
	
	/**
	 * Deletes an Existing Dependant by its Employee
	 * 
	 * @param companyId refers to attribute {@code employee.company.companyId} 
	 * @param employeeId refers to attribute {@code employee.employeeId}
	 * @param dependantId refers to attribute {@code dependantId}
	 */
	public void deleteDependant(Long companyId, Long employeeId, Long dependantId) {
		try {
			String operation = HttpMethod.DELETE.name();
			// Validates and Throws Exception for availability of Company, Employee and Dependant
			validateExistingResourceAndThrowException(companyId, employeeId, dependantId, operation);
			// Deletes the requested Dependant by its DependantId if present
			dependantRepository.deleteById(dependantId);
		} catch (Exception exc) {
			LOGGER.error("Error occurred when trying to delete an existing Dependant by its DependantId - "
					+ dependantId + " and Employee with EmployeeId - " + employeeId
					+ " for the Company with CompanyId - " + companyId, exc);
			HttpStatus httpStatus = AnnotationUtils.findAnnotation(exc.getClass(), ResponseStatus.class).code();
			throw new BayzatTechnicalException(exc.getMessage(), httpStatus);
		}
	}

	/**
	 * Validates and Throws Exception for availability of Company, Employee and Dependant
	 * 
	 * @param companyId refers to attribute {@code employee.company.companyId} 
	 * @param employeeId refers to attribute {@code employee.employeeId}
	 * @param dependantId refers to attribute {@code dependantId}
	 * @param operation refers to attribute of {@link HttpMethod}
	 */
	private void validateExistingResourceAndThrowException(Long companyId, Long employeeId, Long dependantId,
			String operation) {
		// Throws Resource Not Exist Exception if the Company not present
		if (!companyRepository.existsById(companyId)) {
			throw new CompanyResourceNotExistException("The requested Company with CompanyId - " + companyId
					+ " doesn't exist. The requested operation getEmployee(companyId, employeeId)"
					+ " [" + operation + "] cannot be performed.");
		}
		// Finds an Employee by its Company.CompanyId and EmployeeId to determine 
		// if the Employee is present for the Company
		Optional<BzbTEmployee> employeeEntity = employeeRepository.findByCompanyId_EmployeeId(companyId,
				employeeId);
		// Throws Resource Not Exist Exception if the Employee not present
		if (!employeeEntity.isPresent()) {
			throw new EmployeeResourceNotExistException("The requested Employee with EmployeeId - " + employeeId
					+ " for the Company with CompanyId - " + companyId + " doesn't exist. The requested operation"
					+ " [" + operation + "] cannot be performed.");
		}
		// Validates Dependant Exist if applicable
		if (null != dependantId) {
			// Finds a Dependant by its Employee.EmployeeId and DependantId to determine 
			// if the Dependant is present for the Employee
			Optional<BzbTDependant> dependantEntity = dependantRepository.findByEmployeeId_DependantId(employeeId,
					dependantId);
			// Throws Resource Not Exist Exception if the Dependant not present
			if (!dependantEntity.isPresent()) {
				throw new DependantResourceNotExistException(
						"The requested Dependant with DependantId - " + dependantId + " and Employee with EmployeeId - "
								+ employeeId + " for the Company with CompanyId - " + companyId
								+ " doesn't exist. The requested operation [" + operation + "] cannot be performed.");
			}
		}
	}
	
	/**
	 * Validates and Throws Exception for New Resource - Dependant
	 * 
	 * @param companyId refers to attribute {@code employee.company.companyId} 
	 * @param employeeId refers to attribute {@code employee.employeeId}
	 * @param dependant refers to a new instance of {@link BzbTDependant}
	 * @param operation refers to attribute of {@link HttpMethod}
	 */
	private void validateNewResourceAndThrowException(Long companyId, Long employeeId, BzbTDependant dependant,
			String operation) {
		// Finds a Dependant by its Employee.EmployeeId, FirstName and Relationship to determine 
		// if the Dependant already exist for the Employee
		Optional<BzbTDependant> dependantEntity = dependantRepository.findByEmployeeId_FirstName_Relationship(employeeId, 
				dependant.getFirstName(), dependant.getRelationship());
		// Throws Resource Exist Exception if the Employee already present for the Company with this EmployeeCode
		if (dependantEntity.isPresent()) {
			throw new DependantResourceExistException("A Dependant with DependantId - "
					+ dependantEntity.get().getDependantId() + ", FirstName - " + dependant.getFirstName()
					+ " and Relationship - " + dependant.getRelationship().getValue()
					+ " is already exist for the Employee with EmployeeId - " + employeeId
					+ " and its Company with CompanyId - " + companyId + ". The requested operation [" + operation
					+ "] cannot be performed.");
		}
	}
}