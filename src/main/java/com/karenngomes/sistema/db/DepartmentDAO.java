package com.karenngomes.sistema.db;

import java.io.Serializable;
import java.util.ArrayList;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;

import com.karenngomes.sistema.model.Department;

import io.dropwizard.hibernate.AbstractDAO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DepartmentDAO extends AbstractDAO<Department> {

	public DepartmentDAO(SessionFactory sessionFactory) {
		super(sessionFactory);
	}
	
	@Override
    public Department get(Serializable id) throws HibernateException {
        log.info("getting person: id={}", id);
        return super.get(id);
    }
	
	public Department getById(Long id) {
		return currentSession().find(Department.class, id);
	}
    
    public ArrayList<Department> findAll() throws HibernateException {
        log.info("getting departments");
        return (ArrayList<Department>) super.list(query("from Department"));
    }
    
    @Override
    public Department persist(Department entity) throws HibernateException {
        return super.persist(entity);
    }

	public void update(Department d) {
		try {
			currentSession().getTransaction().begin();
			d = currentSession().find(Department.class, d.getId());
			currentSession().merge(d);
			currentSession().getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			currentSession().getTransaction().rollback();
		}
	}

	public Department delete(Department d) {
		try {
			currentSession().getTransaction().begin();
			d = currentSession().find(Department.class, d.getId());
			currentSession().remove(d);
			currentSession().getTransaction().commit();
			return d;
		} catch (Exception ex) {
			ex.printStackTrace();
			currentSession().getTransaction().rollback();
			return null;
		}

	}
    
}
