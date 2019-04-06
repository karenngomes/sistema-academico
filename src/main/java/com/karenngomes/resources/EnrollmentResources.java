package com.karenngomes.resources;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.karenngomes.sistema.db.EnrollmentDAO;
import com.karenngomes.sistema.db.StudentDAO;
import com.karenngomes.sistema.db.SubjectDAO;
import com.karenngomes.sistema.model.Enrollment;
import com.karenngomes.sistema.utils.ErrorMessage;

import io.dropwizard.hibernate.UnitOfWork;
import lombok.AllArgsConstructor;

@Path("/enrollment")
@AllArgsConstructor
@Produces(MediaType.APPLICATION_JSON)
public class EnrollmentResources {
	EnrollmentDAO enrollmentDAO;
	StudentDAO studentDAO;
	SubjectDAO subjectDAO;

	@GET
	@UnitOfWork
	public Response getAll() {

		return Response.ok(enrollmentDAO.findAll()).build();
	}

	@POST
	@UnitOfWork
	public Response createEnrollment(Enrollment e) {
		return Response.ok(enrollmentDAO.persist(e)).build();
	}

	@PUT
	@Path("/{id}/inactive")
	@UnitOfWork
	public Response inactiveEnrollment(@PathParam("id") Long id) {

		Enrollment enrollment = enrollmentDAO.get(id);
		if (enrollment == null) {
			return Response.status(Status.NOT_FOUND).entity(new ErrorMessage("Enrollment not found")).build();
		}

		enrollment.setStatus(false);

		return Response.ok(enrollmentDAO.persist(enrollment)).build();
	}

}
