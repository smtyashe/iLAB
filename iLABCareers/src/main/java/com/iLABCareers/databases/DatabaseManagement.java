package com.iLABCareers.databases;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.iLABCareers.database.entities.BaseEntity;

/**
 * Database management responsible for all the CRUD actions.
 * 
 * 
 *
 */
public class DatabaseManagement {

	private PersistenceManager persistenceManager;

	public DatabaseManagement() {

	}

	public PersistenceManager getPersistenceManager() {
		if (persistenceManager == null) {
			persistenceManager = new PersistenceManager();
		}
		return persistenceManager;
	}

	/**
	 * Close the persistent manager.
	 * 
	 */
	public void closePersistenceManager() {
		if (persistenceManager != null) {
			persistenceManager.close();
		}
	}

	/**
	 * Persist the entity to the database.
	 * 
	 * @param entity
	 */
	public void persist(BaseEntity entity) {
		EntityManager em = getPersistenceManager().getEntityManager();
		em.getTransaction().begin();
		em.persist(entity);
		em.getTransaction().commit();
		em.close();
	}

	/**
	 * Retrieve data from the database.
	 * 
	 * @param query
	 * @return
	 */
	public Object retrieve(String query) {
		EntityManager em = getPersistenceManager().getEntityManager();
		List<?> entity = em.createQuery(query).getResultList();
		if (entity != null) {
			return entity.get(0);
		}
		return null;
	}

	/**
	 * Clear all data in the specified table.
	 * 
	 * @param entity
	 * @return
	 */
	public int clearAllEntityData(BaseEntity entity) {
		EntityManager em = getPersistenceManager().getEntityManager();
		em.getTransaction().begin();
		int affectedRows = em.createQuery("DELETE FROM " + entity.getClass().getSimpleName()).executeUpdate();
		em.getTransaction().commit();
		em.close();
		return affectedRows;
	}

	/**
	 * Persist data into all the database tables.
	 * 
	 * @param entities
	 */
	public void persistEntityCollection(List<? extends BaseEntity> entities) {
		EntityManager em = getPersistenceManager().getEntityManager();
		em.getTransaction().begin();
		for (BaseEntity entity : entities) {
			em.persist(entity);
		}
		em.getTransaction().commit();
		em.close();
	}

	/**
	 * 
	 * @param query
	 * @return
	 */
	public int insertUpdateData(String query) {
		EntityManager em = getPersistenceManager().getEntityManager();
		int affectedRows = em.createQuery(query).executeUpdate();
		return affectedRows;
	}

	/**
	 * Retrieve all data from the specified entity
	 * 
	 * 
	 * @param entity
	 * @return
	 */
	public List<? extends BaseEntity> getAllData (Class<?> entityClassname) {
		return getAllData(entityClassname, null,null);
	}
	
	@SuppressWarnings("unchecked")
	public List<? extends BaseEntity> getAllData (Class<?> entityClassname,String field,String value) {
		String whereCond = "";
		if(field!=null)
		{
			whereCond = " where "+field+"='"+value+"'";
		}
		EntityManager em = getPersistenceManager().getEntityManager();
		Query query = em.createQuery("From "+entityClassname.getSimpleName()+" "+whereCond);
		return (List<? extends BaseEntity>) query.getResultList();
	}

	/**
	 * Get positive test data from a specified table.
	 * 
	 * @param entity
	 * @return
	 */
	public Object getPositiveTestData(BaseEntity entity) {

		return retrieve("Select u from " + entity.getClass().getSimpleName() + " u where u.testType='positive'");
	}
	
	/**
	 * Get positive test data from a specified table.
	 * 
	 * @param entity
	 * @return
	 */
	public Object getNegativeTestData(BaseEntity entity) {

		return retrieve("Select u from " + entity.getClass().getSimpleName() + " u where u.testType='negative'");
	}
	
}

