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

import com.karenngomes.sistema.db.DepartmentDAO;
import com.karenngomes.sistema.db.ProfessorDAO;
import com.karenngomes.sistema.db.SubjectDAO;
import com.karenngomes.sistema.model.Professor;
import com.karenngomes.sistema.utils.ErrorMessage;

import io.dropwizard.hibernate.UnitOfWork;
import lombok.AllArgsConstructor;

@Path("/professor")
@AllArgsConstructor
@Produces(MediaType.APPLICATION_JSON)
public class ProfessorResources {
	DepartmentDAO departmentDAO;
	ProfessorDAO professorDAO;
	SubjectDAO subjectDAO;
	
	@GET
	@UnitOfWork
	public Response getAll() {
		
		return Response.ok(professorDAO.findAll()).build();			
	}
	
	@POST
	@UnitOfWork
	public Response createProfessor(Professor p) {
		return Response.ok(professorDAO.persist(p)).build();
	}
	
	@GET
	@Path("/{id}")
	@UnitOfWork
	public Response getById(@PathParam("id") Long id) {
		
		Professor professor = professorDAO.get(id);
        if (professor == null) { 
            return Response.status(Status.NOT_FOUND).entity(new ErrorMessage("Professor not found")).build();
        }
		
		return Response.ok(professorDAO.get(id)).build();	
	}
	
	@PUT
	@Path("/{id}")
	@UnitOfWork
	public Response updateById(@PathParam("id") Long id, Professor p) {

		Professor professor = professorDAO.get(id);
		
        if (professor == null) { 
            return Response.status(Status.NOT_FOUND).entity(new ErrorMessage("Department not found")).build();
        }
        
        if(p.getFirstName() != null)
        	professor.setFirstName(p.getFirstName());
        if(p.getLastName() != null)
        	professor.setLastName(p.getLastName());
        if(p.getSiap() != null)
        	professor.setSiap(p.getSiap());
       
        
        return Response.ok(professorDAO.persist(professor)).build();
	}
	
	
	@DELETE
	@Path("/{id}")
	@UnitOfWork
	public Response deleteProfessor(@PathParam("id") Long id) {
		
		Professor professor = professorDAO.get(id);
        if (professor == null) { 
            return Response.status(Status.NOT_FOUND).entity(new ErrorMessage("Professor not found")).build();
        }
		
		return Response.ok(professorDAO.delete(professor)).build();	
	}
}
