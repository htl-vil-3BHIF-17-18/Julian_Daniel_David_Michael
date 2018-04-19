package dal;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import bll.Task;

public class CSVIO {
	
	public CSVIO() {}
	
	public void readTasks() {
		File file = chooseFile();
	}
	
	public void writeTasks(ArrayList<Task> tasks) {
		File file = chooseFile();
		BufferedWriter outputStream = null;
		try {
			outputStream = new BufferedWriter(new FileWriter(file.getAbsolutePath()));
			for(Task t : tasks) {
				outputStream.write(t.getFach().toString() + ";" + t.getAufgabe() + ";" + t.getBisDatum() + "\n");
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
		fc.setDialogTitle("Please choose an image...");
		FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV", "csv");
		fc.addChoosableFileFilter(filter);
		File selectedFile = null;
		if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			selectedFile = fc.getSelectedFile();
		}
		return selectedFile;
	}
}
