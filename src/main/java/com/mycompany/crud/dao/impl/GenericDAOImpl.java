package com.mycompany.crud.dao.impl;

import com.mycompany.crud.dao.GenericDAO;
import com.mycompany.crud.util.HibernateUtil;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

public abstract class GenericDAOImpl<T, ID extends Serializable> implements GenericDAO<T, ID> {
    
    private final Class<T> entityClass;
    
    @SuppressWarnings("unchecked")
    public GenericDAOImpl() {
        this.entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
    
    @Override
    public T findById(ID id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        T entity = null;
        try {
            entity = session.get(entityClass, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return entity;
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public List<T> findAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<T> entities = null;
        try {
            entities = session.createQuery("FROM " + entityClass.getName()).list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return entities;
    }
    
    @Override
    public void save(T entity) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(entity);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    
    @Override
    public void update(T entity) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(entity);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    
    @Override
    public void delete(T entity) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(entity);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    
    @Override
    public void deleteById(ID id) {
        T entity = findById(id);
        if (entity != null) {
            delete(entity);
        }
    }
}