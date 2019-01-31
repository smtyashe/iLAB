package com.iLABCareers.database.utilities.creation;

import java.util.ArrayList;
import java.util.List;

import com.iLABCareers.databases.DatabaseManagement;
import com.iLABCareers.database.entities.BaseEntity;
import com.iLABCareers.database.entities.ApplicantEntity;;

public abstract class DataCreation {
	
	protected BaseEntity  [] entities = {new ApplicantEntity()};
	protected DatabaseManagement databaseManagement;
	
	public DataCreation()
	{
		databaseManagement = new DatabaseManagement();
	}
	
	public DatabaseManagement getDatabaseManagement() {
		return databaseManagement;
	}

	public void clearData()
	{
		for(int index = 0; index < entities.length;index++)
		{
			databaseManagement.clearAllEntityData(entities[index]);
		}
	}
	
	public void saveData()
	{
		for(int index = 0; index < entities.length;index++)
		{
			List<? extends BaseEntity> entityCollection  = getEntitiesFromSource(entities[index]);
			persitEntities(entityCollection);
		}
	}
	
	protected void persitEntities(List<? extends BaseEntity> entityCollection)
	{
		databaseManagement.persistEntityCollection(entityCollection);
	}
	
	protected List<? extends BaseEntity> getEntitiesFromSource(BaseEntity entity) {
		switch(entity.getClass().getSimpleName())
		{
			case "ApplicantEntity":
				return getEmployerEntityData();
				
			case "SearchEntity":
				return getSearchEntityData();
				
		}
		return new ArrayList<BaseEntity>();
	}
	
	protected List<? extends BaseEntity> getEntitiesFromSource(String source) {
		return null;
	}
	
	protected  List <? extends BaseEntity> getEmployerEntityData()
	{
		return null;
	}
	
	protected  List <? extends BaseEntity> getSearchEntityData()
	{
		return null;
	}

}
