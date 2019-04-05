package com.karenngomes.resources;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.karenngomes.sistema.db.UniversityDAO;
import com.karenngomes.sistema.model.University;
import com.karenngomes.sistema.utils.ErrorMessage;

import io.dropwizard.hibernate.UnitOfWork;
import lombok.AllArgsConstructor;

@Path("/university")
@AllArgsConstructor
@Produces(MediaType.APPLICATION_JSON)
public class UniversityResources {
	UniversityDAO universityDAO;
	
	@GET
	@UnitOfWork
	public Response getAll() {
		
		return Response.ok(universityDAO.findAll()).build();			
	}
	
	@POST
	@UnitOfWork
	public Response createUniversity(University un) {
		
		return Response.ok(universityDAO.persist(un)).build();
	}
	
	@GET
	@Path("/{id}")
	@UnitOfWork
	
	public Response getById(@PathParam("id") Long id) {
		
		University university = universityDAO.get(id);
        if (university == null) { 
            return Response.status(Status.NOT_FOUND).entity(new ErrorMessage("University not found")).build();
        }
		
		return Response.ok(universityDAO.get(id)).build();
		
	}
	
	@PUT
	@Path("/{id}")
	@UnitOfWork
	public Response updateById(@PathParam("id") Long id, University un) {

		University university = universityDAO.get(id);
        if (university == null) { 
            return Response.status(Status.NOT_FOUND).entity(new ErrorMessage("University not found")).build();
        }
        
        if(un.getName() != null)
        	university.setName(un.getName());
        
        return Response.ok(universityDAO.persist(university)).build();
	}
	
	@DELETE
	@Path("/{id}")
	@UnitOfWork
	public Response deleteUniversity(@PathParam("id") Long id) {
		
		University university = universityDAO.get(id);
        if (university == null) { 
            return Response.status(Status.NOT_FOUND).entity(new ErrorMessage("Department not found")).build();
        }
		
		return Response.ok(universityDAO.delete(university)).build();	
	}

}
