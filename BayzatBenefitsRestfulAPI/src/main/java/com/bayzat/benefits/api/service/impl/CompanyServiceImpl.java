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
		List<BzbTCompany> companies = new ArrayList<>(0);
		companyRepository.findAll().forEach(companies::add);
		return companies;
	}

	@Override
	public BzbTCompany getCompany(Long id) {
		return getAllCompanies().stream().filter(c -> c.getCompanyId().equals(id)).findFirst().get();
	}

	@Override
	public void addCompany(BzbTCompany company) {
		companyRepository.save(company);
	}

	@Override
	public void updateCompany(Long id, BzbTCompany company) {
		company.setCompanyId(id);
		companyRepository.save(company);
	}

	@Override
	public void deleteCompany(Long id) {
		companyRepository.deleteById(id);
	}

}
