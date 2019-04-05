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

import com.karenngomes.sistema.db.CourseDAO;
import com.karenngomes.sistema.db.SecretaryDAO;
import com.karenngomes.sistema.model.Course;
import com.karenngomes.sistema.model.Department;
import com.karenngomes.sistema.model.Secretary;
import com.karenngomes.sistema.utils.ErrorMessage;

import io.dropwizard.hibernate.UnitOfWork;
import lombok.AllArgsConstructor;

@Path("/course")
@AllArgsConstructor
@Produces(MediaType.APPLICATION_JSON)
public class CourseResources {
	CourseDAO courseDAO;
	SecretaryDAO secretaryDAO;

	@GET
	@UnitOfWork
	public Response getAll() {

		return Response.ok(courseDAO.findAll()).build();
	}

	@POST
	@UnitOfWork
	public Response createCourse(Course c) {
		
		Secretary secretary = secretaryDAO.get(c.getSecretary().getId());
		
		if (secretary == null) {
			return Response.status(Status.NOT_FOUND).entity(new ErrorMessage("Secretary not found")).build();
		}
		
		if(secretary.getType() != c.getType()) {
			return Response.status(Status.FORBIDDEN).entity(new ErrorMessage("n sao do msm tipo")).build();
		}
		
		
		return Response.ok(courseDAO.persist(c)).build();
	}

	/*
	@POST
	@Path("/{id}/secretary/{Sid}")
	@UnitOfWork
	public Response addSecretary(@PathParam("id") Long id, @PathParam("Sid") Long sId) {
		
		Course course = courseDAO.get(id);
		Secretary secretary = secretaryDAO.get(sId);

		if (course == null) {
			return Response.status(Status.NOT_FOUND).entity(new ErrorMessage("Course not found")).build();
		}

		if (secretary == null) {
			return Response.status(Status.NOT_FOUND).entity(new ErrorMessage("Secretary not found")).build();
		}
		
		if(secretary.getType() != course.getType()) {
			return Response.status(Status.FORBIDDEN).entity(new ErrorMessage("n sao do msm tipo")).build();
		} 
		
		course.setSecretary(secretary);
		
		return Response.ok(courseDAO.persist(course)).build();
		
	}
	*/
	
	@DELETE
	@Path("/{id}")
	@UnitOfWork
	public Response deleteCourse(@PathParam("id") Long id) {
		
		Course course = courseDAO.get(id);
        if (course == null) { 
            return Response.status(Status.NOT_FOUND).entity(new ErrorMessage("Course not found")).build();
        }
		
		return Response.ok(courseDAO.delete(course)).build();	
	}

}
