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
		StringBuilder sb = new StringBuilder();
		sb.append(this.getUserName().toString()+"|"+this.getPassword().toString()+"|"+this.fname.toString()+"|"+this.lname.toString()+"|"+this.baseAdmin);
		return sb.toString();
		
	}
	// Create new Auction method
	
	// update auction details method
	
	// 
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
