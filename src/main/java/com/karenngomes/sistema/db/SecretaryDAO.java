package com.karenngomes.sistema.db;

import java.io.Serializable;
import java.util.ArrayList;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;

import com.karenngomes.sistema.model.Secretary;

import io.dropwizard.hibernate.AbstractDAO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SecretaryDAO extends AbstractDAO<Secretary> {

	public SecretaryDAO(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@Override
	public Secretary get(Serializable id) throws HibernateException {
		log.info("getting person: id={}", id);
		return super.get(id);
	}

	public Secretary getById(Long id) {
		return currentSession().find(Secretary.class, id);
	}
	
	public ArrayList<Secretary> findAll() throws HibernateException {
		log.info("getting secretaries");
		return (ArrayList<Secretary>) super.list(query("from Secretary"));
	}

	@Override
	public Secretary persist(Secretary entity) throws HibernateException {
		return super.persist(entity);
	}

	public void update(Secretary s) {
		try {
			currentSession().getTransaction().begin();
			s = currentSession().find(Secretary.class, s.getId());
			currentSession().merge(s);
			currentSession().getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			currentSession().getTransaction().rollback();
		}
	}

	public Secretary delete(Secretary s) {
		try {
			currentSession().getTransaction().begin();
			s = currentSession().find(Secretary.class, s.getId());
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