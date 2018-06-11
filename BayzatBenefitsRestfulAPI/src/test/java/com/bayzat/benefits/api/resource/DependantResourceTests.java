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
import com.bayzat.benefits.api.constant.Gender;
import com.bayzat.benefits.api.constant.Relationship;
import com.bayzat.benefits.api.model.BzbTAddress;
import com.bayzat.benefits.api.model.BzbTDependant;
import com.bayzat.benefits.api.model.BzbTEmployee;
import com.bayzat.benefits.api.service.IDependantService;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Bayzat Benefits Restful API Integration Tests for Dependant Resource Controller
 * 
 * @author Mohamed Yusuff
 */
@RunWith(SpringRunner.class)
@SpringBootTest (
		webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
		classes = {BayzatBenefitsRestfulAPIApp.class, DependantResource.class}
)
@AutoConfigureMockMvc
@TestPropertySource(locations="classpath:application-test.properties")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DependantResourceTests {
	
	// Context Path pointing to [/companies/{companyId}/employees/{employeeId}/dependants]
	private static String CONTEXT_PATH = "/companies/" + Long.valueOf(1001) + "/employees/" + Long.valueOf(1001)
			+ "/dependants";

	private static final String SLASH = "/";

	private static final String APPLICATION_HAL_JSON = "application/hal+json;charset=UTF-8";

	private MediaType contentType = MediaType.valueOf(APPLICATION_HAL_JSON);
	
	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	IDependantService dependantService;
	
	private BzbTDependant dependant = null;
	
	@Before
	public void setUp() {
		// Dependant
		dependant = new BzbTDependant();
		dependant.setDependantId(Long.valueOf(1006));
		dependant.setFirstName("Mohamed");
		dependant.setGender(Gender.Male.getValue());
		dependant.setRelationship(Relationship.Child.getValue());
		// Address
		BzbTAddress address = new BzbTAddress();
		address.setAddressId(Long.valueOf(1002));
		address.setBuildingName("Tower 1");
		address.setUnitNumber("08-12");
		address.setStreetAddress("Baniyas Road");
		address.setTown("Deira");
		address.setCity("Dubai");
		address.setState("Dubai");
		address.setCountry("United Arab Emirates");
		address.setPostalCode("00594");
		dependant.setAddress(address);
		// Dependant
		BzbTEmployee employee = new BzbTEmployee();
		employee.setEmployeeId(Long.valueOf(1001));
		dependant.setEmployee(employee);
	}
	
	
	/**
	 * Tests Create a Dependant
	 * 
	 * @throws Exception
	 */
	@Test
	public void a_testCreateDependant() throws Exception {
		// Invokes [/companies/{companyId}/employees/{employeeId}/dependants] Resource with ContentType as
		// "application/json;charset=UTF-8" and verifies the ContentType as "application/hal+json;charset=UTF-8" and
		// Return Response Status is 201 - CREATED
		this.mockMvc
				.perform(post(CONTEXT_PATH).contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
						.content(new ObjectMapper().writeValueAsString(dependant)))
				.andExpect(content().contentType(contentType)).andExpect(status().isCreated());
	}
	
	/**
	 * Tests Create a Dependant Already Exist
	 * 
	 * @throws Exception
	 */
	@Test
	public void b_testCreateDependantAlreadyExist() throws Exception {
		// Invokes [/companies/{companyId}/employees/{employeeId}/dependants] Resource with ContentType as
		// "application/hal+json;charset=UTF-8" and verifies the Return Response Status is 409 - CONFLICT
		// Updates FirstName, Gender and Relationship
		dependant.setFirstName("Yasmin");
		dependant.setGender(Gender.Female.getValue());
		dependant.setRelationship(Relationship.Spouse.getValue());
		this.mockMvc.perform(post(CONTEXT_PATH).contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.content(new ObjectMapper().writeValueAsString(dependant))).andExpect(status().isConflict());
	}
	
	/**
	 * Tests All Companies Found
	 * 
	 * @throws Exception
	 */
	@Test
	public void c_testGetAllDependantsFound() throws Exception {
		// Invokes [/companies/{companyId}/employees/{employeeId}/dependants] Resource and verifies the ContentType is
		// "application/hal+json;charset=UTF-8" and Return Response Status is 200 - OK
		mockMvc.perform(get(CONTEXT_PATH)).andExpect(content().contentType(contentType)).andExpect(status().isOk());
	}
	
	/**
	 * Tests Get All Companies Invalid Resource
	 * 
	 * @throws Exception
	 */
	@Test
	public void d_testGetAllDependantsInvalidCompany() throws Exception {
		// Invokes [/companies/{companyId}/employees/{employeeId}/dependants] Resource and verifies the Return Response
		// Status is 404 - NOT_FOUND
		mockMvc.perform(get("/companies/" + Long.valueOf(5001) + "/employees/" + dependant.getEmployee().getEmployeeId()
				+ "/dependants")).andExpect(status().isNotFound());
	}
	
	/**
	 * Tests Get All Companies Invalid Resource
	 * 
	 * @throws Exception
	 */
	@Test
	public void e_testGetAllDependantsInvalidEmployee() throws Exception {
		// Invokes [/companies/{companyId}/employees/{employeeId}/dependants] Resource and verifies the Return Response
		// Status is 404 - NOT_FOUND
		mockMvc.perform(get("/companies/" + Long.valueOf(1001) + "/employees/" + Long.valueOf(5001) + "/dependants"))
				.andExpect(status().isNotFound());
	}
	
	/**
	 * Tests Get All Companies Invalid Resource
	 * 
	 * @throws Exception
	 */
	@Test
	public void f_testGetAllDependantsInvalidResource() throws Exception {
		// Invokes [/companies/{companyId}/employees/{employeeId}/dependants] Resource and verifies the Return Response
		// Status is 404 - NOT_FOUND
		mockMvc.perform(get("/companies/" + Long.valueOf(1001) + "/employees/" + dependant.getEmployee().getEmployeeId()
				+ "/TestResource")).andExpect(status().isNotFound());
	}
	
	/**
	 * Tests Get a Dependant
	 * 
	 * @throws Exception
	 */
	@Test
	public void g_testGetDependant() throws Exception {
		// Invokes [/companies/{companyId}/employees/{employeeId}/{employeeId}/dependants/dependantId}] Resource and
		// verifies the ContentType is "application/hal+json;charset=UTF-8" and Details are same and Return Response
		// Status is 200 - OK
		mockMvc.perform(get(CONTEXT_PATH + SLASH + dependant.getDependantId()))
				.andExpect(content().contentType(APPLICATION_HAL_JSON))
				.andExpect(jsonPath("$.firstName", is((dependant.getFirstName()))))
				.andExpect(jsonPath("$.address.city", is((dependant.getAddress().getCity()))))
				.andExpect(jsonPath("$.address.postalCode", is((dependant.getAddress().getPostalCode()))))
				.andExpect(status().isOk());
	}
	
	/**
	 * Tests Get a Dependant Not Found
	 * 
	 * @throws Exception
	 */
	@Test
	public void h_testGetDependantNotFound() throws Exception {
		// Invokes [/companies/{companyId}/employees/{employeeId}/dependants/dependantId}] Resource and verifies the
		// Return Response Status is 404 - NOT_FOUND
		mockMvc.perform(get(CONTEXT_PATH + SLASH + Long.valueOf(5001))).andExpect(status().isNotFound());
	}
	
	/**
	 * Tests Get a Dependant for Invalid Argument
	 * 
	 * @throws Exception
	 */
	@Test
	public void i_testGetDependantInvalidArgument() throws Exception {
		// Invokes [/companies/{companyId}/employees/{employeeId}/dependants/dependantId}] Resource and verifies the
		// Return Response Status is 400 - BAD_REQUEST
		// and ContentType is "application/hal+json;charset=UTF-8"
		mockMvc.perform(get(CONTEXT_PATH + "/testArg")).andExpect(status().isBadRequest());
	}
	
	/**
	 * Tests Update a Dependant
	 * 
	 * @throws Exception
	 */
	@Test
	public void j_testUpdateDependant() throws Exception {
		// Invokes [/companies/{companyId}/employees/{employeeId}/dependants/dependantId}] Resource with ContentType as
		// "application/json;charset=UTF-8" and verifies the Return Response Status is 201 - CREATED, ContentType is
		// "application/hal+json;charset=UTF-8" and Details are same
		// Updates Age
		dependant.setAge(2);
		this.mockMvc
				.perform(put(CONTEXT_PATH + SLASH + dependant.getDependantId())
						.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
						.content(new ObjectMapper().writeValueAsString(dependant)))
				.andExpect(content().contentType(contentType))
				.andExpect(jsonPath("$.firstName", is((dependant.getFirstName()))))
				.andExpect(jsonPath("$.address.city", is((dependant.getAddress().getCity()))))
				.andExpect(jsonPath("$.address.postalCode", is((dependant.getAddress().getPostalCode()))))
				.andExpect(status().isCreated());
	}
	
	/**
	 * Tests Update a Dependant Not Exist
	 * 
	 * @throws Exception
	 */
	@Test
	public void k_testUpdateDependantNotExist() throws Exception {
		// Invokes [/companies/{companyId}/employees/{employeeId}/dependants/dependantId}] Resource with ContentType as
		// "application/hal+json;charset=UTF-8" and verifies the Return Response Status is 404 - NOT_FOUND
		this.mockMvc.perform(put(CONTEXT_PATH + SLASH + Long.valueOf(5001)).contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.content(new ObjectMapper().writeValueAsString(dependant))).andExpect(status().isNotFound());
	}
	
	/**
	 * Tests Delete a Dependant
	 * 
	 * @throws Exception
	 */
	@Test
	public void l_testDeleteDependant() throws Exception {
		// Invokes [/companies/{companyId}/employees/{employeeId}/dependants/dependantId}] Resource and verifies the
		// Return Response Status is 204 - NO_CONTENT
		mockMvc.perform(delete(CONTEXT_PATH + SLASH + dependant.getDependantId())).andExpect(status().isNoContent());
	}
	
	/**
	 * Tests Delete a Dependant Not Found
	 * 
	 * @throws Exception
	 */
	@Test
	public void m_testDeleteDependantNotFound() throws Exception {
		// Invokes [/companies/{companyId}/employees/{employeeId}/dependants/dependantId}] Resource and verifies the
		// Return Response Status is 404 - NOT_FOUND
		mockMvc.perform(delete(CONTEXT_PATH + SLASH + Long.valueOf(5001))).andExpect(status().isNotFound());
	}
}