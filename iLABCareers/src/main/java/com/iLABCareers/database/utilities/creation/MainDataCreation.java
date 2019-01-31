package com.iLABCareers.database.utilities.creation;

public class MainDataCreation {

	public static void main(String[] args) {
		DataCreation dataCreation;

		dataCreation = new ExternalSourceDataCreation();
		dataCreation.saveData();
		dataCreation.getDatabaseManagement().closePersistenceManager();
	}

	public static class ConstantArguments {
		public static String _1_CLEAN_DATABASE = "1";
		public static String _1_LOAD_GENERATED_DATA = "1";
		public static String _1_LOAD_EXTERNAL_DATA = "1";

	}
}
