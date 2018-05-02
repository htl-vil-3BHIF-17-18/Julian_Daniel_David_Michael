package gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import bll.Task;
import bll.Task.FAECHER;

public class DialogTaskEdit extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private Task task;
	private TaskList tasklist;
	private JLabel labelFach;
	private JLabel labelAufgabe;
	private JLabel labelDate;
	private JComboBox<String> comboFach;
	private JTextField textfAufgabe;
	private JTextField textfDate;
	private JButton btnSubmit;
	
	public DialogTaskEdit(Task task, TaskList taskList) {
		this.task = task;
		this.tasklist = taskList;
		initializeControls();
	}
	
	private void initializeControls() {
		this.setLayout(new GridLayout(0, 2));
		this.labelFach = new JLabel("Fach: ");
		this.labelAufgabe = new JLabel("Aufgabe: ");
		this.labelDate = new JLabel("Datum: ");
		String[] items = new String[Task.FAECHER.values().length];
		for (int i = 0; i < Task.FAECHER.values().length; i++) {
			items[i] = Task.FAECHER.values()[i].toString();
		}
		this.comboFach = new JComboBox<>(items);
		this.textfAufgabe = new JTextField();
		this.textfDate = new JTextField();
		this.btnSubmit.addActionListener(this);
		
		this.add(labelFach);
		this.add(comboFach);
		this.add(labelAufgabe);
		this.add(textfAufgabe);
		this.add(labelDate);
		this.add(textfDate);
		this.add(new JLabel());
		this.add(btnSubmit);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnSubmit) {
			task.setAufgabe(textfAufgabe.getText());
			task.setFach((FAECHER) comboFach.getSelectedItem());
			SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
			try {
				task.setBisDatum(df.parse(textfDate.getText()));
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			tasklist.updateList();
			this.dispose();
		}
	}
	
}
