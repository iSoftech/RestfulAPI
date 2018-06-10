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
import com.bayzat.benefits.api.exception.CompanyResourceNotExistException;
import com.bayzat.benefits.api.exception.EmployeeResourceExistException;
import com.bayzat.benefits.api.exception.EmployeeResourceNotExistException;
import com.bayzat.benefits.api.model.BzbTEmployee;
import com.bayzat.benefits.api.repo.ICompanyRepository;
import com.bayzat.benefits.api.repo.IEmployeeRepository;
import com.bayzat.benefits.api.service.IEmployeeService;

/**
 * Bayzat Benefits Restful API Service Implementation for <tt>/companies/{companyId}/employees</tt> Resource with CRUD
 * operations.
 * 
 * @author Mohamed Yusuff
 */
@Service
public class EmployeeServiceImpl implements IEmployeeService {

	// To Log the Error Messages in case of Exceptions
	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceImpl.class);

	@Autowired
	private ICompanyRepository companyRepository;
	
	@Autowired
	private IEmployeeRepository employeeRepository;
	
	/**
	 * Returns all Employees by its Company
	 * 
	 * @param companyId refers to attribute {@code company.companyId}
	 * @return a list of {@link BzbTEmployee}
	 */
	@Override
	public List<BzbTEmployee> getAllEmployees(Long companyId) {
		List<BzbTEmployee> employees = null;
		try {
			String operation = HttpMethod.GET.name();
			// Validates and Throws Exception for the Existing Resource - Company
			validateExistingResourceAndThrowException(companyId, null, operation);
			// Finds all Employees Entities by its Company
			employees = employeeRepository.findByCompanyId(companyId);
		} catch (Exception exc) {
			LOGGER.error("Error occurred when trying to fetch all Employees for the Company with CompanyId - "
					+ companyId, exc);
			throw new BayzatTechnicalException(exc.getMessage());
		}
		return employees;
	}

	/**
	 * Returns requested Employee by its Company
	 * 
	 * @param companyId refers to attribute {@code company.companyId} 
	 * @param employeeId refers to attribute {@code employeeId}
	 * @return a {@link BzbTEmployee} identified by its id
	 */
	@Override
	public BzbTEmployee getEmployee(Long companyId, Long employeeId) {
		BzbTEmployee employee = null;
		try {
			String operation = HttpMethod.GET.name();
			// Validates and Throws Exception for the Existing Resource - Company and Employee
			validateExistingResourceAndThrowException(companyId, employeeId, operation);
			// Retrieves requested Employee if present
			employee = employeeRepository.getOne(employeeId);
		} catch (Exception exc) {
			LOGGER.error("Error occurred when trying to fetch an Employee with EmployeeId - " + employeeId
					+ " for the Company with CompanyId - " + companyId, exc);
			throw new BayzatTechnicalException(exc.getMessage());
		}
		return employee;
	}

	/**
	 * Adds a new Employee by its Company
	 * 
	 * @param companyId refers to attribute {@code company.companyId} 
	 * @param employee refers to a new instance of {@link BzbTEmployee}
	 * @return a newly added {@link BzbTEmployee}
	 */
	@Override
	public BzbTEmployee addEmployee(Long companyId, BzbTEmployee employee) {
		BzbTEmployee savedEmployee = null;
		try {
			String operation = HttpMethod.POST.name();
			// Validates and Throws Exception for the Existing Resource - Company
			validateExistingResourceAndThrowException(companyId, null, operation);
			// Validates and Throws Exception for the New Resource - Employee
			validateNewResourceAndThrowException(companyId, employee, operation);
			// Sets the requested Company
			employee.setCompany(companyRepository.getOne(companyId));
			// Saves and Returns the newly added Employee for the Company
			savedEmployee = employeeRepository.save(employee);
		} catch (Exception exc) {
			LOGGER.error("Error occurred when trying to add a new Employee for the Company with CompanyId - "
					+ companyId, exc);
			throw new BayzatTechnicalException(exc.getMessage());
		}
		return savedEmployee;
	}

	/**
	 * Updates an existing Employee by its Company
	 * 
	 * @param companyId refers to attribute {@code company.companyId} 
	 * @param employeeId refers to attribute {@code employeeId}
	 * @param employee refers to an edited instance of {@link BzbTEmployee}
	 * @return an updated {@link BzbTEmployee} identified by its id
	 */
	@Override
	public BzbTEmployee updateEmployee(Long companyId, Long employeeId, BzbTEmployee employee) {
		BzbTEmployee updatedEmployee = null;
		try {
			String operation = HttpMethod.PUT.name();
			// Validates and Throws Exception for the Existing Resource - Company and Employee
			validateExistingResourceAndThrowException(companyId, employeeId, operation);
			// Retrieves an Employee by its EmployeeId
			BzbTEmployee employeeEntity = employeeRepository.getOne(employeeId);
			// Sets the requested Company
			employee.setCompany(companyRepository.getOne(companyId));
			// Sets the request EmployeeId
			employee.setEmployeeId(employeeId);
			// Updates the AddressId If not present to avoid creating duplicate Address Entities
			if (null != employee.getAddress() && null == employee.getAddress().getAddressId()) {
				employee.getAddress().setAddressId(employeeEntity.getAddress().getAddressId());
			}
			// Saves and Returns the updated Employee for the Company
			updatedEmployee = employeeRepository.save(employee);
		} catch (Exception exc) {
			LOGGER.error("Error occurred when trying to update an existing Employee with EmployeeId - " + employeeId
					+ " for the Company with CompanyId - " + companyId, exc);
			throw new BayzatTechnicalException(exc.getMessage());
		}
		return updatedEmployee;
	}
	
	/**
	 * Deletes an Existing Employee by its Company
	 * 
	 * @param companyId refers to attribute {@code company.companyId} 
	 * @param employeeId refers to attribute {@code employeeId}
	 */
	@Override
	public void deleteEmployee(Long companyId, Long employeeId) {
		try {
			String operation = HttpMethod.DELETE.name();
			// Validates and Throws Exception for the Existing Resource - Company and Employee
			validateExistingResourceAndThrowException(companyId, employeeId, operation);
			// Deletes the requested Employee by its EmployeeId if present
			employeeRepository.deleteById(employeeId);
		} catch (Exception exc) {
			LOGGER.error("Error occurred when trying to delete an existing Employee with EmployeeId - " + employeeId
					+ " for the Company with CompanyId - " + companyId, exc);
			throw new BayzatTechnicalException(exc.getMessage());
		}
	}

	/**
	 * Validates and Throws Exception for the Existing Resource - Company and Employee
	 * 
	 * @param companyId refers to attribute {@code company.companyId} 
	 * @param employeeId refers to attribute {@code employeeId}
	 * @param operation refers to attribute of {@link HttpMethod}
	 */
	private void validateExistingResourceAndThrowException(Long companyId, Long employeeId, String operation) {
		// Throws Resource Not Exist Exception if the Company not present
		if (!companyRepository.existsById(companyId)) {
			throw new CompanyResourceNotExistException("The requested Company with CompanyId - " + companyId
					+ " doesn't exist. The requested operation [" + operation + "] cannot be performed.");
		}
		// Validates Employee Exist if applicable
		if (null != employeeId) {
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
		}
	}

	/**
	 * Validates and Throws Exception for the New Resource - Employee
	 * 
	 * @param companyId refers to attribute {@code company.companyId} 
	 * @param employeeId refers to attribute {@code employeeId}
	 * @param operation refers to attribute of {@link HttpMethod}
	 */
	private void validateNewResourceAndThrowException(Long companyId, BzbTEmployee employee, String operation) {
		// Finds an Employee by its Company.CompanyId and EmployeeCode to determine 
		// if the Employee already exist for the Company
		Optional<BzbTEmployee> employeeEntity = employeeRepository.findByCompanyId_EmployeeCode(companyId,
				employee.getEmployeeCode());
		// Throws Resource Exist Exception if the Employee already present for the Company with this EmployeeCode
		if (employeeEntity.isPresent()) {
			throw new EmployeeResourceExistException("An Employee with the EmployeeCode - "
					+ employee.getEmployeeCode() + " is already exist for the Company with CompanyId - " + companyId
					+ ". The requested operation [" + operation + "] cannot be performed.");
		} else {
			// Finds Employee by Address.PostalCode to determine if an Address is already associated 
			// to different Employee
			employeeEntity = employeeRepository.findByAddress_PostalCode(employee.getAddress().getPostalCode());
			// Throws Resource Exist Exception if an Employee is already present with this Address
			if (employeeEntity.isPresent()) {
				throw new EmployeeResourceExistException(
						"The given Address is already associated to another Employee - '"
								+ employeeEntity.get().getEmployeeCode()
								+ "'. The requested operation [" + operation + "] cannot be performed.");
			}
		}
	}
}