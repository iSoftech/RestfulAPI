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

	private static final String MEDIA_TYPE_APPLICATION_HAL_JSON = "application/hal+json";
	
	@Autowired
	private ICompanyService companyService;
	
	/**
	 * <strong>Lists all Companies Entities [<tt>GET</tt>]</strong>
	 * <br>
	 * Returns all <tt>Companies</tt> available in the system with <tt>GET</tt> method
	 * for resource {@code /companies}.
	 * 
	 * <pre>
	 * {@code Response 200 (application/hal+json)}
	 * 	
	 *	{
	 *	    "_embedded": {
	 *	        "bzbTCompanyList": [
	 *	            {
	 *	                "companyId": 1001,
	 *	                "name": "Bayzat",
	 *	                "registrationNumber": "Bzt-2013",
	 *	                "contactNumber": "+97144298898",
	 *	                "email": "talktous@bayzat.com",
	 *	                "website": "http://www.bayzat.com",
	 *	                "address": {
	 *	                    "addressId": 1001,
	 *	                    "buildingName": "Control Tower",
	 *	                    "unitNumber": "10-01",
	 *	                    "streetAddress": "Detroid Rd",
	 *	                    "town": "Motor City",
	 *	                    "city": "Dubai",
	 *	                    "state": "Dubai",
	 *	                    "country": "United Arab Emirates",
	 *	                    "postalCode": "391186"
	 *	                },
	 *	                "_links": {
	 *	                    "self": {
	 *	                        "href": "http://localhost:8086/BayzatBenefits/api/companies/1001"
	 *	                    }
	 *	                }
	 *	            }
	 *	        ]
	 *	    },
	 *	    "_links": {
	 *	        "self": {
	 *	            "href": "http://localhost:8086/BayzatBenefits/api/companies"
	 *	        }
	 *	    }
	 *	}
	 * </pre>
	 * 
	 * @return a list of {@link BzbTCompany} with Response Status as 200 OK
	 */
	@RequestMapping(method=RequestMethod.GET, produces=MEDIA_TYPE_APPLICATION_HAL_JSON)
	public ResponseEntity<Resources<BzbTCompany>> getAllCompanies() {
		// Retrieves All Companies details
		List<BzbTCompany> allCompanies = companyService.getAllCompanies();
		// Iterates through all companies details to set Self Link and individual Company Resource Link
	    for (BzbTCompany company : allCompanies) {
	        // Self Link to newly added Company resource {/companies/{companyId}}
	        Link selfLink = linkTo(CompanyResource.class).slash(company.getCompanyId()).withSelfRel();
	        company.add(selfLink);
	        /*if (orderService.getAllOrdersForCustomer(customerId).size() > 0) {
	            Link ordersLink = linkTo(methodOn(CompanyResource.class)
	              .getOrdersForCustomer(customerId)).withRel("allOrders");
	            customer.add(ordersLink);
	        }*/
	    }
	    // Parent Link to all Companies Resource {/companies}
		Link parentLink = linkTo(CompanyResource.class).withSelfRel();
		// Initialises HATEOAS Resource for newly added Company Resource with list of Links
		Resources<BzbTCompany> result = new Resources<BzbTCompany>(allCompanies, parentLink);
		// Returns the ResponseEntity with HTTPStatus as OK {200}
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
	 * {@code Response 201 (application/hal+json)}
	 * 
	 *   {
	 *   	 "companyId": 1001,
	 *       "name": "Bayzat",
	 *       "registrationNumber": "Bzt-2013",
	 *       "contactNumber": "+97144298898",
	 *       "email": "talktous@bayzat.com",
	 *       "website": "http://www.bayzat.com",
	 *       "address": {
	 *       	 "addressId": 1001,
	 *           "buildingName": "Control Tower",
	 *           "unitNumber": "10-01",
	 *           "streetAddress": "Detroid Rd",
	 *           "town": "Motor City",
	 *           "city": "Dubai",
	 *           "state": "",
	 *           "country": "United Arab Emirates",
	 *           "postalCode": "391186"
	 *       },
	 *	     "_links": {
	 *	         "self": {
	 *	             "href": "http://localhost:8086/BayzatBenefits/api/companies/1001"
	 *	         },
	 *	         "companies": {
	 *	             "href": "http://localhost:8086/BayzatBenefits/api/companies"
	 *	         }
	 *	     }
	 *   }
	 * </pre>
	 * 
	 * @param company refers to a new instance of {@link BzbTCompany}
	 * @return a newly added {@link BzbTCompany} with Response Status as 201 Created
	 */
	@RequestMapping(method=RequestMethod.POST, produces=MEDIA_TYPE_APPLICATION_HAL_JSON)
	public ResponseEntity<Resource<BzbTCompany>> addCompany(@RequestBody BzbTCompany company) {
		// Adds and Returns newly added Company details
		BzbTCompany savedCompany = companyService.addCompany(company);
		// Self Link to newly added Company Resource {/companies/{companyId}}
		Link selfLink = linkTo(CompanyResource.class).slash(savedCompany.getCompanyId()).withSelfRel();
		// Parent Link to all Companies Resource {/companies}
		Link parentLink = linkTo(methodOn(this.getClass()).getAllCompanies()).withRel("companies");
		// Initialises HATEOAS Resource for newly added Company Resource with list of Links
		Resource<BzbTCompany> resource = new Resource<BzbTCompany>(savedCompany, new Links(selfLink, parentLink));
		// URI Builder to build newly created resource location
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{companyId}")
				.buildAndExpand(savedCompany.getCompanyId()).toUri();
		// Returns the ResponseEntity with HTTPStatus as Created {201}
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
	 * {@code Response 200 (application/hal+json)}
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
	 *       },
	 *	     "_links": {
	 *	         "self": {
	 *	             "href": "http://localhost:8086/BayzatBenefits/api/companies/1001"
	 *	         },
	 *	         "companies": {
	 *	             "href": "http://localhost:8086/BayzatBenefits/api/companies"
	 *	         }
	 *	     }
	 *   }
	 * </pre>
	 * 
	 * @param companyId refers to attribute {@code companyId}
	 * @return a single {@link BzbTCompany} identified by its id with Response Status as 200 OK
	 */
	@RequestMapping(value="/{companyId}", method=RequestMethod.GET, produces=MEDIA_TYPE_APPLICATION_HAL_JSON)
	public ResponseEntity<Resource<BzbTCompany>> getCompany(@PathVariable Long companyId) {
		// Retrieves requested Company details
		BzbTCompany company = companyService.getCompany(companyId);
		// Self Link to requested Company Resource {/companies/{companyId}}
		Link selfLink = linkTo(CompanyResource.class).slash(company.getCompanyId()).withSelfRel();
		// Parent Link to all Companies Resource {/companies}
		Link parentLink = linkTo(methodOn(this.getClass()).getAllCompanies()).withRel("companies");
		// Initialises HATEOAS Resource for requested Company Resource with list of Links
		Resource<BzbTCompany> resource = new Resource<BzbTCompany>(company, new Links(selfLink, parentLink));
		// Returns the ResponseEntity with HTTPStatus as OK {200}
		return ResponseEntity.ok(resource);
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
	 *	        "name": "Bayzat",
	 *	        "registrationNumber": "Bzt-2018",
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
	 * {@code Response 201 (application/hal+json)}
	 * 
	 *   {
	 *   	 "companyId": 1001,
	 *       "name": "Bayzat",
	 *       "registrationNumber": "Bzt-2018",
	 *       "contactNumber": "+97144298898",
	 *       "email": "talktous@bayzat.com",
	 *       "website": "http://www.bayzat.com",
	 *       "address": {
	 *       	 "addressId": 1001,
	 *           "buildingName": "Control Tower",
	 *           "unitNumber": "10-01",
	 *           "streetAddress": "Detroid Rd",
	 *           "town": "Motor City",
	 *           "city": "Dubai",
	 *           "state": "",
	 *           "country": "United Arab Emirates",
	 *           "postalCode": "391186"
	 *       },
	 *	     "_links": {
	 *	         "self": {
	 *	             "href": "http://localhost:8086/BayzatBenefits/api/companies/1001"
	 *	         },
	 *	         "companies": {
	 *	             "href": "http://localhost:8086/BayzatBenefits/api/companies"
	 *	         }
	 *	     }
	 *   }
	 * </pre>
	 * 
	 * @param companyId refers to attribute {@code companyId}
	 * @param company refers to an edited instance of {@link BzbTCompany}
	 * @return an updated {@link BzbTCompany} identified by its id with Response Status as 201 Created
	 */
	@RequestMapping(value="/{companyId}", method=RequestMethod.PUT, produces=MEDIA_TYPE_APPLICATION_HAL_JSON)
	public ResponseEntity<Resource<BzbTCompany>> updateCompany(@PathVariable Long companyId,
			@RequestBody BzbTCompany company) {
		// Updates and Returns edited Company details
		BzbTCompany updatedCompany = companyService.updateCompany(companyId, company);
		// Self Link to updated Company Resource {/companies/{companyId}}
		Link selfLink = linkTo(CompanyResource.class).slash(updatedCompany.getCompanyId()).withSelfRel();
		// Parent Link to all Companies Resource {/companies}
		Link parentLink = linkTo(methodOn(this.getClass()).getAllCompanies()).withRel("companies");
		// Initialises HATEOAS Resource for updated Company Resource with list of Links
		Resource<BzbTCompany> resource = new Resource<BzbTCompany>(updatedCompany, new Links(selfLink, parentLink));
		// URI Builder to build updated resource location
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.buildAndExpand(updatedCompany.getCompanyId()).toUri();
		// Returns the ResponseEntity with HTTPStatus as Created {201}
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
	 * {@code Response 204}
	 * </pre>
	 * 
	 * @param companyId refers to attribute {@code companyId}
	 */
	@RequestMapping(value="/{companyId}", method=RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteCompany(@PathVariable Long companyId) {
		// Deletes the requested Company Resource with Response HTTPStatus as No Content {204}
		companyService.deleteCompany(companyId);
	}
}