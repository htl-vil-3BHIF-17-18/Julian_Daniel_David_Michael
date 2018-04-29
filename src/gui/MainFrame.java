package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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

import bll.Task;
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
	private JButton buttonErledigt;
	private JButton buttonAndern;
	private JButton buttonEntfernen;
	private JTextField textfFach;
	private JTextField textfAufgabe;
	private JTextField textfDatum;

	// Radio Buttons unten
	private JPanel pannelnotmaked;
	private JRadioButton vergessen;
	private JRadioButton nigesch;
	
	// Die zwei Listenn
	private TaskList liste;
	private TaskList forgottenlist;

	private JLabel platzhalter;
	private JLabel platzhalter1;
	private JLabel platzhalter2;
	private JLabel platzhalter3;
	private JLabel platzhalter4;
	
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

		menuBar = new JMenuBar();
		this.menuBar.setPreferredSize(new Dimension(40, 60));
		menuSave = new JMenu("Speicher");
		menuItemDBSave = new JMenuItem("Datenbank");
		menuItemCSVSave = new JMenuItem("CSV");
		
		this.panelRight = new JPanel(new GridLayout(12, 1));
		this.panelRight.setPreferredSize(new Dimension(450, 40));
		this.panelList = new JPanel(new GridLayout(11, 1));
		this.panelList.setPreferredSize(new Dimension(40, 40));
		this.pannelnotmaked = new JPanel(new GridLayout(4, 2));
		this.pannelnotmaked.setPreferredSize(new Dimension(8, 75));
	
		this.buttonHinzufuegen = new JButton("Hinzuf�gen");
		this.buttonErledigt = new JButton("Erledigt");
		this.buttonAndern = new JButton("Aendern");
		this.buttonEntfernen = new JButton("Entfernen");

		this.textfFach = new JTextField("Fach:");
		this.textfAufgabe = new JTextField("Aufgabe:");
		this.textfDatum = new JTextField("Datum:");

		this.nigesch = new JRadioButton("nicht geschafft");
		this.vergessen = new JRadioButton("vergessen");
		ButtonGroup groupRadios = new ButtonGroup();
		groupRadios.add(nigesch);
		groupRadios.add(vergessen);
		
		this.liste = new TaskList(new ArrayList<Task>(), this);
		this.liste.setPreferredSize(new Dimension(540, 500));
		this.forgottenlist = new TaskList(new ArrayList<Task>(), this);
		this.forgottenlist.setPreferredSize(new Dimension(50, 50));

		this.platzhalter = new JLabel();
		this.platzhalter1 = new JLabel();
		this.platzhalter2 = new JLabel();
		this.platzhalter3 = new JLabel();
		this.platzhalter4 = new JLabel();
		
		this.panelRight.add(platzhalter);
		this.panelRight.add(textfFach);
		this.panelRight.add(textfAufgabe);
		this.panelRight.add(textfDatum);
		this.panelRight.add(platzhalter1);
		this.panelRight.add(buttonHinzufuegen);
		this.panelRight.add(buttonErledigt);
		this.panelRight.add(buttonAndern);
		this.panelRight.add(buttonEntfernen);
		this.panelRight.add(platzhalter2);
		this.panelRight.add(platzhalter3);
		this.panelRight.add(forgottenlist);
	
		this.menuSave.add(menuItemCSVSave);
		this.menuSave.add(menuItemDBSave);
		this.menuBar.add(menuSave);

		this.pannelnotmaked.add(platzhalter4);
		this.pannelnotmaked.add(nigesch);
		this.pannelnotmaked.add(vergessen);
		
		this.add(this.panelRight, BorderLayout.LINE_END);
		this.setJMenuBar(this.menuBar);
		this.add(this.pannelnotmaked, BorderLayout.PAGE_END);
		this.add(this.liste, BorderLayout.LINE_START);
				
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