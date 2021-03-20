package s21_Meerkat_Project;

public class Admin extends User {
	private String fname;
	private String lname;
	private boolean baseAdmin = false;
	
	Admin(){
		
	}
	
	Admin(String userName, String password, String fname, String lname, boolean base){
		super(userName, password, 'A');
		this.fname = fname;
		this.lname = lname;
		baseAdmin = base;
	}
	
	public String toStringF() {
		return getUserName().toString()+"|"+getPassword().toString()+"|"+fname+"|"+lname+"|"+baseAdmin;
		
	}
	
	public String getFname() {
		return this.fname;
	}
	
	public void setFname(String fname) {
		this.fname = fname;
	}
	
	public String getLname() {
		return this.lname;
	}
	
	public void setLname(String lname) {
		this.lname = lname;
	}
	
	public boolean isBaseAdmin() {
		return this.baseAdmin;
	}
	
	public void setBaseAdmin(boolean base) {
		this.baseAdmin = base;
	}
	
	
}
