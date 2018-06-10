package com.bayzat.benefits.api.repo;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bayzat.benefits.api.constant.Relationship;
import com.bayzat.benefits.api.model.BzbTDependant;
import com.bayzat.benefits.api.model.BzbTEmployee;

/**
 * Bayzat Benefits Restful API Repository Interface for 
 * <tt>/companies/{companyId}/employees/{employeeId}/dependants</tt> Resource with CRUD operations Implemented with
 * built in {@link JpaRepositry} using embedded H2 Database
 * 
 * @author Mohamed Yusuff
 */
@Repository
public interface IDependantRepository extends JpaRepository<BzbTDependant, Long> {

	/**
	 * Finds All Dependants by its Employee.EmployeeId with a custom JPQL Query
	 * 
	 * @param employeeId refers to an attribute of {@link BzbTEmployee}
	 * @return a <tt>List<{@link BzbTDependant}></tt>
	 */
	@Query("select d from BzbTDependant d where d.employee.employeeId = :employeeId")
	public List<BzbTDependant> findAllByEmployeeId(@Param("employeeId") Long employeeId);
	
	/**
	 * Finds a Dependant by its Employee.EmployeeId and DependantId with a custom JPQL Query
	 * 
	 * @param employeeId refers to an attribute of {@link BzbTEmployee}
	 * @param dependantId refers to an attribute of {@link BzbTDependant}
	 * @return a <tt>Optional<{@link BzbTDependant}></tt>
	 */
	@Query("select d from BzbTDependant d where d.employee.employeeId = :employeeId and d.dependantId = :dependantId")
	public Optional<BzbTDependant> findByEmployeeId_DependantId(@Param("employeeId") Long employeeId,
			@Param("dependantId") Long dependantId);

	/**
	 * Finds a Dependant by its Employee.EmployeeId, FirstName and Relationship with a custom JPQL Query
	 * 
	 * @param employeeId refers to an attribute of {@link BzbTEmployee}
	 * @param firstName refers to an attribute of {@link BzbTDependant}
	 * @param relationship refers to an attribute of {@link BzbTDependant}
	 * @return a <tt>Optional<{@link BzbTDependant}></tt>
	 */
	@Query("select d from BzbTDependant d where d.employee.employeeId = :employeeId"
			+ " and d.firstName = :firstName and d.relationship = :relationship")
	public Optional<BzbTDependant> findByEmployeeId_FirstName_Relationship(@Param("employeeId") Long employeeId,
			@Param("firstName") String firstName, @Param("relationship") Relationship relationship);
}