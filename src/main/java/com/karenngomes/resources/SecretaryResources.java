package com.karenngomes.resources;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.karenngomes.sistema.db.SecretaryDAO;
import com.karenngomes.sistema.model.Secretary;
import com.karenngomes.sistema.utils.ErrorMessage;

import io.dropwizard.hibernate.UnitOfWork;
import lombok.AllArgsConstructor;

@Path("/secretary")
@AllArgsConstructor
@Produces(MediaType.APPLICATION_JSON)
public class SecretaryResources {

	SecretaryDAO secretaryDAO;

	@GET
	@UnitOfWork
	public Response getAll() {

		return Response.ok(secretaryDAO.findAll()).build();
	}

	@POST
	@UnitOfWork
	public Response createSecretary(Secretary secretary) {

		return Response.ok(secretaryDAO.persist(secretary)).build();
	}

	@GET
	@Path("/{id}")
	@UnitOfWork

	public Response getById(@PathParam("id") Long id) {

		Secretary secretary = secretaryDAO.get(id);
		if (secretary == null) {
			return Response.status(Status.NOT_FOUND).entity(new ErrorMessage("University not found")).build();
		}

		return Response.ok(secretaryDAO.get(id)).build();

	}

	@PUT
	@Path("/{id}")
	@UnitOfWork
	public Response updateById(@PathParam("id") Long id, Secretary s) {

		Secretary secretary = secretaryDAO.get(id);

		if (secretary == null) {
			return Response.status(Status.NOT_FOUND).entity(new ErrorMessage("Secretary not found")).build();
		}

		if (s.getType() != null && (secretary.getType() != s.getType())) {
			return Response.status(Status.NOT_FOUND).entity(new ErrorMessage("You can't change secretary type"))
					.build();
		}

		return Response.ok(secretaryDAO.persist(secretary)).build();
	}

	@DELETE
	@Path("/{id}")
	@UnitOfWork
	public Response deleteSecretary(@PathParam("id") Long id) {

		Secretary secretary = secretaryDAO.get(id);
		if (secretary == null) {
			return Response.status(Status.NOT_FOUND).entity(new ErrorMessage("Secretary not found")).build();
		}

		return Response.ok(secretaryDAO.delete(secretary)).build();
	}

}