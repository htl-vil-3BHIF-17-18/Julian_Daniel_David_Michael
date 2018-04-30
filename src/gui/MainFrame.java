package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import bll.Task;
import bll.Task.FAECHER;
import bll.Task.STATUS;
import dal.CSVIO;
import dal.DatabaseIO;

public class MainFrame extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	private CSVIO csvHandler;
	private DatabaseIO databaseHandler;

	// Menu bar components
	private JMenu menuSave;
	private JMenuBar menuBar;
	private JMenuItem menuItemDBSave;
	private JMenuItem menuItemCSVSave;

	// Panelright
	private JPanel panelRight = null;
	private JPanel panelList = null;
	private JButton buttonHinzufuegen;
	private JButton buttonAndern;
	private JButton buttonEntfernen;
	private JComboBox<String> comboFach;
	private JTextField textfAufgabe;
	private JTextField textfDatum;
	private JLabel labelFach;
	private JLabel labelAufgabe;
	private JLabel labelDatum;

	// panel unten
	private JPanel panelBottom;
	private JPanel panelBottomLeft;
	private JRadioButton radioVergessen;
	private JRadioButton radioNichtGeschaft;
	private JRadioButton radioErledigt;
	private JButton buttonSetTaskStatus;
	private JButton buttonShowAllNotDoneTasks;

	// Die zwei Listenn
	private TaskList liste;

	public MainFrame() {
		this.setTitle("Hausaufgabenplaner");
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initializeControls();
		this.setVisible(true);
		this.setLocationRelativeTo(null); // Fenste mittig anzeigen
	}

	private void initializeControls() {
		databaseHandler = new DatabaseIO();
		csvHandler = new CSVIO();

		BorderLayout grid = new BorderLayout();
		this.setLayout(grid);

		menuBar = new JMenuBar();
		this.menuBar.setPreferredSize(new Dimension(40, 60));
		menuSave = new JMenu("Speicher");
		menuItemDBSave = new JMenuItem("Datenbank");
		menuItemDBSave.addActionListener(this);
		menuItemCSVSave = new JMenuItem("CSV");
		menuItemCSVSave.addActionListener(this);

		this.panelRight = new JPanel(new GridLayout(0, 2));
		this.panelRight.setPreferredSize(new Dimension(450, 40));
		this.panelList = new JPanel(new GridLayout(11, 1));
		this.panelList.setPreferredSize(new Dimension(40, 40));
		this.panelBottom = new JPanel(new GridLayout(1, 4));
		this.panelBottomLeft = new JPanel(new GridLayout(3, 1));
		this.panelBottomLeft.setPreferredSize(new Dimension(8, 75));

		this.buttonHinzufuegen = new JButton("Hinzuf�gen");
		this.buttonHinzufuegen.addActionListener(this);
		this.buttonAndern = new JButton("Aendern");
		this.buttonAndern.addActionListener(this);
		this.buttonEntfernen = new JButton("Entfernen");
		this.buttonEntfernen.addActionListener(this);

		String[] items = new String[Task.FAECHER.values().length];
		;
		for (int i = 0; i < Task.FAECHER.values().length; i++) {
			items[i] = Task.FAECHER.values()[i].toString();
		}
		this.comboFach = new JComboBox<>(items);
		this.textfAufgabe = new JTextField();
		this.textfDatum = new JTextField();

		this.labelFach = new JLabel("Fach: ");
		this.labelDatum = new JLabel("Datum: ");
		this.labelAufgabe = new JLabel("Aufgabe: ");

		this.radioNichtGeschaft = new JRadioButton("nicht geschafft");
		this.radioNichtGeschaft.addActionListener(this);
		this.radioVergessen = new JRadioButton("vergessen");
		this.radioVergessen.addActionListener(this);
		this.radioErledigt = new JRadioButton("erledigt");
		this.radioErledigt.addActionListener(this);
		this.buttonSetTaskStatus = new JButton("Setze Task Status");
		this.buttonSetTaskStatus.addActionListener(this);
		this.buttonShowAllNotDoneTasks = new JButton("unerledigte Aufgaben");
		this.buttonShowAllNotDoneTasks.addActionListener(this);
		ButtonGroup groupRadios = new ButtonGroup();
		groupRadios.add(radioErledigt);
		groupRadios.add(radioNichtGeschaft);
		groupRadios.add(radioVergessen);

		this.liste = new TaskList(new ArrayList<Task>(), this);
		this.liste.setPreferredSize(new Dimension(540, 500));

		this.panelRight.add(new JLabel()); // platzhalter
		this.panelRight.add(new JLabel()); // platzhalter
		this.panelRight.add(labelFach);
		this.panelRight.add(comboFach);
		this.panelRight.add(labelAufgabe);
		this.panelRight.add(textfAufgabe);
		this.panelRight.add(labelDatum);
		this.panelRight.add(textfDatum);
		this.panelRight.add(new JLabel()); // platzhalter
		this.panelRight.add(buttonHinzufuegen);
		this.panelRight.add(new JLabel()); // platzhalter
		this.panelRight.add(new JLabel()); // platzhalter
		this.panelRight.add(buttonAndern);
		this.panelRight.add(buttonEntfernen);
		this.panelRight.add(new JLabel()); // platzhalter
		this.panelRight.add(new JLabel()); // platzhalter

		this.menuSave.add(menuItemCSVSave);
		this.menuSave.add(menuItemDBSave);
		this.menuBar.add(menuSave);

		this.panelBottomLeft.add(radioErledigt);
		this.panelBottomLeft.add(radioNichtGeschaft);
		this.panelBottomLeft.add(radioVergessen);
		
		panelBottom.add(panelBottomLeft);
		panelBottom.add(buttonSetTaskStatus);
		panelBottom.add(new JLabel());
		panelBottom.add(buttonShowAllNotDoneTasks);

		this.add(this.panelRight, BorderLayout.LINE_END);
		this.setJMenuBar(this.menuBar);
		this.add(this.panelBottom, BorderLayout.PAGE_END);
		this.add(this.liste, BorderLayout.LINE_START);

		this.disableButtons();
		
		this.pack();
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == buttonEntfernen) {
			liste.removeTask(liste.getSelectedTask());
			disableButtons();
		} else if (e.getSource() == buttonHinzufuegen) {
			SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
			try {
				Task newTask = new Task(FAECHER.valueOf((String) comboFach.getSelectedItem()), textfAufgabe.getText(),
						df.parse(textfDatum.getText()), STATUS.OFFEN);
				liste.addTask(newTask);
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
		} else if (e.getSource() == buttonAndern) {
			
		} else if (e.getSource() == buttonSetTaskStatus) {
			Task tempT = liste.getSelectedTask();
			if (radioErledigt.isSelected()) {
				tempT.setStatus(STATUS.ERLEDIGT);
				radioErledigt.setSelected(false);
			} else if (radioNichtGeschaft.isSelected()) {
				tempT.setStatus(STATUS.NICHTGESCHAFT);
				radioNichtGeschaft.setSelected(false);
			} else if (radioVergessen.isSelected()) {
				tempT.setStatus(STATUS.VERGESSEN);
				radioVergessen.setSelected(false);
			}
			liste.updateList();
		} else if (e.getSource() == menuItemCSVSave) {
			csvHandler.writeTasks(liste.getArrayList());
		} else if (e.getSource() == menuItemDBSave) {
			databaseHandler.writeTasks(liste.getArrayList());
		} else if (e.getSource() == radioErledigt || e.getSource() == radioNichtGeschaft
				|| e.getSource() == radioVergessen) { // wenn einer der drei
														// radio
														// buttons aktiviert
														// wird
			buttonSetTaskStatus.setEnabled(true);
		} else if (e.getSource() == buttonShowAllNotDoneTasks) {
			new DialogNotFinishedTasks(liste.getAllNotFinishedTasks());
		}
	}

	private void disableButtons() {
		this.buttonAndern.setEnabled(false);
		this.buttonEntfernen.setEnabled(false);
		this.radioErledigt.setEnabled(false);
		this.radioNichtGeschaft.setEnabled(false);
		this.radioVergessen.setEnabled(false);
		this.buttonSetTaskStatus.setEnabled(false);
	}
	
	public void enableButtons() {
		this.buttonAndern.setEnabled(true);
		this.buttonEntfernen.setEnabled(true);
		this.radioErledigt.setEnabled(true);
		this.radioNichtGeschaft.setEnabled(true);
		this.radioVergessen.setEnabled(true);
	}
}