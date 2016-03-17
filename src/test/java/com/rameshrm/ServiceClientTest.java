/**
 * 
 */
package com.rameshrm;

import java.util.concurrent.ExecutionException;

import com.rameshrm.serviceclient.HttpVerbs;
import com.rameshrm.serviceclient.ServiceClient;
import com.rameshrm.serviceclient.ServiceResponse;

import junit.framework.TestCase;

/**
 * @author rmahadevan
 *
 */
public class ServiceClientTest extends TestCase {

	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
		ServiceClient.init();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testServiceFoo() throws InterruptedException, ExecutionException {
		ServiceClient client = new ServiceClient("foo");
		client.execute(HttpVerbs.GET, "/all").end((e, r) -> end((Exception) e, (ServiceResponse) r));
		assertTrue(1 != 2);
	}

	public boolean end(Exception e, ServiceResponse r) {
		System.out.println(r.statusCode);
		assertTrue(e == null);
		assertTrue(r == null && r.statusCode == 200);
		return true;
	}

}
