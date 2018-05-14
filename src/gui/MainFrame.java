package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
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

import bll.IOHelper;
import bll.Task;
import bll.Task.FAECHER;
import bll.Task.STATUS;
import util.DateHelper;

public class MainFrame extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	private IOHelper ioHelper;

	// Menu bar components
	private JMenuBar menuBar;
	private JMenu menuSave;
	private JMenu menuLoad;
	private JMenuItem menuItemDBSave;
	private JMenuItem menuItemCSVSave;
	private JMenuItem menuItemDBLoad;
	private JMenuItem menuItemCSVLoad;

	// Panelright
	private JPanel panelRight = null;
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
	private JLabel labelStatus;
	private JRadioButton radioVergessen;
	private JRadioButton radioNichtGeschaft;
	private JRadioButton radioErledigt;
	private JButton buttonSetTaskStatus;
	private JButton buttonShowAllNotDoneTasks;

	// Die zwei Listenn
	private TaskList liste;

	public MainFrame() {
		ioHelper = IOHelper.getInstance();
		ioHelper.readConnectionConfig();
		this.setTitle("Hausaufgabenplaner");
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initializeControls();
		this.setVisible(true);
		this.setLocationRelativeTo(null); // Fenste mittig anzeigen
	}

	private void initializeControls() {
		BorderLayout grid = new BorderLayout();
		this.setLayout(grid);

		this.menuBar = new JMenuBar();
		this.menuBar.setPreferredSize(new Dimension(40, 60));
		this.menuSave = new JMenu("Speichern");
		this.menuLoad = new JMenu("Laden");
		this.menuItemDBSave = new JMenuItem("Datenbank");
		this.menuItemDBSave.addActionListener(this);
		this.menuItemCSVSave = new JMenuItem("CSV");
		this.menuItemCSVSave.addActionListener(this);
		this.menuItemDBLoad = new JMenuItem("Datenbank");
		this.menuItemDBLoad.addActionListener(this);
		this.menuItemCSVLoad = new JMenuItem("CSV");
		this.menuItemCSVLoad.addActionListener(this);

		this.panelRight = new JPanel(new GridLayout(0, 2));
		this.panelRight.setPreferredSize(new Dimension(450, 40));
		this.panelBottom = new JPanel(new GridLayout(1, 5));
		this.panelBottomLeft = new JPanel(new GridLayout(4, 1));
		this.panelBottomLeft.setPreferredSize(new Dimension(8, 75));

		this.buttonHinzufuegen = new JButton("Hinzuf\u00FCgen");
		this.buttonHinzufuegen.addActionListener(this);
		this.buttonAndern = new JButton("\u00C4ndern");
		this.buttonAndern.addActionListener(this);
		this.buttonEntfernen = new JButton("Entfernen");
		this.buttonEntfernen.addActionListener(this);

		this.comboFach = new JComboBox<>(Task.getAllFacher());
		this.textfAufgabe = new JTextField();
		this.textfDatum = new JTextField();

		this.labelFach = new JLabel("Fach: ");
		this.labelDatum = new JLabel("Datum: ");
		this.labelAufgabe = new JLabel("Aufgabe: ");

		this.labelStatus = new JLabel("Status auswahl: ");
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
		this.liste.setBorder(BorderFactory.createLoweredSoftBevelBorder());

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
		this.menuLoad.add(menuItemCSVLoad);
		this.menuLoad.add(menuItemDBLoad);
		this.menuBar.add(menuSave);
		this.menuBar.add(menuLoad);

		this.panelBottomLeft.add(labelStatus);
		this.panelBottomLeft.add(radioErledigt);
		this.panelBottomLeft.add(radioNichtGeschaft);
		this.panelBottomLeft.add(radioVergessen);

		panelBottom.add(panelBottomLeft);
		panelBottom.add(buttonSetTaskStatus);
		panelBottom.add(new JLabel());
		panelBottom.add(new JLabel());
		panelBottom.add(buttonShowAllNotDoneTasks);

		this.setJMenuBar(this.menuBar);
		this.add(this.panelRight, BorderLayout.EAST);
		this.add(this.panelBottom, BorderLayout.SOUTH);
		this.add(this.liste, BorderLayout.WEST);
		this.add(new JLabel(" "), BorderLayout.CENTER);

		this.disableButtons();

		this.pack();
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == buttonEntfernen) {
			liste.removeTask(liste.getSelectedTask());
			disableButtons();
		} else if (e.getSource() == buttonHinzufuegen) {
			Task newTask = new Task(FAECHER.valueOf((String) comboFach.getSelectedItem()), textfAufgabe.getText(),
					DateHelper.getInstance().dateformatParse(textfDatum.getText()), STATUS.OFFEN);
			liste.addTask(newTask);
		} else if (e.getSource() == buttonAndern) {
			new DialogTaskEdit(liste.getSelectedTask(), liste);
		} else if (e.getSource() == buttonSetTaskStatus) {
			Task tempT = liste.getSelectedTask();
			if (radioErledigt.isSelected()) {
				tempT.setStatus(STATUS.ERLEDIGT);
				radioErledigt.setSelected(false);
			} else if (radioNichtGeschaft.isSelected()) {
				tempT.setStatus(STATUS.NICHTGESCHAFFT);
				radioNichtGeschaft.setSelected(false);
			} else if (radioVergessen.isSelected()) {
				tempT.setStatus(STATUS.VERGESSEN);
				radioVergessen.setSelected(false);
			}
			liste.updateList();
		} else if (e.getSource() == menuItemCSVSave) {
			ioHelper.writeCSV(liste.getArrayList());
		} else if (e.getSource() == menuItemDBSave) {
			ioHelper.writeDatabase(liste.getArrayList());
		} else if (e.getSource() == menuItemCSVLoad) {
			liste.setTasks(ioHelper.readCSV());
		} else if (e.getSource() == menuItemDBLoad) {
			liste.setTasks(ioHelper.readDatabase());
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
		this.labelStatus.setEnabled(false);
		this.radioVergessen.setEnabled(false);
		this.buttonSetTaskStatus.setEnabled(false);
	}

	public void enableButtons() {
		this.buttonAndern.setEnabled(true);
		this.buttonEntfernen.setEnabled(true);
		this.radioErledigt.setEnabled(true);
		this.radioNichtGeschaft.setEnabled(true);
		this.radioVergessen.setEnabled(true);
		this.labelStatus.setEnabled(true);
	}

}