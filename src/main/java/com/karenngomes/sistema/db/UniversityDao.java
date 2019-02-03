package com.karenngomes.sistema.db;

import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;

import com.karenngomes.sistema.model.University;

import io.dropwizard.hibernate.AbstractDAO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UniversityDao extends AbstractDAO<University> {

	public UniversityDao(SessionFactory sessionFactory) {
		super(sessionFactory);
	}
	
	@Override
    public University get(Serializable id) throws HibernateException {
        log.info("getting person: id={}", id);
        return super.get(id);
    }
    
    public List<University> list() throws HibernateException {
        log.info("getting persons");
        return super.list(query("from Person"));
    }
    
    @Override
    public University persist(University entity) throws HibernateException {
        return super.persist(entity);
    }
    
    public List<Object> byUniverty(University u) {
    	return null;
    }
}
