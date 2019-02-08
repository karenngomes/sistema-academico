package com.karenngomes.sistema.db;

import io.dropwizard.hibernate.AbstractDAO;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;

import java.io.Serializable;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class GenericDAO<T> extends AbstractDAO<T> {

	public GenericDAO(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@Override
	public T get(Serializable id) {
		return super.get(id);
	}

	@Override
	public T persist(T entity) {
		return super.persist(entity);
	}
	
	public List<T> list() throws HibernateException { // findAll
		log.info("getting list from" + getClass());
		return super.list(query("from " + getClass()));
	}
	
}