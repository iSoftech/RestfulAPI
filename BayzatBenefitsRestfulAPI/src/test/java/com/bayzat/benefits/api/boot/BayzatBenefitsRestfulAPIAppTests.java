package com.bayzat.benefits.api.boot;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.bayzat.benefits.api.service.ICompanyService;
import com.bayzat.benefits.api.service.IDependantService;
import com.bayzat.benefits.api.service.IEmployeeService;

/**
 * Bayzat Benefits Restful API Basic JUnit Mocked Tests
 * 
 * @author Mohamed Yusuff
 */
@RunWith(SpringRunner.class)
@WebMvcTest
public class BayzatBenefitsRestfulAPIAppTests {

	private static final String APPLICATION_HAL_JSON = "application/hal+json;charset=UTF-8";

	private MediaType contentType = MediaType.valueOf(APPLICATION_HAL_JSON);
	
	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	ICompanyService companyService;
	
	@MockBean
	IEmployeeService employeeService;
	
	@MockBean
	IDependantService dependantService;
	
	@Before
	public void setUp() {
		// For getAllCompanies()
		Mockito.when(companyService.getAllCompanies()).thenReturn(Collections.emptyList());
		// For getAllEmployees(companyId)
		Mockito.when(employeeService.getAllEmployees(Mockito.anyLong())).thenReturn(Collections.emptyList());
		// For getAllDependants(companyId, employeeId)
		Mockito.when(dependantService.getAllDependants(Mockito.anyLong(), Mockito.anyLong()))
				.thenReturn(Collections.emptyList());
	}
	
	@Test
	public void testGetAllCompanies() throws Exception {
		// Invokes [/companies] Resource and verifies the Return Response Status is 200 - OK
		// and ContentType is "application/hal+json;charset=UTF-8"
		mockMvc.perform(MockMvcRequestBuilders.get("/companies")
				.accept(APPLICATION_HAL_JSON)).andExpect(status().isOk())
		.andExpect(content().contentType(contentType));
		// Verifies If companyService.getAllCompanies() is invoked at least once
		Mockito.verify(companyService).getAllCompanies();
	}
	
	@Test
	public void testGetAllEmployees() throws Exception {
		// Invokes [/companies/{companyId}/employees] Resource and verifies the Return Response Status is 200 - OK
		// and ContentType is "application/hal+json;charset=UTF-8"
		mockMvc.perform(MockMvcRequestBuilders.get("/companies/" + Mockito.anyLong() + "/employees")
				.accept(APPLICATION_HAL_JSON)).andExpect(status().isOk())
		.andExpect(content().contentType(contentType));
		// Verifies If employeeService.getAllEmployees(companyId) is invoked at least once
		Mockito.verify(employeeService).getAllEmployees(Mockito.anyLong());
	}
	
	@Test
	public void testGetAllDependants() throws Exception {
		// Invokes [/companies/{companyId}/employees/{employeeId}] Resource and verifies the Return Response Status is
		// 200 - OK and ContentType is "application/hal+json;charset=UTF-8"
		mockMvc.perform(MockMvcRequestBuilders
				.get("/companies/" + Mockito.anyLong() + "/employees/" + Mockito.anyLong() + "/dependants")
				.accept(APPLICATION_HAL_JSON)).andExpect(status().isOk())
		.andExpect(content().contentType(contentType));
		// Verifies If dependantService.getAllDependants(companyId, employeeId) is invoked at least once
		Mockito.verify(dependantService).getAllDependants(Mockito.anyLong(), Mockito.anyLong());
	}
}