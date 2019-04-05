package com.karenngomes.sistema.db;

import java.io.Serializable;
import java.util.List;

import com.karenngomes.sistema.model.Student;
import com.karenngomes.sistema.model.Student;
import com.karenngomes.sistema.model.Student;

import io.dropwizard.hibernate.AbstractDAO;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StudentDAO extends AbstractDAO<Student> {

    public StudentDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
    
    @Override
    public Student get(Serializable id) throws HibernateException {
        log.info("getting student: id={}", id);
        return super.get(id);
    }

    public List<Student> findAll() throws HibernateException {
        log.info("getting students");
        return super.list(query("from Student"));
    }
    
    @Override
	public Student persist(Student entity) throws HibernateException {
		return super.persist(entity);
	}
    
    public void update(Student s) {
		try {
			currentSession().getTransaction().begin();
			s = currentSession().find(Student.class, s.getId());
			currentSession().merge(s);
			currentSession().getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			currentSession().getTransaction().rollback();
		}
	}
    
    public Student delete(Student s) {
		try {
			currentSession().getTransaction().begin();
			s = currentSession().find(Student.class, s.getId());
			currentSession().remove(s);
			currentSession().getTransaction().commit();
			return s;
		} catch (Exception ex) {
			ex.printStackTrace();
			currentSession().getTransaction().rollback();
			return null;
		}

	}

}