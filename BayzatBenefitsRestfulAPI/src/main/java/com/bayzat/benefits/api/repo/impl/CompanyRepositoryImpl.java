/**
 * 
 */
package com.bayzat.benefits.api.repo.impl;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.bayzat.benefits.api.model.BzbTCompany;
import com.bayzat.benefits.api.repo.ICompanyRepository;

/**
 * @author Mohamed Yusuff
 *
 */
@Repository
public class CompanyRepositoryImpl implements ICompanyRepository {

	@Override
	public <S extends BzbTCompany> S save(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends BzbTCompany> Iterable<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<BzbTCompany> findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean existsById(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterable<BzbTCompany> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<BzbTCompany> findAllById(Iterable<Integer> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(BzbTCompany entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll(Iterable<? extends BzbTCompany> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

}
