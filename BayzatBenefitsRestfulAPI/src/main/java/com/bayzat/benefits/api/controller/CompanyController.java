package com.bayzat.benefits.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bayzat.benefits.api.model.BzbTCompany;
import com.bayzat.benefits.api.service.ICompanyService;

/**
 * @author Mohamed Yusuff
 *
 */
@RestController
public class CompanyController {

	@Autowired
	private ICompanyService companyService;
	
	@RequestMapping(value="/companies", method=RequestMethod.GET)
	public List<BzbTCompany> getAllCompanies() {
		return companyService.getAllCompanies();
	}
	
	@RequestMapping(value="/companies/{id}", method=RequestMethod.GET)
	public void getCompany(@PathVariable Integer id) {
		companyService.getCompany(id);
	}

	@RequestMapping(value="/companies", method=RequestMethod.POST)
	public void addCompany(@RequestBody BzbTCompany company) {
		companyService.addCompany(company);
	}
	
	@RequestMapping(value="/companies/{id}", method=RequestMethod.PUT)
	public void updateCompany(@PathVariable Integer id, @RequestBody BzbTCompany company) {
		companyService.updateCompany(id, company);
	}
	
	@RequestMapping(value="/companies/{id}", method=RequestMethod.DELETE)
	public void deleteCompany(@PathVariable Integer id) {
		companyService.deleteCompany(id);
	}
}
