package com.karenngomes.sistema.db;

import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;

import com.karenngomes.sistema.model.Professor;

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
    
    public List<Professor> list() throws HibernateException {
        log.info("getting secretaries");
        return super.list(query("from Secretary"));
    }
    
    @Override
    public Professor persist(Professor entity) throws HibernateException {
        return super.persist(entity);
    }
    
    public List<Object> byUniverty(Professor s) {
    	return null;
    }
}