package br.com.db.dao.impl;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import br.com.db.dao.GenericDAO;
import br.com.db.exceptions.DAOException;



public abstract class HibernateGenericDAO<E>
extends HibernateDaoSupport implements GenericDAO<E>{

	@Autowired
	public void setFactory(SessionFactory sessionFactory){
		setSessionFactory(sessionFactory);
	}
	
	protected abstract Class<E> getEntityClass();
	
	protected DetachedCriteria createDetachedCriteria(){
		return DetachedCriteria.forClass(getEntityClass());
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public E save(E entity) {
		try {
			return (E) getHibernateTemplate().save(entity);
			
		} catch (DataAccessException e) {
			throw new DAOException(e.getMessage(), e);
		}
	}
	
	public boolean removerRegistro(E entity){
		try {
			getHibernateTemplate().delete(entity);
			return true;
		} catch (DataAccessException e) {
			throw new DAOException(e.getMessage(), e);

		}
		
	}
	
	@Override
	public void saveOrUpdate(E entity) {
	    this.getHibernateTemplate().saveOrUpdate(entity);	
	}
	

	@SuppressWarnings("unchecked")
	@Override
	public List<E> buscarRegistros(String property, Object value) {
		DetachedCriteria criteria = createDetachedCriteria();
		criteria.add(Restrictions.eq(property, value));	
		try {
			return getHibernateTemplate().findByCriteria(criteria);
			
		} catch (DataAccessException e) {
			throw new DAOException(e.getMessage(), e);

		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<E> buscarRegistros() {
	    DetachedCriteria criteria = createDetachedCriteria();
		return getHibernateTemplate().findByCriteria(criteria);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public E buscarRegistro(String property, Object value) {
		try {
			DetachedCriteria criteria = createDetachedCriteria();
			criteria.add(Restrictions.eq(property, value));
			List<E>list = getHibernateTemplate().findByCriteria(criteria);
			if(list.size() > 0){
				return (E) getHibernateTemplate().findByCriteria(criteria).get(0);
			}
		} catch (DataAccessException e) {
			throw new DAOException(e.getMessage(),e);

		}
		
		return null;
		
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<E> buscarRegistrosList(String propriedade, List<Integer> valores) {
		try {
			DetachedCriteria criteria = createDetachedCriteria();
			criteria.add(Restrictions.in(propriedade, valores));
			return getHibernateTemplate().findByCriteria(criteria);
		} catch (Exception e) {
			throw new DAOException(e.getMessage(), e);
		}
		
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<E> buscarRegistrosListP(String propriedade, List<String> valores) {
		try {
			DetachedCriteria criteria = createDetachedCriteria();
			criteria.add(Restrictions.in(propriedade, valores));
			return getHibernateTemplate().findByCriteria(criteria);
		} catch (Exception e) {
			throw new DAOException(e.getMessage(), e);
		}
		
	}
		
	
}
