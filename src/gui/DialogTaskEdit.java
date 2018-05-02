package gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
		fillControls();
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	private void fillControls() {
		this.comboFach.setSelectedItem(task.getFach().toString());
		this.textfAufgabe.setText(task.getAufgabe());
		this.textfDate.setText(MainFrame.dateFormat(task.getBisDatum()));
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
		this.btnSubmit = new JButton("Aendern");
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
		if (e.getSource() == btnSubmit) {
			task.setAufgabe(textfAufgabe.getText());
			task.setFach(FAECHER.valueOf(comboFach.getSelectedItem().toString()));
			task.setBisDatum(MainFrame.dateformatParse(textfDate.getText()));
			tasklist.updateList();
			this.dispose();
		}
	}

}
