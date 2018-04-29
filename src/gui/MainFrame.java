package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

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

public class MainFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	// Menu bar components
	private JMenu menuSave;
	private JMenuBar menuBar;
	private JMenuItem menuItemDBSave;
	private JMenuItem menuItemCSVSave;
	
	private JPanel buttxtf = null;
	private JButton hinzufuegen;
	private JButton erledigt;
	private JButton entfernen;

	private JTextField jtfach;
	private JTextField jtaufgabe;
	private JTextField jtdatum;
	
	private JLabel platzhalter;
	private JPanel nm;
	private JButton notmaked;
	
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
	
	private void initializeControls(){
		BorderLayout grid = new BorderLayout();
		this.setLayout(grid);
		
		this.buttxtf = new JPanel(new GridLayout(11, 1));
		this.buttxtf.setPreferredSize(new Dimension(450, 400));
		
		this.hinzufuegen = new JButton("Hinzuf�gen");
		this.erledigt = new JButton("Erledigt");
		this.entfernen = new JButton("Entfernen");
		
		this.jtfach = new JTextField("Fach:");
		this.jtaufgabe = new JTextField("Aufgabe:");
		this.jtdatum = new JTextField("Datum:");
		
		this.platzhalter= new JLabel();
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
		
		this.buttxtf.add(jtfach);	
		this.buttxtf.add(jtaufgabe);	
		this.buttxtf.add(jtdatum);
		this.buttxtf.add(platzhalter);
		this.buttxtf.add(hinzufuegen);
		this.buttxtf.add(erledigt);
		this.buttxtf.add(entfernen);
		
		this.menuSave.add(menuItemCSVSave);
		this.menuSave.add(menuItemDBSave);
		this.menuBar.add(menuSave);
		
		this.nm.add(nigesch);
		this.nm.add(vergessen);
		
		this.add(this.buttxtf, BorderLayout.LINE_END);
		this.setJMenuBar(this.menuBar);
		this.add(this.nm, BorderLayout.PAGE_END);
		
	
		
		this.pack();
		this.setSize(1000, 850);
	}
}