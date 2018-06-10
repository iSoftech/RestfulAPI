/**
 * 
 */
package com.bayzat.benefits.api.service;

import java.util.List;

import com.bayzat.benefits.api.model.BzbTDependant;

/**
 * Bayzat Benefits Restful API Service Interface for <tt>/companies/{companyId}/employees/{employeeId}/dependants</tt>
 * Resource with CRUD operations.
 * 
 * @author Mohamed Yusuff
 */
public interface IDependantService {

	/**
	 * Returns all Dependants by its Employee
	 * 
	 * @param companyId refers to attribute {@code employee.company.companyId} 
	 * @param employeeId refers to attribute {@code employee.employeeId}
	 * @return a list of {@link BzbTDependant}
	 */
	public List<BzbTDependant> getAllDependants(Long companyId, Long employeeId);
	
	/**
	 * Returns requested Dependant by its Employee
	 * 
	 * @param companyId refers to attribute {@code employee.company.companyId} 
	 * @param employeeId refers to attribute {@code employee.employeeId}
	 * @param dependantId refers to attribute {@code dependantId}
	 * @return a {@link BzbTDependant} identified by its dependantId
	 */
	public BzbTDependant getDependant(Long companyId, Long employeeId, Long dependantId);
	
	/**
	 * Adds a new Dependant by its Employee
	 * 
	 * @param companyId refers to attribute {@code employee.company.companyId} 
	 * @param employeeId refers to attribute {@code employee.employeeId}
	 * @param dependant refers to a new instance of {@link BzbTDependant}
	 * @return a newly added {@link BzbTDependant}
	 */
	public BzbTDependant addDependant(Long companyId, Long employeeId, BzbTDependant dependant);
	
	/**
	 * Updates an existing Dependant by its Employee
	 * 
	 * @param companyId refers to attribute {@code employee.company.companyId} 
	 * @param employeeId refers to attribute {@code employee.employeeId}
	 * @param dependantId refers to attribute {@code dependantId}
	 * @param dependant refers to an edited instance of {@link BzbTDependant}
	 * @return an updated {@link BzbTDependant} identified by its dependantId
	 */
	public BzbTDependant updateDependant(Long companyId, Long employeeId, Long dependantId, BzbTDependant dependant);
	
	/**
	 * Deletes an Existing Dependant by its Employee
	 * 
	 * @param companyId refers to attribute {@code employee.company.companyId} 
	 * @param employeeId refers to attribute {@code employee.employeeId}
	 * @param dependantId refers to attribute {@code dependantId}
	 */
	public void deleteDependant(Long companyId, Long employeeId, Long dependantId);
}
