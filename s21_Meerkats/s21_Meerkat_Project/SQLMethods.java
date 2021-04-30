package s21_Meerkat_Project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * SQLMethods class: contains all methods used to connect/disconnect with SQL
 * database
 *
 */
public class SQLMethods {
	static Connection con = null;
	static Statement stmt = null;

	/**
	 * makeAConnection method makes a connection with SQL database. Please note that
	 * the password and database name is hard-coded for ease of use.
	 * 
	 * @return Connection
	 */
	public static Connection makeAConnection() {
		Connection connection = null;

		String url = "jdbc:mysql://localhost:3306/" + "meerkats";

		String driver = "com.mysql.jdbc.Driver";

		try { // load the driver
			Class.forName(driver);
		} catch (Exception e) { // problem loading driver, class not exist?
			e.printStackTrace();

		}
		try {
			connection = DriverManager.getConnection(url, "CSC202", "CSC202"); // url, uid, password
			stmt = connection.createStatement();
			System.out.println("Connection successful!");

		} catch (SQLException e) {
			e.printStackTrace();
		}

		finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}

			}
		}
		System.out.println("A new connection was made");
		return connection;
	}

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
			con = makeAConnection();
		}

		if (stmt == null) { // ?
			try {
				stmt = con.createStatement();
			} catch (SQLException e) {
				System.out.println("Cannot create the statement");
			}

		}

	}

}