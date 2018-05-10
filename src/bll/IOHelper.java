package bll;

import java.util.ArrayList;

import dal.CSVIO;
import dal.DatabaseIO;
import dal.PropertyManager;

public class IOHelper {

	private static IOHelper instance;
	private CSVIO csvHandler = new CSVIO();
	private DatabaseIO databaseHandler = new DatabaseIO();
	private PropertyManager propertyManager;

	public static IOHelper getInstance() {
		if (IOHelper.instance == null) {
			instance = new IOHelper();
		}
		return instance;
	}

	private IOHelper() {
	}

	public void readConfigFile() {
		propertyManager = PropertyManager.getInstance();
		if (propertyManager.configFileExists()) {
			databaseHandler.setConnectionStringLocal(propertyManager.readProperty("connectionStringLocal"));
			databaseHandler.setConnectionStringPublic(propertyManager.readProperty("connectionStringPublic"));
		}else{
			System.err.println("Die Config file wurde noch nicht erstellt!");
		}
	}

	public void writeCSV(ArrayList<Task> t) {
		csvHandler.writeTasks(t);
	}

	public void writeDatabase(ArrayList<Task> t) {
		databaseHandler.writeTasks(t);
	}

	public ArrayList<Task> readCSV() {
		return csvHandler.readTasks();
	}

	public ArrayList<Task> readDatabase() {
		return databaseHandler.readTasks();
	}

}
