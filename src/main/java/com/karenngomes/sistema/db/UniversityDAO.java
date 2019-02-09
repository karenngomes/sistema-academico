package com.karenngomes.sistema.db;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;

import com.karenngomes.sistema.model.University;

import io.dropwizard.hibernate.AbstractDAO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UniversityDAO extends AbstractDAO<University> {

	public UniversityDAO(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@Override
	public University get(Serializable id) throws HibernateException {
		log.info("getting university: id={}", id);
		return super.get(id);
	}

	public University getById(Long id) {
		return currentSession().find(University.class, id);
	}

	public ArrayList<University> findAll() throws HibernateException {
		log.info("getting universities");
		return (ArrayList<University>) super.list(query("from University"));
	}

	@Override
	public University persist(University entity) throws HibernateException {
		return super.persist(entity);
	}

	public void update(University university) {
		try {
			currentSession().getTransaction().begin();
			university = currentSession().find(University.class, university.getId());
			System.out.println(university.getId());
			University u = (University) currentSession().merge(university);
			System.out.println(u.getId());
			currentSession().getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			currentSession().getTransaction().rollback();
		}
	}

	public University delete(University university) {
		try {
			currentSession().getTransaction().begin();
			university = currentSession().find(University.class, university.getId());
			currentSession().remove(university);
			currentSession().getTransaction().commit();
			return university;
		} catch (Exception ex) {
			ex.printStackTrace();
			currentSession().getTransaction().rollback();
			return null;
		}

	}

}
