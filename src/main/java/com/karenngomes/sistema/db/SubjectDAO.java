package com.karenngomes.sistema.db;

import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;

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
    
    public List<Subject> list() throws HibernateException {
        log.info("getting secretaries");
        return super.list(query("from Secretary"));
    }
    
    @Override
    public Subject persist(Subject entity) throws HibernateException {
        return super.persist(entity);
    }
    
    public List<Object> byUniverty(Subject s) {
    	return null;
    }
}