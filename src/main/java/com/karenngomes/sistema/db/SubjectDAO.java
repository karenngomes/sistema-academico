package com.karenngomes.sistema.db;

import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.karenngomes.sistema.model.Department;
import com.karenngomes.sistema.model.Subject;

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
    
    public List<Subject> findAll() throws HibernateException {
        log.info("getting subjects");
        return super.list(query("from Subject"));
    }
    
    @Override
    public Subject persist(Subject entity) throws HibernateException {
        return super.persist(entity);
    }

    public void update(Subject s) {
    	Session session = super.currentSession();
		try {
			session.getTransaction().begin();
			s = session.find(Subject.class, s.getId());
			session.remove(s);
			session.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			session.getTransaction().rollback();
		}
	}

	public Subject delete(Subject s) {
		Session session = super.currentSession();
		try {
			session.getTransaction().begin();
			s = session.find(Subject.class, s.getId());
			session.remove(s);
			session.getTransaction().commit();
			return s;
		} catch (Exception ex) {
			ex.printStackTrace();
			session.getTransaction().rollback();
			return null;
		}
	}
    
}