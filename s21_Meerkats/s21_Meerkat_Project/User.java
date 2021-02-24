package s21_Meerkat_Project;

public class User {
	private char userType;	
	private String userName;
	private String password;
	private static int tempid = 1000;
	private String id;
	
	User(){
		
	}
	
	User(String userName, String password, char type){
		this.userName = userName;
		this.password = password;
		this.tempid++;
		this.userType = type;
		this.id = "" + type + this.tempid;
	}

	public char getUserType(){
		return this.userType;
	}
	
	public void setUserType(char userType) {
		this.userType = userType;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getId() {
		return this.id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
}
