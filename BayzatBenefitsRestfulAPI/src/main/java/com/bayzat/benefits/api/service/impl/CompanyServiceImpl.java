/**
 * 
 */
package com.bayzat.benefits.api.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bayzat.benefits.api.model.BzbTCompany;
import com.bayzat.benefits.api.repo.ICompanyRepository;
import com.bayzat.benefits.api.service.ICompanyService;

/**
 * @author Mohamed Yusuff
 *
 */
@Service
public class CompanyServiceImpl implements ICompanyService {

	@Autowired
	private ICompanyRepository companyRepository;
	
	@Override
	public List<BzbTCompany> getAllCompanies() {
		//return companyRepository.findAll();
		return new ArrayList<BzbTCompany>();
	}

	@Override
	public BzbTCompany getCompany(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addCompany(BzbTCompany company) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateCompany(Integer id, BzbTCompany company) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteCompany(Integer id) {
		// TODO Auto-generated method stub
		
	}

}
