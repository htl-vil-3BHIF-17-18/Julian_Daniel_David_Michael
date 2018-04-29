package dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Date;

import bll.Task;

public class DatabaseIO {

	public DatabaseIO() {
	}

	public void writeTasks(ArrayList<Task> tasks) {
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			con = DriverManager.getConnection("jdbc:oracle:thin:d3b15/d3b@192.168.128.152:1521:ora11g");
			for (Task t : tasks) {

				stmt = con.prepareStatement("INSERT INTO tasks VALUES (?,?,?)");
				stmt.setString(1, t.getFach().toString());
				stmt.setString(2, t.getAufgabe());
				stmt.setString(3, this.convertDate(t.getBisDatum());
				stmt.execute();
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public ArrayList<Task> readTasks() {
		Connection con = null;
		Statement stmt_Select = null;
		ResultSet rs = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			con = DriverManager.getConnection("jdbc:oracle:thin:d3b15/d3b@192.168.128.152:1521:ora11g");
			stmt_Select = con.createStatement();

			rs = stmt_Select.executeQuery("SELECT * FROM tasks");
			while (rs.next()) {
				// TODO datenbanktabellen erstellen und daten versuchen zu lesen
				// rs.getInt(index), rs.getString(index)...

				// System.out.print(rs.getInt(1) + "\t"); //
				// System.out.println(rs.getString(2));
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
		return null;
	}
	
    private Date convertDate(java.util.Date utilDate) {
        return new Date(utilDate.getTime());
}

}
