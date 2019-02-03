package com.karenngomes.sistema.db;

import java.io.Serializable;
import java.util.List;

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
    
    public List<Secretary> list() throws HibernateException {
        log.info("getting secretaries");
        return super.list(query("from Secretary"));
    }
    
    @Override
    public Secretary persist(Secretary entity) throws HibernateException {
        return super.persist(entity);
    }
    
    public List<Object> byUniverty(Secretary s) {
    	return null;
    }
}