package com.bayzat.benefits.api.boot;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.bayzat.benefits.api.model.BzbTAddress;
import com.bayzat.benefits.api.model.BzbTCompany;
import com.bayzat.benefits.api.resource.CompanyResource;
import com.bayzat.benefits.api.service.ICompanyService;
import com.bayzat.benefits.api.service.IDependantService;
import com.bayzat.benefits.api.service.IEmployeeService;

/**
 * Bayzat Benefits Restful API Integration Tests for Company Resource Controller
 * 
 * @author Mohamed Yusuff
 */
@RunWith(SpringRunner.class)
@SpringBootTest (
		webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
		classes = {BayzatBenefitsRestfulAPIApp.class, CompanyResource.class}
)
@AutoConfigureMockMvc
@TestPropertySource(locations="classpath:application-test.properties")
public class CompanyResourceTests {
	
	private static final String APPLICATION_HAL_JSON = "application/hal+json;charset=UTF-8";

	private MediaType contentType = MediaType.valueOf(APPLICATION_HAL_JSON);
	
	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	ICompanyService companyService;
	
	@Autowired
	IEmployeeService employeeService;
	
	@Autowired
	IDependantService dependantService;
	
	private BzbTCompany company = null;
	
	@Before
	public void setUp() {
		company = new BzbTCompany();
		company.setCompanyId(Long.valueOf(1001));
		company.setName("Bayzat");
		BzbTAddress address = new BzbTAddress();
		address.setAddressId(Long.valueOf(1001));
		address.setCity("Dubai");
		address.setPostalCode("391186");
		company.setAddress(address);
	}
	
	/**
	 * Tests All Companies Found
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetAllCompaniesFound() throws Exception {
		// Invokes [/companies] Resource and verifies the Return Response Status is 200 - OK
		// and ContentType is "application/hal+json;charset=UTF-8"
		mockMvc.perform(get("/companies")
				.accept(APPLICATION_HAL_JSON)).andExpect(status().isOk())
		.andExpect(content().contentType(contentType));
	}
	
	/**
	 * Tests All Companies Not Found
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetAllCompaniesInvalidResource() throws Exception {
		// Invokes [/companies] Resource and verifies the Return Response Status is 404 - NOT_FOUND
		mockMvc.perform(get("/companiesTestResource")
				.accept(APPLICATION_HAL_JSON)).andExpect(status().isNotFound());
	}
	
	/**
	 * Tests a Company Found
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetCompanyFound() throws Exception {
		// Invokes [/companies] Resource and verifies the Return Response Status is 200 - OK
		// and ContentType is "application/hal+json;charset=UTF-8"
		mockMvc.perform(get("/companies/" + company.getCompanyId())
				.accept(APPLICATION_HAL_JSON)).andExpect(status().isOk())
		.andExpect(content().contentType(contentType))
		.andExpect(jsonPath("$.name", is((company.getName()))))
		.andExpect(jsonPath("$.address.city", is((company.getAddress().getCity()))))
		.andExpect(jsonPath("$.address.postalCode", is((company.getAddress().getPostalCode()))));
	}
	
	/**
	 * Tests a Company Not Found
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetCompanyNotFound() throws Exception {
		// Invokes [/companies] Resource and verifies the Return Response Status is 404 - NOT_FOUND
		mockMvc.perform(get("/companies/" + Long.valueOf(5001))
				.accept(APPLICATION_HAL_JSON)).andExpect(status().isNotFound())
		.andExpect(content().contentType(contentType));
	}
	
	/**
	 * Tests a Company for Bad Request
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetCompanyInvalidArgument() throws Exception {
		// Invokes [/companies] Resource and verifies the Return Response Status is 400 - BAD_REQUEST
		// and ContentType is "application/hal+json;charset=UTF-8"
		mockMvc.perform(get("/companies/testArg")
				.accept(APPLICATION_HAL_JSON)).andExpect(status().isBadRequest());
	}
	
	/**
	 * Tests Delete a Company Not Found
	 * 
	 * @throws Exception
	 */
	@Test
	public void testDeleteCompanyNotFound() throws Exception {
		// Invokes [/companies] Resource and verifies the Return Response Status is 404 - NOT_FOUND
		// and ContentType is "application/hal+json;charset=UTF-8"
		mockMvc.perform(get("/companies/" + Long.valueOf(5001))
				.accept(APPLICATION_HAL_JSON)).andExpect(status().isNotFound())
		.andExpect(content().contentType(contentType));
	}
}