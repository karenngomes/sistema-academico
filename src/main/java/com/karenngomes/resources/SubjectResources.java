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

import com.karenngomes.sistema.db.CourseDAO;
import com.karenngomes.sistema.db.ProfessorDAO;
import com.karenngomes.sistema.db.SubjectDAO;
import com.karenngomes.sistema.model.Course;
import com.karenngomes.sistema.model.Department;
import com.karenngomes.sistema.model.Professor;
import com.karenngomes.sistema.model.Subject;
import com.karenngomes.sistema.model.Subject;
import com.karenngomes.sistema.utils.ErrorMessage;

import io.dropwizard.hibernate.UnitOfWork;
import lombok.AllArgsConstructor;

@Path("/subject")
@AllArgsConstructor
@Produces(MediaType.APPLICATION_JSON)
public class SubjectResources {
	SubjectDAO subjectDAO;
	ProfessorDAO professorDAO;
	CourseDAO courseDAO;

	@GET
	@UnitOfWork
	public Response getAll() {		
		return Response.ok(subjectDAO.findAll()).build();			
	}
	
	@POST
	@UnitOfWork
	public Response createSubject(Subject s) {
		return Response.ok(subjectDAO.persist(s)).build();
	}
	
	@POST
	@Path("/{id}/professor/{Pid}")
	@UnitOfWork
	public Response addProfessor(@PathParam("id") Long id, @PathParam("Pid") Long pId) {

		Subject subject = subjectDAO.get(id);
		Professor professor = professorDAO.get(pId);

		if (subject == null) {
			return Response.status(Status.NOT_FOUND).entity(new ErrorMessage("Subject not found")).build();
		}

		if (professor == null) {
			return Response.status(Status.NOT_FOUND).entity(new ErrorMessage("Professor not found")).build();
		}

		subject.setProfessor(professor);

		return Response.ok(subjectDAO.persist(subject)).build();
	}
	
	@POST
	@Path("/{id}/course/{cId}")
	@UnitOfWork
	public Response addCourse(@PathParam("id") Long id, @PathParam("cId") Long cId) {

		Subject subject = subjectDAO.get(id);
		Course course = courseDAO.get(cId);

		if (subject == null) {
			return Response.status(Status.NOT_FOUND).entity(new ErrorMessage("Subject not found")).build();
		}

		if (course == null) {
			return Response.status(Status.NOT_FOUND).entity(new ErrorMessage("Course not found")).build();
		}
		
		if(subject.getType() != course.getType()) {
			return Response.status(Status.NOT_FOUND).entity(new ErrorMessage("Subject and course aren't the same type")).build();
		}

		subject.setCourse(course);

		return Response.ok(subjectDAO.persist(subject)).build();
	}
	
	@POST
	@Path("/{id}/add_required_subject/{sId}")
	@UnitOfWork
	public Response addRequiredSubject(@PathParam("id") Long id, @PathParam("sId") Long sId) {

		Subject subject = subjectDAO.get(id);
		Subject requiredSubject = subjectDAO.get(sId);

		if (subject == null) {
			return Response.status(Status.NOT_FOUND).entity(new ErrorMessage("Subject not found")).build();
		}

		if (requiredSubject == null) {
			return Response.status(Status.NOT_FOUND).entity(new ErrorMessage("Required subject not found")).build();
		}

		if(!subject.addRequiredSubject(requiredSubject)) {
			return Response.status(Status.NOT_FOUND).entity(new ErrorMessage("Required subject already is a required subject")).build();
		}
		

		return Response.ok(subjectDAO.persist(subject)).build();
	}

	@DELETE
	@Path("/{id}")
	@UnitOfWork
	public Response deleteSubject(@PathParam("id") Long id) {
		
		Subject subject = subjectDAO.get(id);
        if (subject == null) { 
            return Response.status(Status.NOT_FOUND).entity(new ErrorMessage("Subject not found")).build();
        }
		
		return Response.ok(subjectDAO.delete(subject)).build();	
	}
	
}
