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
import com.karenngomes.sistema.db.SecretaryDAO;
import com.karenngomes.sistema.model.Department;
import com.karenngomes.sistema.model.Secretary;
import com.karenngomes.sistema.utils.AcademicTypes;
import com.karenngomes.sistema.utils.ErrorMessage;

import io.dropwizard.hibernate.UnitOfWork;
import lombok.AllArgsConstructor;

@Path("/department")
@AllArgsConstructor
@Produces(MediaType.APPLICATION_JSON)
public class DepartmentResources {
	DepartmentDAO departmentDAO;
	SecretaryDAO secretaryDAO;
	
	@GET
	@UnitOfWork
	public Response getAll() {
		
		return Response.ok(departmentDAO.findAll()).build();			
	}
	
	@POST
	@UnitOfWork
	public Response createDepartment(Department d) {
		return Response.ok(departmentDAO.persist(d)).build();
	}
	
	@GET
	@Path("/{id}")
	@UnitOfWork
	
	public Response getById(@PathParam("id") Long id) {
		
		Department department = departmentDAO.get(id);
        if (department == null) { 
            return Response.status(Status.NOT_FOUND).entity(new ErrorMessage("Department not found")).build();
        }
		
		return Response.ok(departmentDAO.get(id)).build();	
	}
	
	@PUT
	@Path("/{id}")
	@UnitOfWork
	public Response updateById(@PathParam("id") Long id, Department d) {

		Department department = departmentDAO.get(id);
		
        if (department == null) { 
            return Response.status(Status.NOT_FOUND).entity(new ErrorMessage("Department not found")).build();
        }
        
        if(d.getName() != null)
        	department.setName(d.getName());
        
        
        return Response.ok(departmentDAO.persist(department)).build();
	}
	
	@POST
	@Path("/{id}/secretary/{Sid}")
	@UnitOfWork
	public Response updateSecretary(@PathParam("id") Long id, @PathParam("Sid") Long sId) {

		Department department = departmentDAO.get(id);
		Secretary secretary = secretaryDAO.get(sId);
		
        if (department == null) { 
            return Response.status(Status.NOT_FOUND).entity(new ErrorMessage("Department not found")).build();
        }
        
        if (secretary == null) { 
            return Response.status(Status.NOT_FOUND).entity(new ErrorMessage("Secretary not found")).build();
        }
        
        if(secretary.getType() == AcademicTypes.UNDERGRADUATE) {
        	if(department.getUnderGraduate() != null) {
        		return Response.status(Status.FORBIDDEN).entity(new ErrorMessage("Undergraduate secretary already exists")).build();
        	} else {
        		department.setUnderGraduate(secretary);
        	}
        } else {
        	if(department.getPostGraduate() != null) {
        		return Response.status(Status.FORBIDDEN).entity(new ErrorMessage("Postgraduate secretary already exists")).build();
        	} else {
        		department.setPostGraduate(secretary);
        	}
        }
               
        return Response.ok(departmentDAO.persist(department)).build();
	}
	
	@DELETE
	@Path("/{id}")
	@UnitOfWork
	public Response deleteDepartment(@PathParam("id") Long id) {
		
		Department department = departmentDAO.get(id);
        if (department == null) { 
            return Response.status(Status.NOT_FOUND).entity(new ErrorMessage("Department not found")).build();
        }
		
		return Response.ok(departmentDAO.delete(department)).build();	
	}

}
