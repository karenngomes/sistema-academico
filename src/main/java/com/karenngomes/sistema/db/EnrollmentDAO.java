package com.karenngomes.sistema.db;

import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;

import com.karenngomes.sistema.model.Enrollment;

import io.dropwizard.hibernate.AbstractDAO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EnrollmentDAO extends AbstractDAO<Enrollment> {
	
	public EnrollmentDAO(SessionFactory sessionFactory) {
		super(sessionFactory);
	}
	
	@Override
    public Enrollment get(Serializable id) throws HibernateException {
        log.info("getting enrollment: id={}", id);
        return super.get(id);
    }
	
	public Enrollment getById(Long id) {
		return currentSession().find(Enrollment.class, id);
	}
    
    public List<Enrollment> findAll() throws HibernateException {
        log.info("getting enrollment");
        return super.list(query("from Enrollment"));
    }
    
    @Override
    public Enrollment persist(Enrollment entity) throws HibernateException {
        return super.persist(entity);
    }

	public void update(Enrollment d) {
		try {
			currentSession().getTransaction().begin();
			d = currentSession().find(Enrollment.class, d.getId());
			currentSession().merge(d);
			currentSession().getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			currentSession().getTransaction().rollback();
		}
	}

	public Enrollment delete(Enrollment d) {
		try {
			currentSession().getTransaction().begin();
			d = currentSession().find(Enrollment.class, d.getId());
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
