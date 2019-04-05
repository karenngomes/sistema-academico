package com.karenngomes.sistema.resources;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.URI;
import java.util.List;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
// import org.jboss.jandex.Main;
import org.junit.After;
import org.junit.Before;
 import org.junit.Test;
//import org.junit.jupiter.api.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
 
import org.glassfish.grizzly.http.server.HttpServer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.jaxrs.annotation.JacksonFeatures;
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import com.karenngomes.resources.UniversityResources;
import com.karenngomes.sistema.Main;
import com.karenngomes.sistema.model.University;

import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jackson.Jackson;

public class UniversityResourcesTest extends JerseyTest  {
	
	private static final ObjectMapper MAPPER = Jackson.newObjectMapper();
	
	
	
	//private HttpServer server;
    private WebTarget target;
 
    @Before
    public void setUp() throws Exception {
    	System.out.println("aqui?");
        //server = Main.startServer();
 
        Client c = ClientBuilder.newClient();
        // c.register(JacksonJaxbJsonProvider.class);
        target = c.target(Main.BASE_URI);
        System.out.println("aqui?");
    }
 /*
    @After
    public void tearDown() throws Exception {
        server.shutdownNow();
    }
    */
    @Override
    protected Application configure() {
        return new ResourceConfig().register(UniversityResources.class).register(JacksonFeatures.class);
    }
 
	
	/*
	@Override
    protected Application configure() {
		enable(TestProperties.LOG_TRAFFIC);
        enable(TestProperties.DUMP_ENTITY);	
        return new ResourceConfig(UniversityResources.class);
    }
	*/
	@Test
	public void test001()  {
		// University un = new University("ufal");
		/*
		Response output = target.path("university").request().post(Entity.entity(un, MediaType.APPLICATION_JSON));
		*/
		System.out.println("chegou");
		//try {
			
			University output = target("/university").request().get(University.class);
		//} catch (Exception e) {
		//	System.out.println(e.getStackTrace());
		//	System.out.println(e);
		//}
		System.out.println("aa");
		//assertEquals("oi", "oi");
		/*
        assertEquals("should return status 200", 200, output.getStatus());
        assertNotNull("Should return list", output.getEntity());
        */
	}
	
}
