package com.karenngomes.sistema.db;

import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;

import com.karenngomes.sistema.model.Course;
import com.karenngomes.sistema.model.Subject;
import com.karenngomes.sistema.model.University;

import io.dropwizard.hibernate.AbstractDAO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SubjectDAO extends AbstractDAO<Subject> {

	public SubjectDAO(SessionFactory sessionFactory) {
		super(sessionFactory);
	}
	
	@Override
    public Subject get(Serializable id) throws HibernateException {
        log.info("getting person: id={}", id);
        return super.get(id);
    }
	
	public Subject getById(Long id) {
		return currentSession().find(Subject.class, id);
	}
    
    public List<Subject> findAll() throws HibernateException {
        log.info("getting subjects");
        return super.list(query("from Subject"));
    }
    
    @Override
    public Subject persist(Subject entity) throws HibernateException {
        return super.persist(entity);
    }

    public void update(Subject s) {
		try {
			currentSession().getTransaction().begin();
			s = currentSession().find(Subject.class, s.getId());
			currentSession().merge(s);
			currentSession().getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			currentSession().getTransaction().rollback();
		}
	}

	public Subject delete(Subject s) {
		try {
			currentSession().getTransaction().begin();
			s = currentSession().find(Subject.class, s.getId());
			currentSession().remove(s);
			currentSession().getTransaction().commit();
			return s;
		} catch (Exception ex) {
			ex.printStackTrace();
			currentSession().getTransaction().rollback();
			return null;
		}

	}
    
}