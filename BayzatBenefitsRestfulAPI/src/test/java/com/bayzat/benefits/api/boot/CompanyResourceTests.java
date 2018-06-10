package com.bayzat.benefits.api.boot;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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
import com.fasterxml.jackson.databind.ObjectMapper;

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
	
	private BzbTCompany company = null;
	
	@Before
	public void setUp() {
		company = new BzbTCompany();
		company.setCompanyId(Long.valueOf(1001));
		company.setName("Bayzat");
		company.setRegistrationNumber("Bzt-2013");
		BzbTAddress address = new BzbTAddress();
		address.setAddressId(Long.valueOf(1001));
		address.setBuildingName("Control Tower");
		address.setUnitNumber("10-01");
		address.setStreetAddress("Detroit Rd");
		address.setTown("Motor City");
		address.setCity("Dubai");
		address.setState("Dubai");
		address.setCountry("United Arab Emirates");
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
	 * Tests Create a Company
	 * 
	 * @throws Exception
	 */
	@Test
	public void testCreateCompany() throws Exception {
		// Invokes [/companies] Resource with ContentType as "application/hal+json;charset=UTF-8" and verifies the
		// Return Response Status is 201 - CREATED
		this.mockMvc.perform(
				post("/companies").contentType(contentType).content(new ObjectMapper().writeValueAsString(company)))
				.andExpect(status().isCreated());
	}
	
	/**
	 * Tests Create a Company Already Exist
	 * 
	 * @throws Exception
	 */
	@Test
	public void testCreateCompanyAlreadyExist() throws Exception {
		// Invokes [/companies] Resource with ContentType as "application/hal+json;charset=UTF-8" and verifies the
		// Return Response Status is 409 - CONFLICT
		this.mockMvc.perform(
				post("/companies").contentType(contentType).content(new ObjectMapper().writeValueAsString(company)))
				.andExpect(status().isConflict());
	}
	
	/**
	 * Tests Create a Company Already Exist with Same Address
	 * 
	 * @throws Exception
	 */
	@Test
	public void testCreateCompanyAlreadyExistWithSameAddress() throws Exception {
		// Invokes [/companies] Resource with ContentType as "application/hal+json;charset=UTF-8" and verifies the
		// Return Response Status is 409 - CONFLICT
		company.setRegistrationNumber("Bzt-2018");
		this.mockMvc.perform(
				post("/companies").contentType(contentType).content(new ObjectMapper().writeValueAsString(company)))
				.andExpect(status().isConflict());
	}
	
	/**
	 * Tests Updates a Company
	 * 
	 * @throws Exception
	 */
	@Test
	public void testUpdateCompany() throws Exception {
		// Invokes [/companies/{companyId}] Resource with ContentType as "application/hal+json;charset=UTF-8" and
		// verifies the Return Response Status is 201 - CREATED
		company.setRegistrationNumber("Bzt-2018");
		this.mockMvc.perform(put("/companies/" + company.getCompanyId()).contentType(contentType)
				.content(new ObjectMapper().writeValueAsString(company))).andExpect(status().isCreated());
	}
	
	/**
	 * Tests Updates a Company Not Exist
	 * 
	 * @throws Exception
	 */
	@Test
	public void testUpdateCompanyNotExist() throws Exception {
		// Invokes [/companies/{companyId}] Resource with ContentType as "application/hal+json;charset=UTF-8" and
		// verifies the Return Response Status is 404 - NOT_FOUND
		this.mockMvc.perform(put("/companies/" + Long.valueOf(5001)).contentType(contentType)
				.content(new ObjectMapper().writeValueAsString(company))).andExpect(status().isNotFound());
	}
	
	/**
	 * Tests Delete a Company Not Found
	 * 
	 * @throws Exception
	 */
	@Test
	public void testDeleteCompanyNotFound() throws Exception {
		// Invokes [/companies/{companyId}] Resource and verifies the Return Response Status is 404 - NOT_FOUND
		// and ContentType is "application/hal+json;charset=UTF-8"
		mockMvc.perform(delete("/companies/" + Long.valueOf(5001))
				.accept(APPLICATION_HAL_JSON)).andExpect(status().isNotFound())
		.andExpect(content().contentType(contentType));
	}
}