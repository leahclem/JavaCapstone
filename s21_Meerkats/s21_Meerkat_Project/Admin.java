package s21_Meerkat_Project;

/**
 * The Admin class extends the User class - used to create an Admin object.
 * An Admin object contains a first name, last name, user ID, password, and account type identifier 'A'.
 */
public class Admin extends User {
	// two private fields
	/**
	 * String - used to hold the Admin User's first name
	 */
	private String fname;
	/**
	 * String - used to hold the Admin User's last name
	 */
	private String lname;
	/**
	 * Empty Admin constructor
	 */
	Admin() {

	}

	/**
	 * Full Constructor - used to create a new Admin User containing parent class User fields:
	 * username, password, and type of user
	 * @param userName - Admin user name 
	 * @param password - Admin password
	 * @param fname - Admin first name
	 * @param lname - Admin last name
	 * 
	 */
	Admin(String userName, String password, String fname, String lname) {
		super(userName, password, 'A');
		this.fname = fname;
		this.lname = lname;
	}
	/**
	 * toString method
	 * @return String containing User and Admin fields 
	 */
	public String toStringF() {
		return getUserName().toString() + "|" + getPassword().toString() + "|" + fname + "|" + lname;// +"|"+baseAdmin;
																										// note removed
																										// base admin
	}

	/**
	 * getFname method
	 * @return fname String
	 */
	public String getFname() {
		return this.fname;
	}

	/**
	 * setFname method
	 * @param fname String
	 */
	public void setFname(String fname) {
		this.fname = fname;
	}

	/**
	 * getLname method
	 * @return lname String
	 */
	public String getLname() {
		return this.lname;
	}

	/**
	 * setLname method
	 * @param lname String 
	 */
	public void setLname(String lname) {
		this.lname = lname;
	}
}
