package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;

import bll.Task;
import bll.Task.STATUS;

public class TaskList extends JPanel implements MouseListener {

	private static final long serialVersionUID = 1L;
	private DefaultListModel<Task> model;
	private JList<Task> taskList;
	private MainFrame mainf;

	public TaskList(ArrayList<Task> tasks, MainFrame mainf) {
		initializeControls();
		this.mainf = mainf;
		this.setPreferredSize(new Dimension(350, 500));
		this.addMouseListener(this);
		this.setTasks(tasks);
	}

	private void initializeControls() {
		model = new DefaultListModel<Task>();
		this.setLayout(new GridLayout(1, 1));
		taskList = new JList<Task>(model);
		taskList.setModel(model);
		taskList.setFont(this.getFont());
		taskList.setBackground(Color.white);
		taskList.addMouseListener(this);
		this.add(taskList);
		this.setVisible(true);
	}

	public void updateList() {
		taskList.updateUI();
	}
	
	public ArrayList<Task> getAllNotFinishedTasks() {
		ArrayList<Task> notFinished = new ArrayList<Task>();
		for (Task t : getArrayList()) {
			if (t.getStatus() == STATUS.NICHTGESCHAFT || t.getStatus() == STATUS.VERGESSEN)
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
		for (Task t : tasks) {
			model.addElement(t);
		}
	}

	public Task getSelectedTask() {
		return taskList.getSelectedValue();
	}

	public void mouseClicked(MouseEvent e) {
		if (mainf != null && getSelectedTask() != null)
			mainf.enableButtons();
	}

	public void mousePressed(MouseEvent e) {
		if (mainf != null && getSelectedTask() != null)
			mainf.enableButtons();
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}
	
	public ArrayList<Task> getArrayList() {
		ArrayList<Task> rgw = new ArrayList<Task>();
		;
		Object[] tasks = model.toArray();
		for (Object t : tasks) {
			rgw.add((Task) t);
		}
		return rgw;
	}

}