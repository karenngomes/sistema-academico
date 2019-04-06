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

import com.karenngomes.sistema.db.*;
import com.karenngomes.sistema.model.*;
import com.karenngomes.sistema.utils.AcademicTypes;
import com.karenngomes.sistema.utils.ErrorMessage;

import io.dropwizard.hibernate.UnitOfWork;
import lombok.AllArgsConstructor;

@Path("/enrollment")
@AllArgsConstructor
@Produces(MediaType.APPLICATION_JSON)
public class EnrollmentResources {
	EnrollmentDAO enrollmentDAO;
	CourseDAO courseDAO;
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
		Course course = courseDAO.get(e.getCourse().getId());
		Student student = studentDAO.get(e.getStudent().getId());

		if(course == null)
			return Response.status(Status.NOT_FOUND).entity(new ErrorMessage("Curso não encontrado")).build();

		if(student == null)
			return Response.status(Status.NOT_FOUND).entity(new ErrorMessage("Estudante não encontrado")).build();

		e.setCourse(course);
		e.setStudent(student);

		return Response.ok(enrollmentDAO.persist(e)).build();
	}

	@POST
	@UnitOfWork
	@Path("/{id}/add_subject/{sId}")
	public Response addNewSubject(@PathParam("id") Long id, @PathParam("sId") Long sId) {
		Enrollment enrollment = enrollmentDAO.get(id);
		Subject subject = subjectDAO.get(sId);

		Student student = enrollment.getStudent();

		if(enrollment.getCourse() == null)

		if (!subject.verifyStudentHasRequiredSubject(enrollment)) {
			return Response.status(Status.FORBIDDEN).entity(new ErrorMessage("tem todas as materias n")).build();
		}

		if (student.getCredits() != 0 && student.getCredits() < subject.getRequiredCredits()) {
			return Response.status(Status.FORBIDDEN).entity(new ErrorMessage("tem todos os creditos n")).build();
		}

		if (enrollment.getCourse() != subject.getCourse()) {
			if (enrollment.getCourse().getType() == AcademicTypes.UNDERGRADUATE) {
				if (student.getCredits() < 170)
					return Response.status(Status.FORBIDDEN)
							.entity(new ErrorMessage("quer cursar disciplina de pos mas tem menos q 170 creditos"))
							.build();
			} else {
				return Response.status(Status.FORBIDDEN).entity(new ErrorMessage("eh de pos")).build();
			}
		}

		switch (enrollment.addSubject(subject)) {
			case"COMPLETED_SUBJECT":
				return Response.status(Status.FORBIDDEN).entity(new ErrorMessage("Student already completed this subject"))
						.build();
			case "CURRENT_SUBJECT":
				return Response.status(Status.FORBIDDEN).entity(new ErrorMessage("Student already contains this subject"))
						.build();
			default:
				return Response.ok(enrollmentDAO.persist(enrollment)).build();
		}
	}

	@POST
	@UnitOfWork
	@Path("/{id}/subject/{sId}")
	public Response addCompletedSubject(@PathParam("id") Long id, @PathParam("sId") Long sId) {
		Enrollment enrollment = enrollmentDAO.get(id);
		Subject subject = subjectDAO.get(sId);

		if (!enrollment.completeSubject(subject)) {
			return Response.status(Status.FORBIDDEN)
					.entity(new ErrorMessage("error to try put this subject in completed subject list")).build();
		}

		return Response.ok(enrollmentDAO.persist(enrollment)).build();

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
