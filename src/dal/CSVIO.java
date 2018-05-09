package dal;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import bll.DateHelper;
import bll.Task;
import bll.Task.FAECHER;
import bll.Task.STATUS;

public class CSVIO {

	public CSVIO() {
	}

	public ArrayList<Task> readTasks() {
		File file = chooseFile();
		BufferedReader inputStream = null;
		String l;
		String[] splittedLine;
		ArrayList<Task> newTasks = new ArrayList<Task>();
		Task tempTask;
		try {
			inputStream = new BufferedReader(new FileReader(file.getAbsolutePath()));
			while ((l = inputStream.readLine()) != null) {
				splittedLine = l.split(";");
				tempTask = new Task(FAECHER.valueOf(splittedLine[0]), splittedLine[1],
						DateHelper.dateformatParse(splittedLine[2]), STATUS.valueOf(splittedLine[3]));
				newTasks.add(tempTask);
			}
		} catch (IOException e) {
			System.err.println("Fehler beim Laden der Datei!");
		} catch (NullPointerException e) {
			System.err.println("Keine Datei wurde ausgewaehlt!");
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return newTasks;
	}

	public void writeTasks(ArrayList<Task> tasks) {
		File file = chooseFile();
		BufferedWriter outputStream = null;
		try {
			outputStream = new BufferedWriter(new FileWriter(file.getAbsolutePath()));
			for (Task t : tasks) {
				outputStream.write(t.getFach().toString() + ";" + t.getAufgabe() + ";"
						+ DateHelper.dateFormat(t.getBisDatum()) + ";" + t.getStatus().toString() + "\n");
			}
		} catch (IOException e) {
			System.err.println("Fehler beim Schreiben der Datei!");
		} finally {
			if (outputStream != null) {
				try {
					outputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private File chooseFile() {
		JFileChooser fc = new JFileChooser();
		fc.setDialogTitle("W�hle eine Datei aus");
		FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV", "csv");
		fc.addChoosableFileFilter(filter);
		File selectedFile = null;
		if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			selectedFile = fc.getSelectedFile();
		}
		return selectedFile;
	}
}
