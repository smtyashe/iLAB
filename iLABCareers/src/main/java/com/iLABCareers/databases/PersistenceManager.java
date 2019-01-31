package com.iLABCareers.databases;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Persistence manager.
 * 
 */
public class PersistenceManager {
	//INSTANCE;
	private EntityManagerFactory emFactory;

	public PersistenceManager() {
		
		emFactory = Persistence.createEntityManagerFactory("jpa-automation");
	}

	public EntityManager getEntityManager() {
		return emFactory.createEntityManager();
	}

	public void close() {
		emFactory.close();
	}
}
