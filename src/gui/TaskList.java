package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JScrollPane;

import bll.Task;
import bll.Task.STATUS;

public class TaskList extends JList<Task> implements MouseListener {

	private static final long serialVersionUID = 1L;
	private JScrollPane scrollPane;
	private DefaultListModel<Task> model;
	private JList<Task> taskList;
	private MainFrame mainf;
	private Task lastSelectedTask;

	public TaskList(ArrayList<Task> tasks, MainFrame mainf) {
		initializeControls();
		this.mainf = mainf;
		this.setPreferredSize(new Dimension(350, 500));
		this.addMouseListener(this);
		this.setTasks(tasks);
	}

	private void initializeControls() {
		model = new DefaultListModel<Task>();
		taskList = new JList<Task>(model);
		scrollPane = new JScrollPane(taskList);

		taskList.setFont(this.getFont());
		taskList.setBackground(Color.white);
		taskList.addMouseListener(this);

		this.add(scrollPane);
	}

	public ArrayList<Task> getAllNotFinishedTasks() {
		ArrayList<Task> notFinished = new ArrayList<Task>();
		for(Task t : getArrayList()) {
			if(t.getStatus() == STATUS.NICHTGESCHAFT || t.getStatus() == STATUS.VERGESSEN)
				notFinished.add(t);
		}
		return notFinished;
	}
	
	public void removeTask(Task task) {
		model.removeElement(task);
	}

	public void addTask(Task newTask) {
		model.addElement(newTask);
	}

	public void setTasks(List<Task> tasks) {
		model.removeAllElements();
		for (Task m : tasks) {
			model.addElement(m);
		}
		this.setModel(model);
	}

	public Task getSelectedTask() {
		return lastSelectedTask;
	}

	public void mouseClicked(MouseEvent e) {
		if(mainf != null)
			mainf.listClicked();
		lastSelectedTask = (taskList.getSelectedValue() != null) ? taskList.getSelectedValue() : lastSelectedTask;
	}

	public void mousePressed(MouseEvent e) {
		if(mainf != null)
			mainf.listClicked();
		lastSelectedTask = (taskList.getSelectedValue() != null) ? taskList.getSelectedValue() : lastSelectedTask;
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public ArrayList<Task> getArrayList() {
		ArrayList<Task> rgw = new ArrayList<Task>();;
		Object[] tasks = model.toArray();
		for(Object t : tasks) {
			rgw.add((Task) t);
		}
		return rgw;
		//return new ArrayList<Task>(Arrays.asList((Task[]) model.toArray()));
	}

}