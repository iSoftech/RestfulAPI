/**
 * 
 */
package com.bayzat.benefits.api.resource;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.bayzat.benefits.api.boot.BayzatBenefitsRestfulAPIAppTests;

/**
 * Bayzat Benefits Restful API Integration Test Suits for All Resources Tests Class
 * 
 * @author Mohamed Yusuff
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
	BayzatBenefitsRestfulAPIAppTests.class,
	RootResourceTests.class,
    CompanyResourceTests.class,
    EmployeeResourceTests.class,
    DependantResourceTests.class
})
public class AllResourceTests {
}
