package com.karenngomes.sistema.db;

import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;

import com.karenngomes.sistema.model.Course;
import com.karenngomes.sistema.model.Department;
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
	
	public Course getById(Long id) {
		return currentSession().find(Course.class, id);
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
		try {
			currentSession().getTransaction().begin();
			c = currentSession().find(Course.class, c.getId());
			currentSession().merge(c);
			currentSession().getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			currentSession().getTransaction().rollback();
		}
	}

	public Course delete(Course c) {
		try {
			currentSession().getTransaction().begin();
			c = currentSession().find(Course.class, c.getId());
			currentSession().remove(c);
			currentSession().getTransaction().commit();
			return c;
		} catch (Exception ex) {
			ex.printStackTrace();
			currentSession().getTransaction().rollback();
			return null;
		}

	}
    
}