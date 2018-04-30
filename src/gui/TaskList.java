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

public class TaskList extends JList<Task> implements MouseListener {

	private static final long serialVersionUID = 1L;
	private JScrollPane scrollPane;
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
		taskList = new JList<Task>();
		taskList.setModel(model);
		scrollPane = new JScrollPane(taskList);

		taskList.setFont(this.getFont());
		taskList.setBackground(Color.white);
		taskList.addMouseListener(this);
		taskList.setModel(model);
		
		this.add(scrollPane);
	}

	public void setTasks(List<Task> tasks) {
		model.removeAllElements();
		for (Task m : tasks) {
			model.addElement(m);
		}
		this.setModel(model);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	public DefaultListModel<Task> getModel() {
		return model;
	}


}