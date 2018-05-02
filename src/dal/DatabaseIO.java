package dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import bll.Task;
import bll.Task.FAECHER;
import bll.Task.STATUS;

public class DatabaseIO {

	public DatabaseIO() {
	}

	public void writeTasks(ArrayList<Task> tasks) {
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			try {
				con = DriverManager.getConnection("jdbc:oracle:thin:d3b15/d3b@212.152.179.117:1521:ora11g");
			} catch (SQLException e) {
				System.err.println("Versuche mit localer IP auf DB zu verbinden...");
				try {
					con = DriverManager.getConnection("jdbc:oracle:thin:d3b15/d3b@192.168.128.152:1521:ora11g");
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			for (Task t : tasks) {
				stmt = con.prepareStatement("INSERT INTO tasks VALUES (?,?,?,?)");
				stmt.setString(1, t.getFach().toString());
				stmt.setString(2, t.getAufgabe());
				stmt.setDate(3, dateToSql(t.getBisDatum()));
				stmt.setString(4, t.getStatus().toString());
				stmt.execute();
			}
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Task> readTasks() {
		ArrayList<Task> tasks = new ArrayList<Task>();
		Task newTask;
		FAECHER fach;
		String aufgabe;
		java.util.Date date;
		STATUS status;
		Connection con = null;
		Statement stmt_Select = null;
		ResultSet rs = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			try {
				con = DriverManager.getConnection("jdbc:oracle:thin:d3b15/d3b@212.152.179.117:1521:ora11g");
			} catch (SQLException e) {
				System.err.println("Versuche mit localer IP auf DB zu verbinden...");
				try {
					con = DriverManager.getConnection("jdbc:oracle:thin:d3b15/d3b@192.168.128.152:1521:ora11g");
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			stmt_Select = con.createStatement();
			rs = stmt_Select.executeQuery("SELECT * FROM tasks");
			while (rs.next()) {
				fach = FAECHER.valueOf(rs.getString(1));
				aufgabe = rs.getString(2);
				date = dateToUtil(rs.getDate(3));
				status = STATUS.valueOf(rs.getString(4));

				newTask = new Task(fach, aufgabe, date, status);
				tasks.add(newTask);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt_Select.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return tasks;
	}

	private java.util.Date dateToUtil(java.sql.Date date) {
		return new java.util.Date(date.getTime());
	}

	private java.sql.Date dateToSql(java.util.Date date) {
		return new java.sql.Date(date.getTime());
	}

}
