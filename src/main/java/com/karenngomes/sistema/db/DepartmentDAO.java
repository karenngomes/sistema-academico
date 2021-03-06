package com.karenngomes.sistema.db;

import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
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
    
    public List<Department> findAll() throws HibernateException {
        log.info("getting departments");
        return super.list(query("from Department"));
    }
    
    @Override
    public Department persist(Department entity) throws HibernateException {
        return super.persist(entity);
    }

	public void update(Department d) {
		Session session = super.currentSession();
		try {
			session.getTransaction().begin();
			d = session.find(Department.class, d.getId());
			session.merge(d);
			session.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			session.getTransaction().rollback();
		}
	}

	public Department delete(Department d) {
		Session session = super.currentSession();
		try {
			session.getTransaction().begin();
			d = session.find(Department.class, d.getId());
			session.remove(d);
			session.getTransaction().commit();
			return d;
		} catch (Exception ex) {
			ex.printStackTrace();
			session.getTransaction().rollback();
			return null;
		}

	}
    
}
