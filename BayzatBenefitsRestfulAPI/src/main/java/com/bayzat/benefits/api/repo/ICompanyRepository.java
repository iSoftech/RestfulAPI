/**
 * 
 */
package com.bayzat.benefits.api.repo;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bayzat.benefits.api.model.BzbTCompany;

/**
 * @author Mohamed
 *
 */
@Repository
public interface ICompanyRepository extends CrudRepository<BzbTCompany, Long> {

}
