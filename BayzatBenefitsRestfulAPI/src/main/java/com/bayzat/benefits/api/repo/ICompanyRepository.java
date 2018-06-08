/**
 * 
 */
package com.bayzat.benefits.api.repo;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bayzat.benefits.api.model.BzbTCompany;

/**
 * Bayzat Benefits Restful API Repository Interface for <tt>/companies</tt> Resource with CRUD operations Implemented
 * with built in {@link JpaRepositry} using embedded H2 Database
 * 
 * @author Mohamed Yusuff
 */
@Repository
public interface ICompanyRepository extends JpaRepository<BzbTCompany, Long> {

	/**
	 * Finds a Company by its Address.PostalCode with a custom HQL Query
	 * 
	 * @param postalCode refers to an attribute of {@link BzbTAddress}
	 * @return a <tt>Optional<{@link BzbTCompany}></tt>
	 */
	@Query("select c from BzbTCompany c "
			+ "inner join BzbTAddress a on a.addressId = c.address.addressId "
			+ "where a.postalCode = :postalCode")
	public Optional<BzbTCompany> findByAddress_PostalCode(@Param("postalCode") String postalCode);

	/**
	 * Finds a Company by its Name and RegistrationNumber with a custom HQL Query
	 * 
	 * @param name refers to an attribute of {@link BzbTCompany}
	 * @param registrationNumber refers to an attribute of {@link BzbTCompany}
	 * @return a <tt>Optional<{@link BzbTCompany}></tt>
	 */
	@Query("select c from BzbTCompany c where c.name = :name and c.registrationNumber = :registrationNumber")
	public Optional<BzbTCompany> findByName_RegistrationNumber(@Param("name") String name,
			@Param("registrationNumber") String registrationNumber);
}
