package com.karenngomes.sistema.db;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;

import com.karenngomes.sistema.model.Professor;
import com.karenngomes.sistema.model.Subject;

import io.dropwizard.hibernate.AbstractDAO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProfessorDAO extends AbstractDAO<Professor> {

	public ProfessorDAO(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@Override
	public Professor get(Serializable id) throws HibernateException {
		log.info("getting person: id={}", id);
		return super.get(id);
	}
	
	public Professor getById(Long id) {
		return currentSession().find(Professor.class, id);
	}

	public ArrayList<Professor> findAll() throws HibernateException {
		log.info("getting professors");
		return (ArrayList<Professor>) super.list(query("from Professor"));
	}

	@Override
	public Professor persist(Professor entity) throws HibernateException {
		return super.persist(entity);
	}

	public void update(Professor p) {
		try {
			currentSession().getTransaction().begin();
			p = currentSession().find(Professor.class, p.getId());
			currentSession().merge(p);
			currentSession().getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			currentSession().getTransaction().rollback();
		}
	}

	public Professor delete(Professor p) {
		try {
			currentSession().getTransaction().begin();
			p = currentSession().find(Professor.class, p.getId());
			currentSession().remove(p);
			currentSession().getTransaction().commit();
			return p;
		} catch (Exception ex) {
			ex.printStackTrace();
			currentSession().getTransaction().rollback();
			return null;
		}

	}
}