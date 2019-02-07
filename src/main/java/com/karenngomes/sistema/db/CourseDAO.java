package com.karenngomes.sistema.db;

import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;

import com.karenngomes.sistema.model.Course;

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
    
    public List<Course> list() throws HibernateException {
        log.info("getting courses");
        return super.list(query("from Course"));
    }
    
    @Override
    public Course persist(Course entity) throws HibernateException {
        return super.persist(entity);
    }
    
    public List<Object> byUniverty(Course s) {
    	return null;
    }
}