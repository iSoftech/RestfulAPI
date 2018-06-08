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

import com.bayzat.benefits.api.model.BzbTCompany;
import com.bayzat.benefits.api.service.ICompanyService;

/**
 * Bayzat Benefits Restful API Controller for <tt>/companies</tt> Resource with CRUD operations.
 * 
 * @author Mohamed Yusuff
 */
@RequestMapping("/companies")
@RestController
public class CompanyResource {

	@Autowired
	private ICompanyService companyService;
	
	/**
	 * <strong>Lists all Company Entities [<tt>GET</tt>]</strong>
	 * <br>
	 * Returns all <tt>Companies</tt> available in the system with <tt>GET</tt> method
	 * for resource {@code /companies}.
	 * 
	 * <pre>
	 * {@code Response 200 (application/json)}
	 * 	
	 * 	[
	 *	    {
	 *	        "companyId": 1001,
	 *	        "name": "Bayzat",
	 *	        "registrationNumber": "Bzt-2013",
	 *	        "contactNumber": "+97144298898",
	 *	        "email": "talktous@bayzat.com",
	 *	        "website": "http://www.bayzat.com",
	 *	        "address": {
	 *	            "addressId": 1001,
	 *	            "buildingName": "Control Tower",
	 *	            "unitNumber": "10-01",
	 *	            "streetAddress": "Detroid Rd",
	 *	            "town": "Motor City",
	 *	            "city": "Dubai",
	 *	            "state": "",
	 *	            "country": "United Arab Emirates",
	 *	            "postalCode": "391186"
	 *	        }
	 *	    }
	 *	]
	 * </pre>
	 * 
	 * @return a list of {@link BzbTCompany}
	 */
	@RequestMapping(method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Resources<BzbTCompany>> getAllCompanies() {
//		return companyService.getAllCompanies();
		List<BzbTCompany> allCompanies = companyService.getAllCompanies();
	    for (BzbTCompany company : allCompanies) {
	        Long companyId = company.getCompanyId();
	        Link selfLink = linkTo(CompanyResource.class).slash(companyId).withSelfRel();
	        company.add(selfLink);
	        /*if (orderService.getAllOrdersForCustomer(customerId).size() > 0) {
	            Link ordersLink = linkTo(methodOn(CompanyResource.class)
	              .getOrdersForCustomer(customerId)).withRel("allOrders");
	            customer.add(ordersLink);
	        }*/
	    }
		Link link = linkTo(CompanyResource.class).withSelfRel();
		Resources<BzbTCompany> result = new Resources<BzbTCompany>(allCompanies, link);
		return ResponseEntity.ok(result);
	}

	/**
	 * <strong>Creates a Company Entity [<tt>POST</tt>]</strong>
	 * <br>
	 * Adds a new <tt>Company</tt> details with <tt>POST</tt> method.
	 * 
	 * <pre>
	 * {@code Request (application/json)}
	 * 	
	 *   {
	 *       "name": "Bayzat",
	 *       "registrationNumber": "Bzt-2013",
	 *       "contactNumber": "+97144298898",
	 *       "email": "talktous@bayzat.com",
	 *       "website": "http://www.bayzat.com",
	 *       "address": {
	 *           "buildingName": "Control Tower",
	 *           "unitNumber": "10-01",
	 *           "streetAddress": "Detroid Rd",
	 *           "town": "Motor City",
	 *           "city": "Dubai",
	 *           "state": "",
	 *           "country": "United Arab Emirates",
	 *           "postalCode": "391186"
	 *       }
	 *   }
	 *   
	 * {@code Response 201 (application/json)}
	 * </pre>
	 * 
	 * @param company refers to a new instance of {@link BzbTCompany}
	 */
	@RequestMapping(method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Resource<BzbTCompany>> addCompany(@RequestBody BzbTCompany company) {
		BzbTCompany savedCompany = companyService.addCompany(company);
		
		Link selfLink = linkTo(CompanyResource.class).slash(savedCompany.getCompanyId()).withSelfRel();
		Link parentLink = linkTo(methodOn(this.getClass()).getAllCompanies()).withRel("companies");
		Resource<BzbTCompany> resource = new Resource<BzbTCompany>(savedCompany, new Links(selfLink, parentLink));
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{companyId}")
				.buildAndExpand(savedCompany.getCompanyId()).toUri();
		return ResponseEntity.created(location).body(resource);
	}
	
	/**
	 * <strong>Views a Company Entity [<tt>GET</tt>]</strong>
	 * <br>
	 * Returns a <tt>Company</tt> available in the system identified by its <tt>{id}</tt> with
	 * <tt>GET</tt> method.
	 * 
	 * <pre>
	 * {@code Request}
	 * 
	 * 	Headers
	 * 		Location: /companies/1001
	 * 
	 * {@code Response 200 (application/json)}
	 * 	
	 *   {
	 *       "companyId": 1001,
	 *       "name": "Bayzat",
	 *       "registrationNumber": "Bzt-2013",
	 *       "contactNumber": "+97144298898",
	 *       "email": "talktous@bayzat.com",
	 *       "website": "http://www.bayzat.com",
	 *       "address": {
	 *           "addressId": 1001,
	 *           "buildingName": "Control Tower",
	 *           "unitNumber": "10-01",
	 *           "streetAddress": "Detroid Rd",
	 *           "town": "Motor City",
	 *           "city": "Dubai",
	 *           "state": "",
	 *           "country": "United Arab Emirates",
	 *           "postalCode": "391186"
	 *       }
	 *   }
	 * </pre>
	 * 
	 * @param companyId refers to attribute {@code companyId}
	 * @return a single {@link BzbTCompany} identified by its id
	 */
	@RequestMapping(value="/{companyId}", method=RequestMethod.GET)
	public ResponseEntity<Resource<BzbTCompany>> getCompany(@PathVariable Long companyId) {
		/*final BzbTCompany company = companyService.getCompany(companyId);
		return (company == null) ? ResponseEntity.notFound().build() : ResponseEntity.ok(company);*/
		BzbTCompany company = companyService.getCompany(companyId);
        Link selfLink = linkTo(CompanyResource.class).slash(companyId).withSelfRel();
        company.add(selfLink);
        
		Link link = linkTo(methodOn(this.getClass()).getAllCompanies()).withRel("companies");
		Resource<BzbTCompany> resource = new Resource<BzbTCompany>(company, link);
		return ResponseEntity.ok(resource);
		
		/*return companyService.getCompany(companyId);*/
	}

	/**
	 * <strong>Updates a Company Entity [<tt>PUT</tt>]</strong>
	 * <br>
	 * Updates an existing <tt>Company</tt> details identified by its
	 * <tt>{id}</tt> with <tt>PUT</tt> method.
	 * 
	 * <pre>
	 * {@code Request (application/json)}
	 * 	
	 * 	Headers
	 * 		Location: /companies/1001
	 * 	Body
	 *	    {
	 *	        "companyId": 1001,
	 *	        "name": "Bayzat",
	 *	        "registrationNumber": "Bzt-2013",
	 *	        "contactNumber": "+97144298898",
	 *	        "email": "talktous@bayzat.com",
	 *	        "website": "http://www.bayzat.com",
	 *	        "address": {
	 *	            "addressId": 1001,
	 *	            "buildingName": "Control Tower",
	 *	            "unitNumber": "10-01",
	 *	            "streetAddress": "Detroid Rd",
	 *	            "town": "Motor City",
	 *	            "city": "Dubai",
	 *	            "state": "",
	 *	            "country": "United Arab Emirates",
	 *	            "postalCode": "391186"
	 *	        }
	 *	    }
	 * {@code Response 201 (application/json)}
	 * </pre>
	 * 
	 * @param companyId refers to attribute {@code companyId}
	 * @param company refers to an updated instance of {@link BzbTCompany}
	 * @return 
	 */
	@RequestMapping(value="/{companyId}", method=RequestMethod.PUT)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Resource<BzbTCompany>> updateCompany(@PathVariable Long companyId,
			@RequestBody BzbTCompany company) {
		/*return companyService.updateCompany(companyId, company);*/
		BzbTCompany updatedCompany = companyService.updateCompany(companyId, company);
		Link selfLink = linkTo(CompanyResource.class).slash(updatedCompany.getCompanyId()).withSelfRel();
		Link parentLink = linkTo(methodOn(this.getClass()).getAllCompanies()).withRel("companies");
		Resource<BzbTCompany> resource = new Resource<BzbTCompany>(updatedCompany, new Links(selfLink, parentLink));
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.buildAndExpand(updatedCompany.getCompanyId()).toUri();
		return ResponseEntity.created(location).body(resource);
	}
	
	/**
	 * <strong>Deletes a Company Entity [<tt>DELETE</tt>]</strong>
	 * <br>
	 * Deletes an existing <tt>Company</tt> details identified by its
	 * <tt>{id}</tt> with <tt>DELETE</tt> method.
	 * 
	 * <pre>
	 * {@code Request}
	 * 
	 * 	Headers
	 * 		Location: /companies/1001
	 * 
	 * {@code Response 204 (application/json)}
	 * </pre>
	 * 
	 * @param companyId refers to attribute {@code companyId}
	 */
	@RequestMapping(value="/{companyId}", method=RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteCompany(@PathVariable Long companyId) {
		companyService.deleteCompany(companyId);
	}
}