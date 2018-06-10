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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.bayzat.benefits.api.model.BzbTDependant;
import com.bayzat.benefits.api.service.IDependantService;

/**
 * Bayzat Benefits Restful API Controller for <tt>/companies/{companyId}/employees/{employeeId}/dependants</tt> Resource with CRUD operations.
 * 
 * @author Mohamed Yusuff
 */
@RequestMapping(value="/companies/{companyId}/employees/{employeeId}/dependants", produces="application/hal+json")
@RestController
public class DependantResource {

	@Autowired
	private IDependantService dependantService;
	
	/**
	 * <strong>Lists all Dependants Entities [<tt>GET</tt>]</strong>
	 * <br>
	 * Returns all <tt>Dependants</tt> available in the system with <tt>GET</tt> method
	 * for resource {@code /companies/{companyId}/employees/{employeeId}/dependants}.
	 * 
	 * <pre>
	 * {@code Response 200 (application/hal+json)}
	 * </pre>
	 * @param companyId refers to attribute {@code companyId}
	 * @param employeeId refers to attribute {@code employeeId}
	 * @return a list of {@link BzbTDependant} with Response Status as 200 OK
	 */
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<Resources<BzbTDependant>> getAllDependants(@PathVariable Long companyId,
			@PathVariable Long employeeId) {
		// Retrieves All Dependants details for the Company
		List<BzbTDependant> allDependants = dependantService.getAllDependants(companyId, employeeId);
		// Iterates through all dependants details to set Self Link
	    for (BzbTDependant dependant : allDependants) {
	        // Self Link to Dependant resource [/companies/{companyId}/employees/{employeeId}/dependants/{dependantId}]
			Link selfLink = linkTo(methodOn(this.getClass()).getDependant(companyId, employeeId, dependant.getDependantId()))
					.withSelfRel();
			// Attaches Self Link to all Dependant Resources
	        dependant.add(selfLink);
	    }
	    // Populates All Parent Links for Companies, Company, Employees, Employee, and Dependants 
	    Links parentLinks = populateLinks(companyId, employeeId, null);
		// Initialises HATEOAS Resource for newly added Dependant Resource with list of Links
		Resources<BzbTDependant> employeeResource = new Resources<BzbTDependant>(allDependants, parentLinks);
		// Returns the ResponseEntity with HTTPStatus as OK {200}
		return ResponseEntity.ok(employeeResource);
	}

	/**
	 * <strong>Creates a Dependant Entity [<tt>POST</tt>]</strong>
	 * <br>
	 * Adds a new <tt>Dependant</tt> details with <tt>POST</tt> method.
	 * 
	 * <pre>
	 * {@code Request (application/json)}
	 * </pre>
	 * 
	 * @param companyId refers to attribute {@code companyId}
	 * @param employeeId refers to attribute {@code employeeId}
	 * @param dependant refers to a new instance of {@link BzbTDependant}
	 * @return a newly added {@link BzbTDependant} with Response Status as 201 Created
	 */
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Resource<BzbTDependant>> addDependant(@PathVariable Long companyId,
			@PathVariable Long employeeId, @RequestBody BzbTDependant dependant) {
		// Adds and Returns newly added Dependant details
		BzbTDependant savedDependant = dependantService.addDependant(companyId, employeeId, dependant);
		// Populates All Links for Companies, Company, Employees, Employee, Dependants, and Dependant 
	    Links allLinks = populateLinks(companyId, employeeId, savedDependant.getDependantId());
		// Initialises HATEOAS Resource for newly added Dependant Resource with list of Links
		Resource<BzbTDependant> resource = new Resource<BzbTDependant>(savedDependant, allLinks);
		// URI Builder to build newly created resource location
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{dependantId}")
				.buildAndExpand(savedDependant.getDependantId()).toUri();
		// Returns the ResponseEntity with HTTPStatus as Created {201}
		return ResponseEntity.created(location).body(resource);
	}
	
	/**
	 * <strong>Views a Dependant Entity [<tt>GET</tt>]</strong>
	 * <br>
	 * Returns a <tt>Dependant</tt> available in the system identified by its <tt>{employeeId}</tt> with
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
	 * @param dependantId refers to attribute {@code dependantId}
	 * @return a single {@link BzbTDependant} identified by its employeeId with Response Status as 200 OK
	 */
	@RequestMapping(value="/{dependantId}", method=RequestMethod.GET)
	public ResponseEntity<Resource<BzbTDependant>> getDependant(@PathVariable Long companyId,
			@PathVariable Long employeeId, @PathVariable Long dependantId) {
		// Retrieves requested Dependant details
		BzbTDependant employee = dependantService.getDependant(companyId, employeeId, dependantId);
		// Populates All Links for Companies, Company, Employees, Employee, Dependants, and Dependant
	    Links allLinks = populateLinks(companyId, employeeId, dependantId);
		// Initialises HATEOAS Resource for requested Dependant Resource with list of Links
		Resource<BzbTDependant> resource = new Resource<BzbTDependant>(employee, allLinks);
		// Returns the ResponseEntity with HTTPStatus as OK {200}
		return ResponseEntity.ok(resource);
	}

	/**
	 * <strong>Updates a Dependant Entity [<tt>PUT</tt>]</strong>
	 * <br>
	 * Updates an existing <tt>Dependant</tt> details identified by its
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
	 * @param dependant refers to an edited instance of {@link BzbTDependant}
	 * @return an updated {@link BzbTDependant} identified by its employeeId with Response Status as 201 Created
	 */
	@RequestMapping(value="/{dependantId}", method=RequestMethod.PUT)
	public ResponseEntity<Resource<BzbTDependant>> updateDependant(@PathVariable Long companyId,
			@PathVariable Long employeeId, @PathVariable Long dependantId, @RequestBody BzbTDependant dependant) {
		// Updates and Returns edited Dependant details
		BzbTDependant updatedDependant = dependantService.updateDependant(companyId, employeeId, dependantId, dependant);
		// Populates All Links for Companies, Company, Employees, Employee, Dependants, and Dependant
	    Links allLinks = populateLinks(companyId, employeeId, dependantId);
		// Initialises HATEOAS Resource for updated Dependant Resource with list of Links
		Resource<BzbTDependant> resource = new Resource<BzbTDependant>(updatedDependant, allLinks);
		// URI Builder to build updated resource location
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.buildAndExpand(updatedDependant.getDependantId()).toUri();
		// Returns the ResponseEntity with HTTPStatus as Created {201}
		return ResponseEntity.created(location).body(resource);
	}
	
	/**
	 * <strong>Deletes a Dependant Entity [<tt>DELETE</tt>]</strong>
	 * <br>
	 * Deletes an existing <tt>Dependant</tt> details identified by its
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
	@RequestMapping(value="/{dependantId}", method=RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteDependant(@PathVariable Long companyId, @PathVariable Long employeeId, @PathVariable Long dependantId) {
		// Deletes the requested Dependant Resource with Response HTTPStatus as No Content {204}
		dependantService.deleteDependant(companyId, employeeId, dependantId);
	}

	/**
	 * Populates and Returns All Links for Companies, Company, Dependants, Dependant, and Dependants 
	 * 
	 * @param companyId refers to attribute {@code companyId}
	 * @param employeeId refers to attribute {@code employeeId}
	 * @return {@link Links} of Parent Links for this class
	 */
	private Links populateLinks(Long companyId, Long employeeId, Long dependantId) {
		// Parent Link to All Companies Resource [/companies]
		Link companiesLink = linkTo(methodOn(CompanyResource.class).getAllCompanies()).withRel("companies");
		// Parent Link to Company Resource [/companies/{companyId}]
		Link companyLink = linkTo(methodOn(CompanyResource.class).getCompany(companyId)).withRel("company");
		// Parent Link to All Companies Resource [/companies/{companyId}/employees]
		Link employeesLink = linkTo(methodOn(EmployeeResource.class).getAllEmployees(companyId)).withRel("employees");
		// Parent Link to Company Resource [/companies/{companyId}/employees/{employeeId}]
		Link employeeLink = linkTo(methodOn(EmployeeResource.class).getEmployee(companyId, employeeId)).withRel("employee");
		// Parent Link to all Dependants Resource [/companies/{companyId}/employees/{employeeId}/dependants]
		Link dependantsLink = linkTo(methodOn(this.getClass()).getAllDependants(companyId, employeeId)).withSelfRel();
		// Creates All Links with companiesLink, companyLink, employeesLink, employeeLink, and dependantsLink
		Links allLinks = new Links(companiesLink, companyLink, employeesLink, employeeLink, dependantsLink);
		// Updates Links for Single Resource Result
		if (null != dependantId) {
			// Updates Dependants Link label to "employees" when Dependant Link is referred as "self"
			dependantsLink = dependantsLink.withRel("dependants");
			// Self Link to newly added or update or retrieved Dependant Resource
			// [/companies/{companyId}/employees/{employeeId}/dependants/{dependantId}]
			Link selfLink = linkTo(methodOn(this.getClass()).getDependant(companyId, employeeId, dependantId)).withSelfRel();
			// Creates All Links with companiesLink, companyLink, employeesLink, employeeLink, dependantsLink, and dependantLink 
			allLinks = new Links(companiesLink, companyLink, employeesLink, employeeLink, dependantsLink, selfLink);
		}
		return allLinks;
	}
}