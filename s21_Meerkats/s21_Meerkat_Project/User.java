package s21_Meerkat_Project;

/**
 * User class, used to create a User object A User object contains: userType: a
 * char identifying whether the User is an Admin or Customer userName: a String
 * containing the userName, this field is the primary key for database
 * references password: a String containing the password tempid: an int
 * containing a tempid (not implemented) id: a String containing the id (not
 * implemented)
 *
 */

public class User {

	/**
	 * userType: a char identifying whether the User is an Admin or Customer
	 */
	private char userType;
	/**
	 * userName: a String containing the userName, this field is the primary key for
	 * database references
	 */
	private String userName; // primary key
	/**
	 * password: a String containing the password
	 */
	private String password;
	/**
	 * tempid: an int containing a tempid (not implemented)
	 */
	private static int tempid = 1000;
	/**
	 * id: a String containing the id (not implemented)
	 */
	private String id;

	/**
	 * The empty constructor
	 */
	User() {

	}

	/**
	 * Full constructor containing userName, password, type
	 * 
	 * @param userName
	 *            String
	 * @param password
	 *            String
	 * @param type
	 *            char
	 */
	User(String userName, String password, char type) {
		this.userName = userName;
		this.password = password;
		this.tempid++;
		this.userType = type;
		this.id = "" + type + this.tempid;
	}

	/**
	 * toString method used to return a String containing all the fields of the User
	 * object
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "The username is: " + userName + ". \nThe password is: " + password
				+ ". The type is (A)min or (C)ustomer: " + userType;
	}

	/**
	 * getUserType method
	 * 
	 * @return userType char
	 */
	public char getUserType() {
		return this.userType;
	}

	/**
	 * setUserType
	 * 
	 * @param userType
	 *            char
	 */
	public void setUserType(char userType) {
		this.userType = userType;
	}

	/**
	 * getUserName method
	 * 
	 * @return userName String
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * setUserName method
	 * 
	 * @param userName
	 *            String
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * getPassword
	 * 
	 * @return password String
	 */
	public String getPassword() {
		return this.password;
	}

	/**
	 * setPassword
	 * 
	 * @param password
	 *            String
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * getID method
	 * 
	 * @return ID String
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * setID method
	 * 
	 * @param id
	 *            String
	 */
	public void setId(String id) {
		this.id = id;
	}
}
