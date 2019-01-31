package com.iLABCareers.database.entities;

import java.lang.reflect.Field;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseEntity {

	@Id
	@GeneratedValue
	private long id;

	private String testType;
	
	private String assertion;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTestType() {
		return testType;
	}

	public void setTestType(String testType) {
		this.testType = testType;
	}
	
	public String getAssertion() {
		return assertion;
	}

	public void setAssertion(String assertion) {
		this.assertion = assertion;
	}

	public String toString()
	{
		Field[] fields = this.getClass().getDeclaredFields();
		Object value;
		String data = this.getClass().getSimpleName()+"{";
		for(Field field: fields)
		{
			field.setAccessible(true);
			if(field.getName().equals("id") ||field.getName().equals("serialVersionUID"))
			{
				continue;
			}

			try 
			{
				value = field.get(this);
				if(value!=null)
				{
					data = data + field.getName()+"="+(String)value+",";
				}
			} 
			catch (IllegalArgumentException | IllegalAccessException e)
			{
				e.printStackTrace();
			}
		}
		return data+"}";
	}
}
