package com.bayzat.benefits.api.resource;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.bayzat.benefits.api.boot.BayzatBenefitsRestfulAPIApp;
import com.bayzat.benefits.api.model.BzbTAddress;
import com.bayzat.benefits.api.model.BzbTCompany;
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
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CompanyResourceTests {
	
	// Context Path pointing to [/companies]
	private static final String CONTEXT_PATH = "/companies";

	private static final String SLASH = "/";

	private static final String APPLICATION_HAL_JSON = "application/hal+json;charset=UTF-8";

	private MediaType contentType = MediaType.valueOf(APPLICATION_HAL_JSON);
	
	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	ICompanyService companyService;
	
	private BzbTCompany company = null;
	
	
	@Before
	public void setUp() {
		// Company
		company = new BzbTCompany();
		company.setCompanyId(Long.valueOf(1001));
		company.setName("Bayzat");
		company.setRegistrationNumber("Bzt-2013");
		// Address
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
	 * Tests Create a Company
	 * 
	 * @throws Exception
	 */
	@Test
	public void a_testCreateCompany() throws Exception {
		// Invokes [/companies] Resource with ContentType as "application/json;charset=UTF-8" and verifies the
		// Return Response Status is 201 - CREATED and ContentType as "application/hal+json;charset=UTF-8" 
		this.mockMvc
				.perform(post(CONTEXT_PATH).contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
						.content(new ObjectMapper().writeValueAsString(company)))
				.andExpect(status().isCreated()).andExpect(content().contentType(contentType));
	}
	
	/**
	 * Tests Create a Company Already Exist
	 * 
	 * @throws Exception
	 */
	@Test
	public void b_testCreateCompanyAlreadyExist() throws Exception {
		// Invokes [/companies] Resource with ContentType as "application/hal+json;charset=UTF-8" and verifies the
		// Return Response Status is 409 - CONFLICT
		this.mockMvc.perform(post(CONTEXT_PATH).contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.content(new ObjectMapper().writeValueAsString(company))).andExpect(status().isConflict());
	}
	
	/**
	 * Tests Create a Company Already Exist with Same Address
	 * 
	 * @throws Exception
	 */
	@Test
	public void c_testCreateCompanyAlreadyExistWithSameAddress() throws Exception {
		// Invokes [/companies] Resource with ContentType as "application/hal+json;charset=UTF-8" and verifies the
		// Return Response Status is 409 - CONFLICT
		// Updates Registration Number
		company.setRegistrationNumber("Bzt-2018");
		this.mockMvc.perform(post(CONTEXT_PATH).contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.content(new ObjectMapper().writeValueAsString(company))).andExpect(status().isConflict());
	}
	
	/**
	 * Tests All Companies Found
	 * 
	 * @throws Exception
	 */
	@Test
	public void d_testGetAllCompaniesFound() throws Exception {
		// Invokes [/companies] Resource and verifies the Return Response Status is 200 - OK
		// and ContentType is "application/hal+json;charset=UTF-8"
		mockMvc.perform(get(CONTEXT_PATH)).andExpect(status().isOk())
				.andExpect(content().contentType(contentType));
	}
	
	/**
	 * Tests Get All Companies Invalid Resource
	 * 
	 * @throws Exception
	 */
	@Test
	public void e_testGetAllCompaniesInvalidResource() throws Exception {
		// Invokes [/companies] Resource and verifies the Return Response Status is 404 - NOT_FOUND
		mockMvc.perform(get("/companiesTestResource"))
				.andExpect(status().isNotFound());
	}
	
	/**
	 * Tests Get a Company
	 * 
	 * @throws Exception
	 */
	@Test
	public void f_testGetCompany() throws Exception {
		// Invokes [/companies/{companyId}] Resource and verifies the Return Response Status is 200 - OK
		// and ContentType is "application/hal+json;charset=UTF-8" and Details are same
		mockMvc.perform(get(CONTEXT_PATH + SLASH + company.getCompanyId()))
				.andExpect(status().isOk()).andExpect(content().contentType(APPLICATION_HAL_JSON))
				.andExpect(jsonPath("$.name", is((company.getName()))))
				.andExpect(jsonPath("$.address.city", is((company.getAddress().getCity()))))
				.andExpect(jsonPath("$.address.postalCode", is((company.getAddress().getPostalCode()))));
	}
	
	/**
	 * Tests Get a Company Not Found
	 * 
	 * @throws Exception
	 */
	@Test
	public void g_testGetCompanyNotFound() throws Exception {
		// Invokes [/companies/{companyId}] Resource and verifies the Return Response Status is 404 - NOT_FOUND
		mockMvc.perform(get(CONTEXT_PATH + SLASH + Long.valueOf(5001)))
				.andExpect(status().isNotFound()).andExpect(content().contentType(contentType));
	}
	
	/**
	 * Tests Get a Company for Invalid Argument
	 * 
	 * @throws Exception
	 */
	@Test
	public void h_testGetCompanyInvalidArgument() throws Exception {
		// Invokes [/companies/{companyId}] Resource and verifies the Return Response Status is 400 - BAD_REQUEST
		// and ContentType is "application/hal+json;charset=UTF-8"
		mockMvc.perform(get("/companies/testArg")).andExpect(status().isBadRequest());
	}
	
	/**
	 * Tests Update a Company
	 * 
	 * @throws Exception
	 */
	@Test
	public void i_testUpdateCompany() throws Exception {
		// Invokes [/companies/{companyId}] Resource with ContentType as "application/json;charset=UTF-8" and
		// verifies the Return Response Status is 201 - CREATED, ContentType is "application/hal+json;charset=UTF-8" and
		// Details are same
		// Updates Registration Number
		company.setRegistrationNumber("Bzt-2018");
		// To get this updated through Business Logic
		company.getAddress().setAddressId(null);
		this.mockMvc
				.perform(put(CONTEXT_PATH + SLASH + company.getCompanyId()).contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
						.content(new ObjectMapper().writeValueAsString(company)))
				.andExpect(status().isCreated()).andExpect(content().contentType(contentType))
				.andExpect(jsonPath("$.name", is((company.getName()))))
				.andExpect(jsonPath("$.address.city", is((company.getAddress().getCity()))))
				.andExpect(jsonPath("$.address.postalCode", is((company.getAddress().getPostalCode()))));
	}
	
	/**
	 * Tests Update a Company Not Exist
	 * 
	 * @throws Exception
	 */
	@Test
	public void j_testUpdateCompanyNotExist() throws Exception {
		// Invokes [/companies/{companyId}] Resource with ContentType as "application/hal+json;charset=UTF-8" and
		// verifies the Return Response Status is 404 - NOT_FOUND
		this.mockMvc.perform(put(CONTEXT_PATH + SLASH + Long.valueOf(5001)).contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.content(new ObjectMapper().writeValueAsString(company))).andExpect(status().isNotFound());
	}
	
	/**
	 * Tests Delete a Company
	 * 
	 * @throws Exception
	 */
	@Test
	public void k_testDeleteCompany() throws Exception {
		// Invokes [/companies/{companyId}] Resource and verifies the Return Response Status is 204 - NO_CONTENT
		mockMvc.perform(delete(CONTEXT_PATH + SLASH + company.getCompanyId())).andExpect(status().isNoContent());
	}
	
	/**
	 * Tests Delete a Company Not Found
	 * 
	 * @throws Exception
	 */
	@Test
	public void l_testDeleteCompanyNotFound() throws Exception {
		// Invokes [/companies/{companyId}] Resource and verifies the Return Response Status is 404 - NOT_FOUND
		mockMvc.perform(delete(CONTEXT_PATH + SLASH + Long.valueOf(5001))).andExpect(status().isNotFound());
	}
}