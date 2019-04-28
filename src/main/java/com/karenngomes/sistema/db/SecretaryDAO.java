package com.karenngomes.sistema.db;

import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.karenngomes.sistema.model.Secretary;
import com.karenngomes.sistema.model.Subject;

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
	
	public List<Secretary> findAll() throws HibernateException {
		log.info("getting secretaries");
		return super.list(query("from Secretary"));
	}

	@Override
	public Secretary persist(Secretary entity) throws HibernateException {
		return super.persist(entity);
	}

	public void update(Secretary s) {
		Session session = super.currentSession();
		try {
			session.getTransaction().begin();
			s = session.find(Secretary.class, s.getId());
			session.merge(s);
			session.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			session.getTransaction().rollback();
		}
	}

	public Secretary delete(Secretary s) {
		Session session = super.currentSession();
		try {
			session.getTransaction().begin();
			s = session.find(Secretary.class, s.getId());
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