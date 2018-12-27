package com.karenngomes.sistema;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("")
@Produces(MediaType.APPLICATION_JSON)
public class TestResource {
	
	@GET
	public String get_anyone_is_fine() {
		return "Hello, World!";
	}
	
}
