package gui;

import java.util.ArrayList;

import javax.swing.JDialog;

import bll.Task;

public class DialogNotFinishedTasks extends JDialog {

	private static final long serialVersionUID = 1L;
	private ArrayList<Task> tasks;
	private TaskList list;

	public DialogNotFinishedTasks(ArrayList<Task> tasks) {
		this.tasks = tasks;
		initializeControls();
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}

	private void initializeControls() {
		list = new TaskList(tasks, null);
		this.add(list);
		this.pack();
	}

}
