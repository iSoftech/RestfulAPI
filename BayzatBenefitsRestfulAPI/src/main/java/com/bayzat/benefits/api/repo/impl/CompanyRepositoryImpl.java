/**
 * 
 */
package com.bayzat.benefits.api.repo.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.bayzat.benefits.api.model.BzbTAddress;
import com.bayzat.benefits.api.model.BzbTCompany;
import com.bayzat.benefits.api.repo.ICompanyRepository;

/**
 * @author Mohamed Yusuff
 *
 */
@Repository
public class CompanyRepositoryImpl implements ICompanyRepository {

	private static Map<Integer, BzbTCompany> companies = new HashMap<>();
	
	static {
		companies.put(1001, new BzbTCompany("Cognizant", "C12345", "+6569256536", "contact@cognizant.com",
				"www.cognizant.com", new BzbTAddress("Plaza8@CBP", "07-01/06", "1 Change Business Park Central 1", "", "Singapore", "Singapore", "Singapore", "486025")));
		companies.put(1002, new BzbTCompany("UBS AG", "U12345", "+6564954871", "contact@ubs.com",
				"www.ubs.com", new BzbTAddress("Hansapoint@CBP", "01-01", "10 Change Business Park Central 2", "", "Singapore", "", "Singapore", "486030")));
		companies.put(1003, new BzbTCompany("Bayzat", "B12345", "+96197168137", "contact@bayzat.com",
				"www.bayzat.com", new BzbTAddress("Control Tower", "10", "Motor City", "", "Dubai", "", "United Arab Emirates", "391186")));
	}
	
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
