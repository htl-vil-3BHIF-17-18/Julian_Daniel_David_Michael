package bll;

import java.util.ArrayList;

import dal.CSVIO;
import dal.DatabaseIO;

public class IOHelper {

	private CSVIO csvHandler;
	private DatabaseIO databaseHandler;
	
	public IOHelper () {
		csvHandler = new CSVIO();
		databaseHandler = new DatabaseIO();
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
