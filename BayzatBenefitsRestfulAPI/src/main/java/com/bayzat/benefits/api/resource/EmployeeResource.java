package com.bayzat.benefits.api.resource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Links;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.bayzat.benefits.api.model.BzbTEmployee;
import com.bayzat.benefits.api.service.IDependantService;
import com.bayzat.benefits.api.service.IEmployeeService;

/**
 * Bayzat Benefits Restful API Controller for <tt>/companies/{companyId}/employees</tt> Resource with CRUD operations.
 * 
 * @author Mohamed Yusuff
 */
@RequestMapping(value="/companies/{companyId}/employees", produces="application/hal+json")
@RestController
public class EmployeeResource {

	@Autowired
	private IEmployeeService employeeService;
	
	@Autowired
	private IDependantService dependantService;
	
	/**
	 * <strong>Lists all Employees Entities [<tt>GET</tt>]</strong>
	 * <br>
	 * Returns all <tt>Employees</tt> available in the system with <tt>GET</tt> method
	 * for resource {@code /companies/{companyId}/employees}.
	 * 
	 * <pre>
	 * {@code Response 200 (application/hal+json)}
	 * </pre>
	 * @param companyId refers to attribute {@code companyId}
	 * @return a list of {@link BzbTEmployee} with Response Status as 200 OK
	 */
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<Resources<BzbTEmployee>> getAllEmployees(@PathVariable Long companyId) {
		// Retrieves All Employees details for the Company
		List<BzbTEmployee> allEmployees = employeeService.getAllEmployees(companyId);
		// Iterates through all employees details to set Self Link to individual Employee 
		// and its Dependants Resource Link
	    for (BzbTEmployee employee : allEmployees) {
	        // Self Link to Employee resource [/companies/{companyId}/employees/{employeeId}]
			Link selfLink = linkTo(methodOn(this.getClass()).getEmployee(companyId, employee.getEmployeeId()))
					.withSelfRel();
			// Attaches Self Link to all Employee Resources
	        employee.add(selfLink);
			// Attaches Child Link to all Employee Resources if available
			// [/companies/{companyId}/employees/{employeeId}/dependants]
			if (!CollectionUtils.isEmpty(dependantService.getAllDependants(companyId, employee.getEmployeeId()))) {
				Link dependantsLink = linkTo(
						methodOn(DependantResource.class).getAllDependants(companyId, employee.getEmployeeId()))
								.withRel("dependants");
				employee.add(dependantsLink);
	        }
	    }
	    // Populates All Parent Links for Companies, Company, and Employees
	    Links parentLinks = populateLinks(companyId, null);
		// Initialises HATEOAS Resource for newly added Employee Resource with list of Links
		Resources<BzbTEmployee> employeeResource = new Resources<BzbTEmployee>(allEmployees, parentLinks);
		// Returns the ResponseEntity with HTTPStatus as OK {200}
		return ResponseEntity.ok(employeeResource);
	}

	/**
	 * <strong>Creates a Employee Entity [<tt>POST</tt>]</strong>
	 * <br>
	 * Adds a new <tt>Employee</tt> details with <tt>POST</tt> method.
	 * 
	 * <pre>
	 * {@code Request (application/json)}
	 * 	
	 * {@code Response 201 (application/hal+json)}
	 * </pre>
	 * 
	 * @param companyId refers to attribute {@code companyId}
	 * @param employee refers to a new instance of {@link BzbTEmployee}
	 * @return a newly added {@link BzbTEmployee} with Response Status as 201 Created
	 */
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Resource<BzbTEmployee>> addEmployee(@PathVariable Long companyId,
			@RequestBody BzbTEmployee employee) {
		// Adds and Returns newly added Employee details
		BzbTEmployee savedEmployee = employeeService.addEmployee(companyId, employee);
		// Populates All Links for Companies, Company, Employees, and Employee 
	    Links allLinks = populateLinks(companyId, savedEmployee.getEmployeeId());
		// Initialises HATEOAS Resource for newly added Employee Resource with list of Links
		Resource<BzbTEmployee> resource = new Resource<BzbTEmployee>(savedEmployee, allLinks);
		// URI Builder to build newly created resource location
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{employeeId}")
				.buildAndExpand(savedEmployee.getEmployeeId()).toUri();
		// Returns the ResponseEntity with HTTPStatus as Created {201}
		return ResponseEntity.created(location).body(resource);
	}
	
	/**
	 * <strong>Views a Employee Entity [<tt>GET</tt>]</strong>
	 * <br>
	 * Returns a <tt>Employee</tt> available in the system identified by its <tt>{employeeId}</tt> with
	 * <tt>GET</tt> method.
	 * 
	 * <pre>
	 * {@code Request}
	 * 	Headers
	 * 		Location: /employees/1001
	 * 
	 * {@code Response 200 (application/hal+json)}
	 * </pre>
	 * 
	 * @param companyId refers to attribute {@code companyId}
	 * @param employeeId refers to attribute {@code employeeId}
	 * @return a single {@link BzbTEmployee} identified by its employeeId with Response Status as 200 OK
	 */
	@RequestMapping(value="/{employeeId}", method=RequestMethod.GET)
	public ResponseEntity<Resource<BzbTEmployee>> getEmployee(@PathVariable Long companyId,
			@PathVariable Long employeeId) {
		// Retrieves requested Employee details
		BzbTEmployee employee = employeeService.getEmployee(companyId, employeeId);
		// Populates All Links for Companies, Company, Employees, Employee, and Dependants
	    Links allLinks = populateLinks(companyId, employeeId);
		// Initialises HATEOAS Resource for requested Employee Resource with list of Links
		Resource<BzbTEmployee> resource = new Resource<BzbTEmployee>(employee, allLinks);
		// Returns the ResponseEntity with HTTPStatus as OK {200}
		return ResponseEntity.ok(resource);
	}

	/**
	 * <strong>Updates a Employee Entity [<tt>PUT</tt>]</strong>
	 * <br>
	 * Updates an existing <tt>Employee</tt> details identified by its
	 * <tt>{employeeId}</tt> with <tt>PUT</tt> method.
	 * 
	 * <pre>
	 * {@code Request (application/json)}
	 * 	Headers
	 * 		Location: /employees/1001
	 * {@code Response 201 (application/hal+json)}
	 * </pre>
	 * 
	 * @param companyId refers to attribute {@code companyId}
	 * @param employeeId refers to attribute {@code employeeId}
	 * @param employee refers to an edited instance of {@link BzbTEmployee}
	 * @return an updated {@link BzbTEmployee} identified by its employeeId with Response Status as 201 Created
	 */
	@RequestMapping(value="/{employeeId}", method=RequestMethod.PUT)
	public ResponseEntity<Resource<BzbTEmployee>> updateEmployee(@PathVariable Long companyId,
			@PathVariable Long employeeId, @RequestBody BzbTEmployee employee) {
		// Updates and Returns edited Employee details
		BzbTEmployee updatedEmployee = employeeService.updateEmployee(companyId, employeeId, employee);
		// Populates All Links for Companies, Company, Employees, Employee, and Dependants
	    Links allLinks = populateLinks(companyId, employeeId);
		// Initialises HATEOAS Resource for updated Employee Resource with list of Links
		Resource<BzbTEmployee> resource = new Resource<BzbTEmployee>(updatedEmployee, allLinks);
		// URI Builder to build updated resource location
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.buildAndExpand(updatedEmployee.getEmployeeId()).toUri();
		// Returns the ResponseEntity with HTTPStatus as Created {201}
		return ResponseEntity.created(location).body(resource);
	}
	
	/**
	 * <strong>Deletes a Employee Entity [<tt>DELETE</tt>]</strong>
	 * <br>
	 * Deletes an existing <tt>Employee</tt> details identified by its
	 * <tt>{employeeId}</tt> with <tt>DELETE</tt> method.
	 * 
	 * <pre>
	 * {@code Request}
	 * 	Headers
	 * 		Location: /employees/1001
	 * {@code Response 204}
	 * </pre>
	 * 
	 * @param companyId refers to attribute {@code companyId}
	 * @param employeeId refers to attribute {@code employeeId}
	 */
	@RequestMapping(value="/{employeeId}", method=RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteEmployee(@PathVariable Long companyId, @PathVariable Long employeeId) {
		// Deletes the requested Employee Resource with Response HTTPStatus as No Content {204}
		employeeService.deleteEmployee(companyId, employeeId);
	}

	/**
	 * Populates and Returns All Links for Companies, Company, Employees, Employee, and Dependants 
	 * 
	 * @param companyId refers to attribute {@code companyId}
	 * @param employeeId refers to attribute {@code employeeId}
	 * @return {@link Links} of Parent Links for this class
	 */
	private Links populateLinks(Long companyId, Long employeeId) {
		// Parent Link to all Employees Resource [/companies/{companyId}/employees]
		Link employeesLink = linkTo(methodOn(this.getClass()).getAllEmployees(companyId)).withSelfRel();
		// Parent Link to Company Resource [/companies/{companyId}]
		Link companyLink = linkTo(methodOn(CompanyResource.class).getCompany(companyId)).withRel("company");
		// Parent Link to All Companies Resource [/companies]
		Link companiesLink = linkTo(methodOn(CompanyResource.class).getAllCompanies()).withRel("companies");
		// Creates All Links with companiesLink, companyLink, and employeesLink
		Links allLinks = new Links(companiesLink, companyLink, employeesLink);
		// Updates Links for Single Resource Result
		if (null != employeeId) {
			// Updates Employees Link label to "employees" when Employee Link is referred as "self"
			employeesLink = employeesLink.withRel("employees");
			// Self Link to newly added or update or retrieved Employee Resource
			// [/companies/{companyId}/employees/{employeeId}]
			Link selfLink = linkTo(methodOn(this.getClass()).getEmployee(companyId, employeeId)).withSelfRel();
			// Creates All Links with companiesLink, companyLink, employeesLink, and employeeLink 
			allLinks = new Links(companiesLink, companyLink, employeesLink, selfLink);
			// Attaches Child Link to Employee Resource if available
			// [/companies/{companyId}/employees/{employeeId}/dependants]
			if (!CollectionUtils.isEmpty(dependantService.getAllDependants(companyId, employeeId))) {
				Link dependantsLink = linkTo(methodOn(DependantResource.class).getAllDependants(companyId, employeeId))
						.withRel("dependants");
				// Creates All Links with companiesLink, companyLink, employeesLink, employeeLink, and dependantsLink
				allLinks = new Links(companiesLink, companyLink, employeesLink, selfLink, dependantsLink);
			}
		}
		return allLinks;
	}
}