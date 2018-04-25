package gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MainFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	// Menu bar components
	private JMenu menuSave;
	private JMenuBar menuBar;
	private JMenuItem menuItemDBSave;
	private JMenuItem menuItemCSVSave;
	
	public MainFrame() {
		this.setTitle("Hausaufgabenplaner");
		this.setSize(800, 600);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		initializeControls();
	}
	
	private void initializeControls() {
		// TODO: buttons labels etc
		this.setLayout(new BorderLayout());
		menuSave = new JMenu("Speicher");
		menuBar = new JMenuBar();
		
		menuItemDBSave = new JMenuItem("Datenbank");
		
		
		menuSave.add(menuItemCSVSave);
		menuSave.add(menuItemDBSave);
		
		menuBar.add(menuSave);
	}
	
}
