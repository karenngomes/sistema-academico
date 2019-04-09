package com.karenngomes.sistema.db;

import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.karenngomes.sistema.model.Professor;

import io.dropwizard.hibernate.AbstractDAO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProfessorDAO extends AbstractDAO<Professor> {

	public ProfessorDAO(SessionFactory sessionFactory) {
		super(sessionFactory);
	}
	
	@Override
    public Professor get(Serializable id) throws HibernateException {
        log.info("getting professor: id={}", id);
        return super.get(id);
    }

	public List<Professor> findAll() throws HibernateException {
		log.info("getting professors");
		return super.list(query("from Professor"));
	}
	
	@Override
    public Professor persist(Professor entity) throws HibernateException {
        return super.persist(entity);
    }
	
	public void update(Professor p) {
		Session session = super.currentSession();
		try {
			p = session.find(Professor.class, p.getId());
			session.merge(p);
			session.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			session.getTransaction().rollback();
		}
	}
	
	public Professor delete(Professor p) {
		Session session = super.currentSession();
		try {
			p = session.find(Professor.class, p.getId());
			session.remove(p);
			session.getTransaction().commit();
			return p;
		} catch (Exception ex) {
			ex.printStackTrace();
			session.getTransaction().rollback();
			return null;
		}

	}

}