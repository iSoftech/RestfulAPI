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
 * @author Mohamed Yusuff
 *
 */
@Repository
public interface ICompanyRepository extends JpaRepository<BzbTCompany, Long> {

	/*public Optional<BzbTCompany> findByName(@Param("name") String name);*/
	
	@Query("select c from BzbTCompany c "
			+ "inner join BzbTAddress a on a.addressId = c.address.addressId "
			+ "where a.postalCode = :postalCode")
	public Optional<BzbTCompany> findByAddress_PostalCode(@Param("postalCode") String postalCode);

	@Query("select c from BzbTCompany c where c.name = :name and c.registrationNumber = :registrationNumber")
	public Optional<BzbTCompany> findByName_RegistrationNumber(@Param("name") String name,
			@Param("registrationNumber") String registrationNumber);
}
