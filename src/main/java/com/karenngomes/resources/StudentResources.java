package com.karenngomes.resources;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.karenngomes.sistema.db.StudentDAO;
import com.karenngomes.sistema.db.SubjectDAO;
import com.karenngomes.sistema.model.Student;
import com.karenngomes.sistema.utils.ErrorMessage;

import io.dropwizard.hibernate.UnitOfWork;
import lombok.AllArgsConstructor;

@Path("/student")
@AllArgsConstructor
@Produces(MediaType.APPLICATION_JSON)
public class StudentResources {
	StudentDAO studentDAO;
	SubjectDAO subjectDAO;
	
	@GET
	@UnitOfWork
	public Response getAll() {

		return Response.ok(studentDAO.findAll()).build();
	}

	
	@POST
	@UnitOfWork
	public Response createStudent(Student d) {
		return Response.ok(studentDAO.persist(d)).build();
	}
	
	@GET
	@Path("/{id}")
	@UnitOfWork
	public Response getById(@PathParam("id") Long id) {
		
		Student student = studentDAO.get(id);
        if (student == null) { 
            return Response.status(Status.NOT_FOUND).entity(new ErrorMessage("Student not found")).build();
        }
		
		return Response.ok(studentDAO.get(id)).build();	
	}
	
	@DELETE
	@Path("/{id}")
	@UnitOfWork
	public Response deleteStudent(@PathParam("id") Long id) {
		
		Student student = studentDAO.get(id);
        if (student == null) { 
            return Response.status(Status.NOT_FOUND).entity(new ErrorMessage("Student not found")).build();
        }
		
		return Response.ok(studentDAO.delete(student)).build();	
	}
	
}
