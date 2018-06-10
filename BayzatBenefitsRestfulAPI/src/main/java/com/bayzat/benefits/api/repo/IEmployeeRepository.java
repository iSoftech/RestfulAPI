package com.bayzat.benefits.api.repo;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bayzat.benefits.api.model.BzbTAddress;
import com.bayzat.benefits.api.model.BzbTCompany;
import com.bayzat.benefits.api.model.BzbTEmployee;

/**
 * Bayzat Benefits Restful API Repository Interface for <tt>/companies/{companyId}/employees</tt> Resource with CRUD
 * operations Implemented with built in {@link JpaRepositry} using embedded H2 Database
 * 
 * @author Mohamed Yusuff
 */
@Repository
public interface IEmployeeRepository extends JpaRepository<BzbTEmployee, Long> {

	/**
	 * Finds a list of Employees by its Company.CompanyId with a custom JPQL Query
	 * 
	 * @param companyId refers to an attribute of {@link BzbTCompany}
	 * @return a list of {@link BzbTEmployee}
	 */
	@Query("select e from BzbTEmployee e where e.company.companyId = :companyId")
	public List<BzbTEmployee> findByCompanyId(@Param("companyId") Long companyId);
	
	/**
	 * Finds an Employee by its Company.CompanyId and its EmployeeId with a custom JPQL Query
	 * 
	 * @param companyId refers to an attribute of {@link BzbTCompany}
	 * @param employeeId refers to an attribute of {@link BzbTEmployee}
	 * @return a <tt>Optional<{@link BzbTEmployee}></tt>
	 */
	@Query("select e from BzbTEmployee e where e.company.companyId = :companyId and e.employeeId = :employeeId")
	public Optional<BzbTEmployee> findByCompanyId_EmployeeId(@Param("companyId") Long companyId,
			@Param("employeeId") Long employeeId);
	
	/**
	 * Finds an Employee by its Company.CompanyId and its EmployeeCode with a custom JPQL Query
	 * 
	 * @param companyId refers to an attribute of {@link BzbTCompany}
	 * @param employeeCode refers to an attribute of {@link BzbTEmployee}
	 * @return a <tt>Optional<{@link BzbTEmployee}></tt>
	 */
	@Query("select e from BzbTEmployee e where e.company.companyId = :companyId and e.employeeCode = :employeeCode")
	public Optional<BzbTEmployee> findByCompanyId_EmployeeCode(@Param("companyId") Long companyId,
			@Param("employeeCode") String employeeCode);
	
	/**
	 * Finds an Employee by its Address.PostalCode with a custom JPQL Query
	 * 
	 * @param postalCode refers to an attribute of {@link BzbTAddress}
	 * @return a <tt>Optional<{@link BzbTEmployee}></tt>
	 */
	@Query("select e from BzbTEmployee e "
			+ "inner join BzbTAddress a on a.addressId = e.address.addressId "
			+ "where a.postalCode = :postalCode")
	public Optional<BzbTEmployee> findByAddress_PostalCode(@Param("postalCode") String postalCode);
}