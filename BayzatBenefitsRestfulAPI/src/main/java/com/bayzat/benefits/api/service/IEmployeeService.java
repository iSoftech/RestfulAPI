/**
 * 
 */
package com.bayzat.benefits.api.service;

import java.util.List;

import com.bayzat.benefits.api.model.BzbTEmployee;

/**
 * Bayzat Benefits Restful API Service Interface for <tt>/companies/{companyId}/employees</tt> Resource with CRUD
 * operations.
 * 
 * @author Mohamed Yusuff
 */
public interface IEmployeeService {

	/**
	 * Returns all Employees by its Company
	 * 
	 * @param companyId refers to attribute {@code company.companyId}
	 * @return a list of {@link BzbTEmployee}
	 */
	public List<BzbTEmployee> getAllEmployees(Long companyId);
	
	/**
	 * Returns requested Employee by its Company
	 * 
	 * @param companyId refers to attribute {@code company.companyId} 
	 * @param employeeId refers to attribute {@code employeeId}
	 * @return a {@link BzbTEmployee} identified by its id
	 */
	public BzbTEmployee getEmployee(Long companyId, Long employeeId);
	
	/**
	 * Adds a new Employee by its Company
	 * 
	 * @param companyId refers to attribute {@code company.companyId} 
	 * @param employee refers to a new instance of {@link BzbTEmployee}
	 * @return a newly added {@link BzbTEmployee}
	 */
	public BzbTEmployee addEmployee(Long companyId, BzbTEmployee employee);
	
	/**
	 * Updates an existing Employee by its Company
	 * 
	 * @param companyId refers to attribute {@code company.companyId} 
	 * @param employeeId refers to attribute {@code employeeId}
	 * @param employee refers to an edited instance of {@link BzbTEmployee}
	 * @return an updated {@link BzbTEmployee} identified by its id
	 */
	public BzbTEmployee updateEmployee(Long companyId, Long employeeId, BzbTEmployee employee);
	
	/**
	 * Deletes an Existing Employee by its Company
	 * 
	 * @param companyId refers to attribute {@code company.companyId} 
	 * @param employeeId refers to attribute {@code employeeId}
	 */
	public void deleteEmployee(Long companyId, Long employeeId);
}
