package s21_Meerkat_Project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SQLMethods {
	static Connection con = null;
	static Statement stmt = null;

	public static Connection makeAConnection() {
		Scanner scan = new Scanner(System.in);
		Connection connection = null;

		System.out.println("What is the name of the database?");
		String name = scan.nextLine();
		String url = "jdbc:mysql://localhost:3306/" + name;

		System.out.println("Username:");
		String uid = scan.nextLine();
		System.out.println("Password:");
		String pass = scan.nextLine();

		String driver = "com.mysql.jdbc.Driver";

		try { // load the driver
			Class.forName(driver);
		} catch (Exception e) { // problem loading driver, class not exist?
			e.printStackTrace();

		}
		try {
			connection = DriverManager.getConnection(url, uid, pass);
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

	public static void closeConnection() {
		if (con != null) {
			try {
				con.close();
				con = null;
				// stmt.close();
				System.out.println("The connectionwas successfully closed");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void checkConnect() {
		if (con == null) {
			System.out.println("A connection does not exist");
		} else
			System.out.println("A connection already existed so we will use it");

		if (stmt == null) {
			try {
				stmt = con.createStatement();
			} catch (SQLException e) {
				System.out.println("Cannot create the statement");
			}

		}

	}

}