package com.iLABCareers.database.utilities.retrieval;

import java.util.List;

import com.iLABCareers.databases.DatabaseManagement;
import com.iLABCareers.database.entities.BaseEntity;

public class DataRetrieval {

	private DatabaseManagement dbManager;
	private static DataRetrieval INSTANCE;
	
	private DataRetrieval()
	{
		dbManager = new DatabaseManagement();
	}
	
	public static DataRetrieval getInstance()
	{
		if(INSTANCE == null)
		{
			INSTANCE = new DataRetrieval();
		}
		return INSTANCE;
	}
	
	/**
	 * Retrieve all data.
	 * 
	 * @return
	 * A list containing entities with both negative and positive data.
	 */
	public List<? extends BaseEntity> getAllData(Class entityClassname) {

		return  dbManager.getAllData(entityClassname);
	}
	
	/**
	 * Retrieve all positive data.
	 * 
	 * @return
	 *  A list containing entities with negative data.
	 */
	public List<? extends BaseEntity> getAllPositiveData(Class entityClassname) {

		return  dbManager.getAllData(entityClassname, "testType","positive");
	}
	
	/**
	 * Retrieve all positive data.
	 * 
	 * @return
	 *  A list containing entities with negative data.
	 */
	public List<? extends BaseEntity> getAllNegativeData(Class entity) {

		return  dbManager.getAllData(entity, "testType","negative");
	}

	/**
	 * Get data for test type - positive
	 * 
	 * @return
	 */
	public BaseEntity getPositiveData(BaseEntity entity) {

		return (BaseEntity) dbManager.getPositiveTestData(entity);
	}
	
	/**
	 * Get data for test type - positive
	 * 
	 * @return
	 */
	public BaseEntity getNegativeData(BaseEntity entity) {

		return (BaseEntity) dbManager.getNegativeTestData(entity);
	}
	
	
	/**
	 * Retrieve all data.
	 * 
	 * @return
	 *Gets list containing entities based on the given criteria
	 */
	public List<? extends BaseEntity> getAllData (Class entity,String field,String value) {

		return  dbManager.getAllData(entity, field, value);
	}
	
}
