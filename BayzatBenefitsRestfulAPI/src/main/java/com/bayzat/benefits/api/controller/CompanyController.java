package com.bayzat.benefits.api.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bayzat.benefits.api.model.BzbTAddress;
import com.bayzat.benefits.api.model.BzbTCompany;

/**
 * @author Mohamed Yusuff
 *
 */
@RestController
public class CompanyController {

	@RequestMapping("/companies")
	public List<BzbTCompany> getAllCompanies() {
		return Collections.singletonList(new BzbTCompany("Cognizant", "C12345", "+6569256536", "contact@cognizant.com",
				"www.cognizant.com", new BzbTAddress()));
	}
}
