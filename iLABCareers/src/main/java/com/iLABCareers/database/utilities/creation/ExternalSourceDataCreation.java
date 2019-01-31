package com.iLABCareers.database.utilities.creation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.iLABCareers.database.entities.BaseEntity;
import com.iLABCareers.utilities.files.TextFilesUtil;

public class ExternalSourceDataCreation extends DataCreation {
	private String[] fields;
	public static String DELIMETER = "\t";

	Logger logger = Logger
			.getLogger(ExternalSourceDataCreation.class.getName());

	public ExternalSourceDataCreation() {
		super();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void saveData() {
		String dataPath = "data/";
		String entitiesPackage = "com.iLABCareers.database.entities.";
		List<String[]> fileContents;
		Class<? extends BaseEntity> entityClass;
		List<? extends BaseEntity> entityCollection;

		List<String> files = TextFilesUtil.getFiles(dataPath);
		for (int index = 0; index < files.size(); index++) {
			fileContents = readFileIntoArray(dataPath + files.get(index));
			try {
				if (!files.get(index).contains(".")
						|| files.get(index).contains("#")
						|| files.get(index).contains("~")) {
					continue;
				}
				String filename = files.get(index).substring(0,
						files.get(index).indexOf("."));
				System.out.println("processing file "+filename);
				entityClass = (Class<? extends BaseEntity>) Class
						.forName(entitiesPackage + filename);
				databaseManagement
						.clearAllEntityData(entityClass.newInstance());
				entityCollection = getEntityData(entityClass, fileContents);
				persitEntities(entityCollection);
			}

			catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (Exception ex) {
				ex.printStackTrace();
			}

		}
	}

	private List<String[]> readFileIntoArray(String fileName) {
		final List<String[]> fileContents = new ArrayList<>();
		BufferedReader bufferedReader = null;
		try {
			bufferedReader = new BufferedReader(new FileReader(fileName));
			String currentLine = bufferedReader.readLine();
			fields = currentLine.split(DELIMETER);
			currentLine = bufferedReader.readLine();
			String[] rowItems;
			while (currentLine != null) {
				rowItems = currentLine.split(DELIMETER);
				fileContents.add(rowItems);
				currentLine = bufferedReader.readLine();
			}
			bufferedReader.close();
		} catch (Exception ex) {
			logger.severe("Exception enountered : " + ex.getMessage());
			ex.printStackTrace();
		}
		return fileContents;
	}

	private List<? extends BaseEntity> getEntityData(
			final Class<? extends BaseEntity> entityClass,
			final List<String[]> fileContents) {
		final List<BaseEntity> entities = new ArrayList<BaseEntity>();
		BaseEntity baseEntity;
		for (int i = 0; i < fileContents.size(); i++) {
			baseEntity = getEntity(entityClass, fileContents.get(i));
			if (baseEntity != null) {
				entities.add(baseEntity);
			}
		}
		return entities;
	}

	private BaseEntity getEntity(final Class<? extends BaseEntity> entityClass,
			final String[] record) {
		try {
			final BaseEntity entity = entityClass.newInstance();
			String methodName;
			for (int i = 0; i < fields.length; i++) {
				methodName = "set" + Character.toUpperCase(fields[i].charAt(0))
						+ fields[i].substring(1, fields[i].length());
				Method method = entity.getClass().getMethod(methodName,
						String.class);
				method.invoke(entity, record[i]);
			}
			return entity;
		} catch (Exception e) {
			logger.severe("Exception enountered : " + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
}
