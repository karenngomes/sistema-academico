package com.karenngomes.sistema.db;

import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.karenngomes.sistema.model.Course;
import com.karenngomes.sistema.model.Department;
import com.karenngomes.sistema.model.Professor;
import com.karenngomes.sistema.model.Secretary;

import io.dropwizard.hibernate.AbstractDAO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CourseDAO extends AbstractDAO<Course> {

	public CourseDAO(SessionFactory sessionFactory) {
		super(sessionFactory);
	}
	
	@Override
    public Course get(Serializable id) throws HibernateException {
        log.info("getting course: id={}", id);
        return super.get(id);
    }
    
    public List<Course> findAll() throws HibernateException {
        log.info("getting courses");
        return super.list(query("from Course"));
    }
    
    @Override
    public Course persist(Course entity) throws HibernateException {
        return super.persist(entity);
    }
    
    public void update(Course c) {
    	Session session = super.currentSession();
		try {
			c = session.find(Course.class, c.getId());
			session.merge(c);
			session.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			session.getTransaction().rollback();
		}
	}

	public Course delete(Course c) {
		Session session = super.currentSession();
		try {
			c = session.find(Course.class, c.getId());
			session.remove(c);
			session.getTransaction().commit();
			return c;
		} catch (Exception ex) {
			ex.printStackTrace();
			session.getTransaction().rollback();
			return null;
		}

	}
    
}