package com.bayzat.benefits.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bayzat.benefits.api.model.BzbTCompany;
import com.bayzat.benefits.api.service.ICompanyService;

/**
 * Bayzat Benefits Restful API Controller for <tt>/companies</tt> Resource with CRUD operations.
 * 
 * @author Mohamed Yusuff
 */
@RestController
public class CompanyController {

	private static final String COLLECTION_URL = "/companies";
	private static final String ITEM_URL = COLLECTION_URL + "/{companyId}";
	
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
	@RequestMapping(value=COLLECTION_URL, method=RequestMethod.GET)
	public List<BzbTCompany> getAllCompanies() {
		return companyService.getAllCompanies();
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
	@RequestMapping(value=ITEM_URL, method=RequestMethod.GET)
	public /*ResponseEntity<?>*/ BzbTCompany getCompany(@PathVariable Long companyId) {
		/*final BzbTCompany company = companyService.getCompany(companyId);
		return (company == null) ? ResponseEntity.notFound().build() : ResponseEntity.ok(company);*/
		return companyService.getCompany(companyId);
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
	@RequestMapping(value=COLLECTION_URL, method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public void addCompany(@RequestBody BzbTCompany company) {
		companyService.addCompany(company);
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
	 */
	@RequestMapping(value=ITEM_URL, method=RequestMethod.PUT)
	@ResponseStatus(HttpStatus.CREATED)
	public void updateCompany(@PathVariable Long companyId, @RequestBody BzbTCompany company) {
		companyService.updateCompany(companyId, company);
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
	@RequestMapping(value=ITEM_URL, method=RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteCompany(@PathVariable Long companyId) {
		companyService.deleteCompany(companyId);
	}
}