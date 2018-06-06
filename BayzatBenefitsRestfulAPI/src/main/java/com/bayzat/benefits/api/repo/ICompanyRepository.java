/**
 * 
 */
package com.bayzat.benefits.api.repo;


import org.springframework.data.repository.CrudRepository;

import com.bayzat.benefits.api.model.BzbTCompany;

/**
 * @author Mohamed
 *
 */
public interface ICompanyRepository extends CrudRepository<BzbTCompany, Integer> {

}
