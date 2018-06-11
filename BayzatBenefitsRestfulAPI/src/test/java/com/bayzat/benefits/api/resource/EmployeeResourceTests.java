package com.bayzat.benefits.api.resource;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;

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
import com.bayzat.benefits.api.model.BzbTAddress;
import com.bayzat.benefits.api.model.BzbTCompany;
import com.bayzat.benefits.api.model.BzbTDependant;
import com.bayzat.benefits.api.model.BzbTEmployee;
import com.bayzat.benefits.api.service.IEmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Bayzat Benefits Restful API Integration Tests for Employee Resource Controller
 * 
 * @author Mohamed Yusuff
 */
@RunWith(SpringRunner.class)
@SpringBootTest (
		webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
		classes = {BayzatBenefitsRestfulAPIApp.class, EmployeeResource.class}
)
@AutoConfigureMockMvc
@TestPropertySource(locations="classpath:application-test.properties")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EmployeeResourceTests {
	
	// Context Path pointing to [/companies/{companyId}/employees]
	private static String CONTEXT_PATH = "/companies/" + Long.valueOf(1001) + "/employees";

	private static final String SLASH = "/";

	private static final String APPLICATION_HAL_JSON = "application/hal+json;charset=UTF-8";

	private MediaType contentType = MediaType.valueOf(APPLICATION_HAL_JSON);
	
	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	IEmployeeService employeeService;
	
	private BzbTEmployee employee = null;
	
	@Before
	public void setUp() {
		// Employee
		employee = new BzbTEmployee();
		employee.setEmployeeId(Long.valueOf(1005));
		employee.setFirstName("Mohamed");
		employee.setEmployeeCode("BZT-10050");
		employee.setGender(Gender.Male.getValue());
		// Address
		BzbTAddress address = new BzbTAddress();
		address.setAddressId(Long.valueOf(1007));
		address.setBuildingName("Tower 1");
		address.setUnitNumber("08-12");
		address.setStreetAddress("Baniyas Road");
		address.setTown("Deira");
		address.setCity("Dubai");
		address.setState("Dubai");
		address.setCountry("United Arab Emirates");
		address.setPostalCode("005940");
		employee.setAddress(address);
		// Company
		BzbTCompany company = new BzbTCompany();
		company.setCompanyId(Long.valueOf(1001));
		employee.setCompany(company);
		// Dependants
		BzbTDependant dependant = new BzbTDependant();
		dependant.setFirstName("Yalina");
		dependant.setEmployee(employee);
		employee.setDependants(Collections.singletonList(dependant));
	}
	
	
	/**
	 * Tests Create a Employee
	 * 
	 * @throws Exception
	 */
	@Test
	public void a_testCreateEmployee() throws Exception {
		// Invokes [/companies/{companyId}/employees] Resource with ContentType as "application/json;charset=UTF-8" and
		// verifies the Return Response Status is 201 - CREATED and ContentType as "application/hal+json;charset=UTF-8"
		this.mockMvc
				.perform(post(CONTEXT_PATH).contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
						.content(new ObjectMapper().writeValueAsString(employee)))
				.andExpect(status().isCreated()).andExpect(content().contentType(contentType));
	}
	
	/**
	 * Tests Create a Employee Already Exist
	 * 
	 * @throws Exception
	 */
	@Test
	public void b_testCreateEmployeeAlreadyExist() throws Exception {
		// Invokes [/companies/{companyId}/employees] Resource with ContentType as "application/hal+json;charset=UTF-8" and verifies the
		// Return Response Status is 409 - CONFLICT
		this.mockMvc.perform(post(CONTEXT_PATH).contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.content(new ObjectMapper().writeValueAsString(employee))).andExpect(status().isConflict());
	}
	
	/**
	 * Tests Create a Employee Already Exist with Same Address
	 * 
	 * @throws Exception
	 */
	@Test
	public void c_testCreateEmployeeAlreadyExistWithSameAddress() throws Exception {
		// Invokes [/companies/{companyId}/employees] Resource with ContentType as "application/hal+json;charset=UTF-8" and verifies the
		// Return Response Status is 409 - CONFLICT
		// Updates Employee Code
		employee.setEmployeeCode("BZT-10100");
		this.mockMvc.perform(post(CONTEXT_PATH).contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.content(new ObjectMapper().writeValueAsString(employee))).andExpect(status().isConflict());
	}
	
	/**
	 * Tests All Companies Found
	 * 
	 * @throws Exception
	 */
	@Test
	public void d_testGetAllEmployeesFound() throws Exception {
		// Invokes [/companies/{companyId}/employees] Resource and verifies the Return Response Status is 200 - OK
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
	public void e_testGetAllEmployeesInvalidCompany() throws Exception {
		// Invokes [/companies/{companyId}/employees] Resource and verifies the Return Response Status is 404 - NOT_FOUND
		mockMvc.perform(get("/companies/" + Long.valueOf(5001) + "/employees")).andExpect(status().isNotFound());
	}
	
	/**
	 * Tests Get All Companies Invalid Resource
	 * 
	 * @throws Exception
	 */
	@Test
	public void f_testGetAllEmployeesInvalidResource() throws Exception {
		// Invokes [/companies/{companyId}/employees] Resource and verifies the Return Response Status is 404 - NOT_FOUND
		mockMvc.perform(get("/companies/" + employee.getCompany().getCompanyId() + "/TestResource"))
				.andExpect(status().isNotFound());
	}
	
	/**
	 * Tests Get a Employee
	 * 
	 * @throws Exception
	 */
	@Test
	public void g_testGetEmployee() throws Exception {
		// Invokes [/companies/{companyId}/employees/{employeeId}] Resource and verifies the Return Response Status is 200 - OK
		// and ContentType is "application/hal+json;charset=UTF-8" and Details are same
		mockMvc.perform(get(CONTEXT_PATH + SLASH + employee.getEmployeeId()))
				.andExpect(status().isOk()).andExpect(content().contentType(APPLICATION_HAL_JSON))
				.andExpect(jsonPath("$.firstName", is((employee.getFirstName()))))
				.andExpect(jsonPath("$.employeeCode", is((employee.getEmployeeCode()))))
				.andExpect(jsonPath("$.address.city", is((employee.getAddress().getCity()))))
				.andExpect(jsonPath("$.address.postalCode", is((employee.getAddress().getPostalCode()))));
	}
	
	/**
	 * Tests Get a Employee Not Found
	 * 
	 * @throws Exception
	 */
	@Test
	public void h_testGetEmployeeNotFound() throws Exception {
		// Invokes [/companies/{companyId}/employees/{employeeId}] Resource and verifies the Return Response Status is 404 - NOT_FOUND
		mockMvc.perform(get(CONTEXT_PATH + SLASH + Long.valueOf(5001))).andExpect(status().isNotFound());
	}
	
	/**
	 * Tests Get a Employee for Invalid Argument
	 * 
	 * @throws Exception
	 */
	@Test
	public void i_testGetEmployeeInvalidArgument() throws Exception {
		// Invokes [/companies/{companyId}/employees/{employeeId}] Resource and verifies the Return Response Status is 400 - BAD_REQUEST
		// and ContentType is "application/hal+json;charset=UTF-8"
		mockMvc.perform(get(CONTEXT_PATH + "/testArg")).andExpect(status().isBadRequest());
	}
	
	/**
	 * Tests Update a Employee
	 * 
	 * @throws Exception
	 */
	@Test
	public void j_testUpdateEmployee() throws Exception {
		// Invokes [/companies/{companyId}/employees/{employeeId}] Resource with ContentType as "application/json;charset=UTF-8" and
		// verifies the Return Response Status is 201 - CREATED, ContentType is "application/hal+json;charset=UTF-8" and
		// Details are same
		// Updates Employee Code
		employee.setEmployeeCode("BZT-10100");
		// To get this updated through Business Logic
		employee.getAddress().setAddressId(null);
		this.mockMvc
				.perform(put(CONTEXT_PATH + SLASH + employee.getEmployeeId()).contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
						.content(new ObjectMapper().writeValueAsString(employee)))
				.andExpect(status().isCreated()).andExpect(content().contentType(contentType))
				.andExpect(jsonPath("$.firstName", is((employee.getFirstName()))))
				.andExpect(jsonPath("$.employeeCode", is((employee.getEmployeeCode()))))
				.andExpect(jsonPath("$.address.city", is((employee.getAddress().getCity()))))
				.andExpect(jsonPath("$.address.postalCode", is((employee.getAddress().getPostalCode()))));
	}
	
	/**
	 * Tests Update a Employee Not Exist
	 * 
	 * @throws Exception
	 */
	@Test
	public void k_testUpdateEmployeeNotExist() throws Exception {
		// Invokes [/companies/{companyId}/employees/{employeeId}] Resource with ContentType as "application/hal+json;charset=UTF-8" and
		// verifies the Return Response Status is 404 - NOT_FOUND
		this.mockMvc.perform(put(CONTEXT_PATH + SLASH + Long.valueOf(5001)).contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.content(new ObjectMapper().writeValueAsString(employee))).andExpect(status().isNotFound());
	}
	
	/**
	 * Tests Delete a Employee
	 * 
	 * @throws Exception
	 */
	@Test
	public void l_testDeleteEmployee() throws Exception {
		// Invokes [/companies/{companyId}/employees/{employeeId}] Resource and verifies the Return Response Status is 204 - NO_CONTENT
		mockMvc.perform(delete(CONTEXT_PATH + SLASH + employee.getEmployeeId())).andExpect(status().isNoContent());
	}
	
	/**
	 * Tests Delete a Employee Not Found
	 * 
	 * @throws Exception
	 */
	@Test
	public void m_testDeleteEmployeeNotFound() throws Exception {
		// Invokes [/companies/{companyId}/employees/{employeeId}] Resource and verifies the Return Response Status is 404 - NOT_FOUND
		mockMvc.perform(delete(CONTEXT_PATH + SLASH + Long.valueOf(5001))).andExpect(status().isNotFound());
	}
}