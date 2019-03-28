package com.karenngomes.resources;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.karenngomes.sistema.model.Test;

@Path("/secretary")
@Produces(MediaType.APPLICATION_JSON)
public class SecretaryResources {
	
	@GET
	public String get_anyone_is_fine() {
		return "Hello, World!";
	}
	
	@Path("/teste")
	@GET
	public String getTudo () {
		Test t = new Test();
		return t.toString();
	}
	
}