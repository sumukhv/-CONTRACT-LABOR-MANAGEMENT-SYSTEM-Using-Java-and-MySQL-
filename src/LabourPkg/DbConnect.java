package LabourPkg;

//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.ObjectOutputStream;
import java.sql.Connection;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class DbConnect {

	public static final String URL = "jdbc:mysql://localhost:3306/labour_db?autoReconnect=true&useSSL=false";
	public static final String USER = "root";
	public static final String PASSWORD = "pwd2017*";
	public static final String DRIVER_CLASS = "com.mysql.jdbc.Driver";

	// private constructor
	public DbConnect() {
		try {
			// Step 2: Load MySQL Java driver
			Class.forName(DRIVER_CLASS);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private static Connection createConnection() {

		Connection connection = null;
		try {
			// Step 3: Establish Java MySQL connection
			if (connection.isClosed()) {
				connection = DriverManager.getConnection(URL, USER, PASSWORD);
				System.out.println("Connection Successful");		
			}else {
				System.out.println("Connection is open already");
			}
			
		} catch (SQLException e) {
			// System.out.println("ERROR: Unable to Connect to Database." + e.getMessage());
			JOptionPane.showMessageDialog(null,
					"SQL ERROR Unable to Connect to Database: " + e.getMessage().toString());
		} finally {

			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return connection;
	}

	public Connection getConnection() {
		return createConnection();
	}

	public int SqlInsUpdDel(String InsUpdDel) {
		// Result 1 is Failed.
		Connection con = getConnection();
		Statement stmt = null;
		int res = 0;
		try {
			stmt = con.createStatement();
			res = stmt.executeUpdate(InsUpdDel);
			return res;
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "SQL ERROR : " + ex.getMessage().toString());

			return 100;
		}

	}

	public ResultSet SqlSelect(String SelectSql) {
		// Result 1 is Failed.
		Connection con = getConnection();
		Statement stmt = null;
		ResultSet rs = null;

		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(SelectSql);
			// JOptionPane.showMessageDialog(null, SelectSql);
			return rs;
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "SQL ERROR : " + ex.getMessage().toString());
			return rs;
		}
	}
}