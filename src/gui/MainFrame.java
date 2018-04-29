package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

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
	private JButton buttonHinzufuegen;
	private JButton buttonErledigt;
	private JButton buttonEntfernen;
	private JTextField textfFach;
	private JTextField textfAufgabe;
	private JTextField textfDatum;

	private JLabel platzhalter;
	private JPanel nm;
	private JButton notmaked;

	// Radio Buttons unten heil
	private JRadioButton vergessen;
	private JRadioButton nigesch;

	public MainFrame() {
		this.setTitle("Hausaufgabenplaner");
		this.setSize(2000, 900);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		initializeControls();
	}

	private void initializeControls() {
		databaseHandler = new DatabaseIO();
		csvHandler = new CSVIO();
		
		BorderLayout grid = new BorderLayout();
		this.setLayout(grid);

		this.panelRight = new JPanel(new GridLayout(11, 1));
		this.panelRight.setPreferredSize(new Dimension(450, 400));

		this.buttonHinzufuegen = new JButton("Hinzuf�gen");
		this.buttonErledigt = new JButton("Erledigt");
		this.buttonEntfernen = new JButton("Entfernen");

		this.textfFach = new JTextField("Fach:");
		this.textfAufgabe = new JTextField("Aufgabe:");
		this.textfDatum = new JTextField("Datum:");

		this.platzhalter = new JLabel();
		this.nm = new JPanel(new GridLayout(2, 1));
		this.nm.setPreferredSize(new Dimension(100, 100));
		this.nm.setBackground(Color.BLUE);

		this.nigesch = new JRadioButton("nicht geschafft");
		this.vergessen = new JRadioButton("vergessen");

		ButtonGroup groupRadios = new ButtonGroup();
		groupRadios.add(nigesch);
		groupRadios.add(vergessen);

		menuBar = new JMenuBar();
		this.menuBar.setPreferredSize(new Dimension(40, 60));
		menuSave = new JMenu("Speicher");
		menuItemDBSave = new JMenuItem("Datenbank");
		menuItemCSVSave = new JMenuItem("CSV");

		this.panelRight.add(textfFach);
		this.panelRight.add(textfAufgabe);
		this.panelRight.add(textfDatum);
		this.panelRight.add(platzhalter);
		this.panelRight.add(buttonHinzufuegen);
		this.panelRight.add(buttonErledigt);
		this.panelRight.add(buttonEntfernen);

		this.menuSave.add(menuItemCSVSave);
		this.menuSave.add(menuItemDBSave);
		this.menuBar.add(menuSave);

		this.nm.add(nigesch);
		this.nm.add(vergessen);

		this.add(this.panelRight, BorderLayout.LINE_END);
		this.setJMenuBar(this.menuBar);
		this.add(this.nm, BorderLayout.PAGE_END);

		this.pack();
		this.setSize(1000, 850);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == buttonEntfernen) {
			
		}else if(e.getSource() == buttonErledigt) {
			
		}else if(e.getSource() == buttonHinzufuegen) {
			
		}else if(e.getSource() == menuItemCSVSave) {
			// csvHandler.writeTasks();
		}else if(e.getSource() == menuItemDBSave) {
			// databaseHandler.writeTasks();
		}
	}
}