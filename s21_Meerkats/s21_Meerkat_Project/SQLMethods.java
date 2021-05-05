package s21_Meerkat_Project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Enumeration;
import java.util.Scanner;

/**
 * SQLMethods class: contains all methods used to connect/disconnect with SQL
 * database
 *
 */
public class SQLMethods {
	/**
	 * This is connection to the Database.
	 */
	static Connection con = null;
	/**
	 * Statement to run SQL queries
	 */
	static Statement stmt = null;

	/**
	 * closeConnection method is used to close the connection with SQL database upon
	 * log out.
	 */
	public static void closeConnection() {
		if (con != null) {
			try {
				con.close();
				con = null;
				// stmt.close();
				System.out.println("The connection was successfully closed");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * checkConnect method checks for valid connection with SQL database, if not
	 * detected will attempt to create a connections using makeAConnection() method.
	 */
	public static void checkConnect() {
		if (con == null) {
			con = connect();
		}

		if (stmt == null) { // ?
			try {
				stmt = con.createStatement();
			} catch (SQLException e) {
				System.out.println("Cannot create the statement");
			}

		}

	}

	/**
	 * Creates a connection driver to the Database
	 * 
	 * @return - The connection to the database
	 */
	public static Connection connect() {
		Connection connection = null;
		String name = "meerkats";
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/" + name;
		try { // load the driver
			Class.forName(driver).newInstance();
			// System.out.println("Known drivers that are registered:");
			Enumeration enumer = DriverManager.getDrivers();
			// while (enumer.hasMoreElements())
			// System.out.println(enumer.nextElement());
		} catch (Exception e) { // problem loading driver, class not exist?
			e.printStackTrace();

		}
		try {
			connection = DriverManager.getConnection(url, "CSC202", "CSC202");
			System.out.println("Connection successful!");

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}

}